package Model;

import java.util.ArrayList;
import java.util.Random;

import View.Map;
import View.Window;

public class Game implements Observer, Subject {

	private Window window;
	private World world;
	private Level level;
	private char[][] map;
	private ArrayList<Player> players = new ArrayList<>();
	private ArrayList<Monster> monsters = new ArrayList<>();
	private ArrayList<Item> items = new ArrayList<>();
	private ArrayList<Gate> gates = new ArrayList<>();
	private ArrayList<Collidable> collidables = new ArrayList<>();
	public ArrayList<Projectile> projectiles = new ArrayList<>();
	public Random random = new Random();

	public enum GameState {
		Running, Dead
	}

	public static GameState state = GameState.Running;

	public Game(Window window, Level level) {
		this.window = window;
		this.world = new World();
		this.setLevel(level);
		this.map = this.level.mapMatrix;
		players.add(new Player(100, 100, 4, 1000, 200, this));
		this.generateCollidables();
		this.notifyObserver(window);
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	@Override
	public synchronized void update() {
		if (state == GameState.Running) {
			sendToGraveyard();
			checkCollision();
			notifyObserver(window);
			if (players.get(0).getHealth() == 0) {
				state = GameState.Dead;
			}
		}
	}

	@Override
	public void notifyObserver(Observer observer) {
		observer.update();
		window.draw(map, players, monsters, items, projectiles);
	}

	public void generatePotion(int x, int y) {
		int rand = random.nextInt(3);
		Item potion;
		if (rand == 1) {
			potion = new Potion(x, y, random.nextInt(200), 0, false);
			items.add(potion);
			collidables.add(potion);
		} else if (rand == 2) {
			potion = new Potion(x, y, 0, random.nextInt(200), false);
			items.add(potion);
			collidables.add(potion);
		}
	}

	public void generateCollidables() {
		collidables.add(players.get(0));
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				char item = map[i][j];
				if (item == '1') {
					Block block = new Block(j, i);
					collidables.add(block);
				} else if (item == '2') {
					Monster monster = new Monster(j * Map.ratioWidth, i * Map.ratioHeight, 1, 100, this);
					monsters.add(monster);
					collidables.add(monster);
				} else if (item == 'N' || item == 'S' || item == 'E' || item == 'W') {
					Gate gate = new Gate(j, i, item);
					gates.add(gate);
					collidables.add(gate);
				} else if (item == 'P') {
					generatePotion(j * Map.ratioWidth, i * Map.ratioHeight);
				} else if (item == 'I') {
					Potion invincibleBonus = new Potion(j * Map.ratioWidth, i * Map.ratioHeight, 0, 0, true);
					items.add(invincibleBonus);
					collidables.add(invincibleBonus);
				}
			}
		}
	}

	public void checkCollision() {
		for (int j = 0; j < collidables.size(); j++) {

			if (collidables.get(j) != players.get(0) && players.get(0).collides(collidables.get(j))
					&& players.get(0).isChangingMap() == false) {
				collidables.get(j).acceptCollision(players.get(0));
			}

			for (int i = 0; i < monsters.size(); i++) {
				if (collidables.get(j) != monsters.get(i) && monsters.get(i).collides(collidables.get(j))) {
					collidables.get(j).acceptCollision(monsters.get(i));
				}
			}

			for (int i = 0; i < projectiles.size(); i++) {
				if (collidables.get(j) != projectiles.get(i) && projectiles.get(i).collides(collidables.get(j))) {
					collidables.get(j).acceptCollision(projectiles.get(i));
					projectiles.remove(i);
				}
			}
		}
	}

	public void sendToGraveyard() {
		for (int j = 0; j < collidables.size(); j++) {
			for (int i = 0; i < monsters.size(); i++) {
				if (collidables.get(j) == monsters.get(i) && monsters.get(i).dead) {
					generatePotion(monsters.get(i).getPosX(), monsters.get(i).getPosY());
					collidables.remove(j);
					monsters.remove(i);
				}
			}
			for (int i = 0; i < items.size(); i++) {
				if (collidables.get(j) == items.get(i) && items.get(i).isConsummed()) {
					collidables.remove(j);
					items.remove(i);
				}
			}
		}
	}

	public void movePlayerRight() {
		players.get(0).move(1, 0);
	}

	public void movePlayerLeft() {
		players.get(0).move(-1, 0);
	}

	public void movePlayerUp() {
		players.get(0).move(0, -1);
	}

	public void movePlayerDown() {
		players.get(0).move(0, 1);
	}

	public void stopRight() {
		players.get(0).setMovingRight(false);
		players.get(0).stop();
	}

	public void stopLeft() {
		players.get(0).setMovingLeft(false);
		players.get(0).stop();
	}

	public void stopUp() {
		players.get(0).setMovingUp(false);
		players.get(0).stop();
	}

	public void stopDown() {
		players.get(0).setMovingDown(false);
		players.get(0).stop();
	}

	public void playerAttack() {
		players.get(0).attack();
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public void addProjectile(int x, int y, int dir, int speed, int damage) {
		this.projectiles.add(new Projectile(x, y, dir, speed, damage));
	}

	public void changeLevelTo(char direction) {
		this.world.changeLevel(direction);
		int level = this.world.getLevel();
		String nameLevel = String.format("data/game_%s.txt", level);
		this.loadLevel(nameLevel);
	}

	public synchronized void loadLevel(String nameLevel) {
		for (int i = 0; i < monsters.size(); i++) {
			monsters.get(i).setHealth(0);
		}
		monsters.removeAll(monsters);
		collidables.removeAll(collidables);
		projectiles.removeAll(projectiles);
		items.removeAll(items);

		this.level = new Level(nameLevel);
		this.map = level.mapMatrix;
		this.generateCollidables();
		this.notifyObserver(window);
	}
}

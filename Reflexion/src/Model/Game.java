package Model;

import View.Window;

import java.util.ArrayList;

public class Game implements Observer, Subject {

	private Window window;
	private Level level;
	private char[][] map;
	private ArrayList<Player> players = new ArrayList<>();
	private ArrayList<Monster> monsters = new ArrayList<>();
	private ArrayList<Block> blocks = new ArrayList<>();
	private ArrayList<Gate> gates = new ArrayList<>();
	private ArrayList<Collidable> collidables = new ArrayList<>();
	public ArrayList<Projectile> projectiles = new ArrayList<>();

	public Game(Window window, Level level) {
		this.window = window;
		this.setLevel(level);
		this.map = this.level.mapMatrix;
		players.add(new Player(1, 1, 4, 1000, this));
		this.generateCollidables();
		this.notifyObserver(window);
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	@Override
	public synchronized void update() {
		sendToGraveyard();
		checkCollision();
		notifyObserver(window);
	}

	@Override
	public void notifyObserver(Observer observer) {
		observer.update();
		window.draw(map, players, monsters, blocks, projectiles);
	}

	public void generateCollidables() {
		collidables.add(players.get(0));
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				char item = map[i][j];
				if (item == '1') {
					Block block = new Block(j, i);
					blocks.add(block);
					collidables.add(block);
				} else if (item == '2') {
					Monster monster = new Monster(j, i, 1, 100, this);
					monsters.add(monster);
					collidables.add(monster);
				} else if (item == 'G') {
					Gate gate = new Gate(j, i, "data/game_1.txt");
					gates.add(gate);
					collidables.add(gate);
				}
			}
		}
	}

	
	public void checkCollision() {
		for (int j = 0; j < collidables.size(); j++) {
			if (collidables.get(j) != players.get(0) && players.get(0).collides(collidables.get(j))) {
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
					collidables.remove(j);
					monsters.remove(i);
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

	public synchronized void loadLevel(String nameLevel) {
		monsters.removeAll(monsters);
		collidables.removeAll(collidables);
		blocks.removeAll(blocks);
		projectiles.removeAll(projectiles);

		this.level = new Level(nameLevel);
		this.map = level.mapMatrix;
		this.generateCollidables();
		this.notifyObserver(window);
	}
}

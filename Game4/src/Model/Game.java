package Model;

import View.Window;

import java.util.ArrayList;

public class Game implements Runnable {

	private Window window;
	private Thread thread;
	private Level level;
	private char[][] map;
	private ArrayList<Player> players = new ArrayList<>();
	private ArrayList<Monster> monsters = new ArrayList<>();
	private ArrayList<Block> blocks = new ArrayList<>();
	private ArrayList<Collidable> collidables = new ArrayList<>();

	public Game(Window window, Level level) {
		this.window = window;
		this.level = level;
		this.map = level.mapMatrix;
		players.add(new Player(1, 1, 100)); // pos < dimension matrice
		this.generateCollidables();
		this.window.draw(map, players, monsters, blocks);
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		while (true) {
			try {
				players.get(0).update();
				moveMonsters();
				checkCollision();
				window.draw(map, players, monsters, blocks);
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void generateCollidables() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				char item = map[i][j];
				if (item == '1') {
					Block block = new Block(i, j);
					blocks.add(block);
					collidables.add(block);
				} else if (item == '2') {
					Monster monster = new Monster(i, j, 100);
					monsters.add(monster);
					collidables.add(monster);
				}
			}
		}
		collidables.add(players.get(0));
	}

	public void checkCollision() {
		for (int j = 0; j < collidables.size(); j++) {
			if (collidables.get(j) != players.get(0) && players.get(0).collides(collidables.get(j))) {
				int edge = players.get(0).collidesWith(collidables.get(j));
				players.get(0).applyCollision(collidables.get(j), edge);
			}
			for (int i = 0; i < monsters.size(); i++) {
				if (collidables.get(j) != monsters.get(i) && monsters.get(i).collides(collidables.get(j))) {
					int edge = monsters.get(i).collidesWith(collidables.get(j));
					monsters.get(i).applyCollision(collidables.get(j), edge);
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

	public void playerAttack() {
		// players.get(0).attack();
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

	public void moveMonsters() {
		for (Monster monster : monsters) {
			monster.update();
			monster.lookForPlayer(players.get(0));
		}
	}
}

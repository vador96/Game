package Model;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import View.Window;

public class Game implements Runnable {
	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<Block> blocks = new ArrayList<Block>();
	private ArrayList<Monster> monsters = new ArrayList<Monster>();
    private boolean playerAttacking = false;
	private Thread thread;
	private int[][] map;
	private Window window;
	private int size = 20;

	public Game(Window window) {
		this.window = window;
		players.add(new Player(100, 100));
		this.map = this.getMap();
		window.draw(this.map, players.get(0).getPosX(), players.get(0).getPosY());
		thread = new Thread(this);
		thread.start();
	}

	public void run() {
		while (true) {
			for (Monster monster : monsters) {
				checkCollision(monster.getHitBox());
			}
            if (isPlayerAttacking()) {
                for (int i = 0; i<monsters.size();i++) {
                    checkAttack(monsters.get(i));
                    if (monsters.get(i).getHealth() <= 0) {
                        monsters.remove(i);
                    }
                }
            }
			players.get(0).update();
            if (players.get(0).getHealth() <= 0) {
                System.out.println("dead");
                //players.remove(0);
                break;
            }
			window.draw(this.map, players.get(0).getPosX(), players.get(0).getPosY());
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void movePlayerLeft() {
		players.get(0).move(-1, 0);
	}

	public void movePlayerRight() {
		players.get(0).move(1, 0);
	}

	public void movePlayerDown() {
		players.get(0).move(0, 1);
	}

	public void movePlayerUp() {
		players.get(0).move(0, -1);
	}

	public void movePlayerStop() {
		players.get(0).move(0, 0);
	}

	public int[][] getMap() {
		int[][] map = new int[this.size][this.size];
		for (int i = 0; i < this.size; i++)
			for (int j = 0; j < this.size; j++)
				map[i][j] = 0;

		try {
			loadMap("data/map1.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Block block : blocks) {
			int x = block.getPosX();
			int y = block.getPosY();
			map[x][y] = 1;
		}

		for (Monster monster : monsters) {
			int x = monster.getPosX();
			int y = monster.getPosY();
			map[x][y] = 3;
		}

		return map;
	}

	private void loadMap(String filename) throws IOException {
		ArrayList<String> lines = new ArrayList<String>();
		int width = 0;
		int height = 0;

		BufferedReader reader = new BufferedReader(new FileReader(filename));
		while (true) {
			String line = reader.readLine();
			if (line == null) {
				reader.close();
				break;
			}

			if (!line.startsWith("!")) {
				lines.add(line);
				width = Math.max(width, line.length());
			}
		}

		height = lines.size();

		for (int j = 0; j < height; j++) {
			String line = (String) lines.get(j);
			for (int i = 0; i < width; i++) {
				if (i < line.length()) {
					char ch = line.charAt(i);
					if (ch == '1') {
						Block t = new Block(i, j);
						blocks.add(t);
					} else if (ch == '3') {
						Monster m = new Monster(i, j);
						monsters.add(m);
					}
				}
			}
		}
	}

	public void checkCollision(Rectangle r) {
		if (players.get(0).getHitBox().intersects(r)) {
			players.get(0).move(-1, 0);
			players.get(0).isCollision = true;
		}
	}

    public void checkAttack(Monster monster) {
        Rectangle r = monster.getHitBox();
        if (players.get(0).attackBox.intersects(r)) {
            monster.damage(10);
        }
    }

    public boolean isPlayerAttacking() {
        return this.playerAttacking;
    }

    public void setPlayerAttacking(boolean attacking) {
        this.playerAttacking = attacking;
    }

}

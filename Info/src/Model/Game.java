package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import View.Window;

public class Game {
	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<Block> blocks = new ArrayList<Block>();
	private Window window;
	private int size = 20;

	public Game(Window window) {
		this.window = window;
		// Map building

		try {
			loadMap("src/data/map1.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * for (int i = 0; i < size; i++) { blocks.add(new Block(i, 0));
		 * blocks.add(new Block(0, i)); blocks.add(new Block(i, size - 1));
		 * blocks.add(new Block(size - 1, i)); }
		 */

		// Creating one Player at position (1,1)
		players.add(new Player(10, 10));

		window.draw(this.getMap());
	}

	public void movePlayerLeft() {
		players.get(0).move(-1, 0);
		window.draw(this.getMap());
	}

	public void movePlayerRight() {
		players.get(0).move(1, 0);
		window.draw(this.getMap());
	}

	public void movePlayerDown() {
		players.get(0).move(0, 1);
		window.draw(this.getMap());
	}

	public void movePlayerUp() {
		players.get(0).move(0, -1);
		window.draw(this.getMap());
	}

	public int[][] getMap() {
		int[][] map = new int[this.size][this.size];
		for (int i = 0; i < this.size; i++)
			for (int j = 0; j < this.size; j++)
				map[i][j] = 0;

		for (Player player : players) {
			int x = player.getPosX();
			int y = player.getPosY();
			map[x][y] = 2;
		}
		for (Block block : blocks) {
			int x = block.getPosX();
			int y = block.getPosY();
			map[x][y] = 1;
		}
		System.out.println(map);
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
					if (Character.getNumericValue(ch) == 1) {
						Block b = new Block(i, j);
						blocks.add(b);
					}
				}
			}
		}
	}
}
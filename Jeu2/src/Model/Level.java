package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Level {

	private ArrayList<String> lines = new ArrayList<>();
	char[][] mapMatrix;
	private int width = 0;
	private int height = 0;

	public Level(String filename) {
		try {
			this.buildLevel(filename);
			this.generateMap();
		} catch (IOException e) {
			e.printStackTrace();
			e.getMessage();
		}
	}

	private void buildLevel(String filename) throws IOException {
		// read the file and build a list of String
		@SuppressWarnings("resource")
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String line;
		while ((line = reader.readLine()) != null) {
			lines.add(line);
			width = Math.max(width, line.length());
		}
		height = lines.size();
	}

	private char[][] generateMap() {
        // build a matrix of char with the elements of the lines-list
		mapMatrix = new char[height][width];
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				char item = lines.get(j).charAt(i);
				mapMatrix[j][i] = item;
			}
		}
		return mapMatrix;
	}
}

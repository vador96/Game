package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Level {

    private ArrayList<String> lines = new ArrayList<>();
    private int width = 0;
    private int height = 0;
    char[][] mapMatrix;

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

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
            width = Math.max(width, line.length());
        }
        height = lines.size();

    }

    private char[][] generateMap() {
        mapMatrix = new char[height][width];
        for (int i = 0; i<height; i++) {
            for (int j = 0; j<width; j++) {
                char item = lines.get(i).charAt(j);
                mapMatrix[i][j] = item;
            }
        }
        return mapMatrix;
    }
}

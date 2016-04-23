package View;

import Model.Monster;
import Model.Player;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Map extends JPanel{

    private char[][] mapMatrix;
    private ArrayList<Player> players;
    private ArrayList<Monster> monsters;

    public Map() {
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    public void paint(Graphics g) {
        if (mapMatrix == null) {
        } else {
            for (int i = 0; i < mapMatrix.length; i++) {
                for (int j = 0; j < mapMatrix[0].length; j++) {
                    int x = i;
                    int y = j;
                    int item = mapMatrix[i][j];

                    g.setColor(Color.lightGray);
                    g.fillRect(x * 50, y * 50, 50, 50);
                    g.setColor(Color.BLACK);
                    g.drawRect(x * 50, y * 50, 50, 50);
                    if (item == '1') {
                        g.setColor(Color.DARK_GRAY);
                        g.fillRect(x * 50, y * 50, 50, 50);
                    }
                }
            }
            for (Monster monster : monsters) {
                int x = monster.getPosX();
                int y = monster.getPosY();
                g.setColor(Color.RED);
                g.fillOval(x, y, 50, 50);
            }

            for (Player player : players) {
                int x = player.getPosX();
                int y = player.getPosY();
                g.setColor(Color.cyan);
                g.fillOval(x, y, 50, 50);
            }
        }
    }

    public void refresh(char[][] mapMatrix, ArrayList<Player> players, ArrayList<Monster> monsters) {
        this.mapMatrix = mapMatrix;
        this.players = players;
        this.monsters = monsters;
        repaint();
    }
}

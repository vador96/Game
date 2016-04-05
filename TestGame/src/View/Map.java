package View;

import Model.Game;
import Model.Level;
import Model.Monster;
import Model.Player;

import javax.swing.*;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Graphics;

public class Map extends JPanel {

    private int[][] mapMatrix;
    private Level level;

    public Map() {
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    public void paint(Graphics g) {
        if (mapMatrix == null) {
        } else {
            // construct the background
            for (int i = 0; i < mapMatrix.length; i++) {
                for (int j = 0; j < mapMatrix.length; j++) {
                    int x = i;
                    int y = j;
                    int color = mapMatrix[i][j];

                    if (color == 0) {
                        Image image = Toolkit.getDefaultToolkit().getImage("res/Background.png");
                        g.drawImage(image, x*20, y*20, this); // color 0 = BlockVide
                    }
                }
            }

            for(Player player : level.getPlayers()){
                int posX = player.getPosX();
                int posY = player.getPosY();
                String direction = player.getDirection();
                Image img = Toolkit.getDefaultToolkit().getImage("res/LinkRun"+direction+"1.png");
                g.drawImage(img, posX*20, posY*20, this);
            }

            for(Monster monster : level.getMonsters()){
                int posX = monster.getPosX();
                int posY = monster.getPosY();
                String direction = monster.getDirection();
                Image img = Toolkit.getDefaultToolkit().getImage("res/MeleeRun"+direction+"1.png");
                g.drawImage(img, posX*20, posY*20, this);
            }

        }
    }

    public void setMapMatrix(int [][] mapMatrix) {
        this.mapMatrix = mapMatrix;
        this.repaint();
    }
}
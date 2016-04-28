package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JPanel;

import Model.Block;
import Model.Monster;
import Model.Player;
import Model.Projectile;

public class Map extends JPanel {

	private static final long serialVersionUID = 1L;

	private char[][] mapMatrix;
	private ArrayList<Player> players;
	private ArrayList<Monster> monsters;
	private ArrayList<Block> blocks;
    private ArrayList<Projectile> projectiles;

	private final int sizeSquarre = 40;

	public Map() {
		this.setFocusable(true);
		this.requestFocusInWindow();
	}

	public void paintComponent(Graphics g) {
        super.paintComponent(g);
		if (mapMatrix == null) {
		} else {
			for (int i = 0; i < mapMatrix.length; i++) {
				for (int j = 0; j < mapMatrix[0].length; j++) {
					int x = i;
					int y = j;

					Image img = Toolkit.getDefaultToolkit().getImage("data/Background.png");
					g.drawImage(img, x * sizeSquarre, y * sizeSquarre, this);
				}
			}

			for (Block block : blocks) {
				int x = block.getPosX();
				int y = block.getPosY();
				// g.setColor(Color.DARK_GRAY);
				// g.fillRect(x, y, 50, 50);
				Image img = Toolkit.getDefaultToolkit().getImage("data/Obstacle1.png");
				g.drawImage(img, x, y, this);
			}

			for (Monster monster : monsters) {
				int x = monster.getPosX();
				int y = monster.getPosY();
				// g.setColor(Color.RED);
				// g.fillOval(x, y, 50, 50);
				Image img = Toolkit.getDefaultToolkit().getImage("data/BomberRunDown1.png");
				g.drawImage(img, x, y, this);
			}

			for (Player player : players) {
				int x = player.getPosX();
				int y = player.getPosY();
				g.setColor(Color.cyan);
				g.fillOval(x, y, sizeSquarre, sizeSquarre);
			}

            for (Projectile projectile : projectiles) {
                int x = (int) projectile.getHitbox().getX();
                int y = (int) projectile.getHitbox().getY();
                int width = (int) projectile.getHitbox().getWidth();
                int height = (int) projectile.getHitbox().getHeight();
                g.setColor(Color.YELLOW);
                g.fillRect(x, y, width, height);
            }
		}
	}

	public void refresh(char[][] mapMatrix, ArrayList<Player> players, ArrayList<Monster> monsters,
						ArrayList<Block> blocks, ArrayList<Projectile> projectiles) {
		this.mapMatrix = mapMatrix;
		this.players = players;
		this.monsters = monsters;
		this.blocks = blocks;
		this.projectiles = projectiles;
	}
}

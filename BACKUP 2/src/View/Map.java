package View;

import java.awt.Color;
import java.awt.Font;
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

	private Font fontPlayer = new Font(null, Font.BOLD, 20);

	private ArrayList<Player> players;
	private ArrayList<Monster> monsters;
	private ArrayList<Block> blocks;
	private ArrayList<Projectile> projectiles;
	
	public double NORMAL_WIDTH = 1000.0;
	public double NORMAL_HEIGHT = 1000.0;

	private final int TILE_WIDTH = 40;
	private final int TILE_HEIGHT = 40;
	public int minX = 0;
	public int maxX = (int) Window.WINDOW_WIDTH / TILE_WIDTH;
	public int minY = 0;
	public int maxY = (int) Window.WINDOW_HEIGHT / TILE_HEIGHT;
	
	public final int ratioWidth = (int) (Window.WINDOW_WIDTH/NORMAL_WIDTH*40.0);
	public final int ratioHeight = (int) (Window.WINDOW_HEIGHT/NORMAL_HEIGHT*40.0);
	
	Image img = Toolkit.getDefaultToolkit().getImage("data/Background.png");
	Image img1 = Toolkit.getDefaultToolkit().getImage("data/Obstacle1.png");

	public Map() {
		this.setFocusable(true);
		this.requestFocusInWindow();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (mapMatrix == null) {
		} else {
			for (int i = minY; i <= maxY && i < mapMatrix.length; i++) {
				for (int j = minX; j <= maxX && j < mapMatrix[0].length; j++) {
					int x = j * ratioWidth;
					int y = i * ratioHeight;

					char item = mapMatrix[i][j];

					g.drawImage(img, x, y, ratioWidth, ratioHeight, this);

					if (item == '1') {
						g.drawImage(img1, x, y, ratioWidth, ratioHeight, this);
					}
				}
			}

			for (Monster monster : monsters) {
				int x = monster.getPosX();
				int y = monster.getPosY();
				// g.setColor(Color.RED);
				// g.fillOval(x, y, 50, 50);
				Image img = Toolkit.getDefaultToolkit().getImage("data/BomberRun31.png");
				g.drawImage(img, x, y, ratioWidth, ratioHeight, this);
				g.setColor(Color.RED);
				g.drawRect(x, y - 12, monster.getHealth() / 2, 10);
				g.fillRect(x, y - 12, monster.getHealth() / 2, 10);
			}

			for (Player player : players) {
				int x = player.getPosX();
				int y = player.getPosY();
				// g.setColor(Color.cyan);
				// g.fillOval(x, y, sizeSquarre, sizeSquarre);
				Image img = Toolkit.getDefaultToolkit()
						.getImage("data/LinkRun" + Integer.toString(player.getDir()) + "1.png");
				g.drawImage(img, x, y, ratioWidth, ratioHeight, this);
				g.setColor(Color.RED);
				g.drawRect(750, 20, player.getMaxHealth() / 5, 20);
				g.fillRect(750, 20, player.getHealth() / 5, 20);

				g.setFont(fontPlayer);
				g.setColor(Color.WHITE);
				g.drawString(Integer.toString(player.getHealth()), 900, 38);

				g.setColor(Color.BLUE);
				g.drawRect(750, 50, player.getMaxHealth() / 5, 10);
				g.fillRect(750, 50, 200, 20);

				g.setFont(fontPlayer);
				g.setColor(Color.WHITE);
				g.drawString("1000", 900, 68);
			}

			for (Projectile projectile : projectiles) {
				int x = (int) projectile.getHitbox().getX();
				int y = (int) projectile.getHitbox().getY();
				// int width = (int) projectile.getHitbox().getWidth();
				// int height = (int) projectile.getHitbox().getHeight();
				// g.setColor(Color.YELLOW);
				// g.fillRect(x, y, width, height);
				Image img = Toolkit.getDefaultToolkit()
						.getImage("data/Arrow" + Integer.toString(projectile.getDirection()) + "1.png");
				g.drawImage(img, x, y, ratioWidth, ratioHeight, this);
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

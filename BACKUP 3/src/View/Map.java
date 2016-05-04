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

	public static double NORMAL_WIDTH = 1000.0;
	public static double NORMAL_HEIGHT = 1000.0;

	private final static double TILE_WIDTH = 40.0;
	private final static double TILE_HEIGHT = 40.0;

	public final static double RATIO_WIDTH = Window.WINDOW_WIDTH/NORMAL_WIDTH*TILE_WIDTH;
	public final static double RATIO_HEIGHT = Window.WINDOW_HEIGHT/NORMAL_HEIGHT*TILE_HEIGHT;

	public final static int ratioWidth = (int) RATIO_WIDTH;
	public final static int ratioHeight = (int) RATIO_HEIGHT;

	public int minX = 0;
	public int maxX = (int) (Window.WINDOW_WIDTH / RATIO_WIDTH);
	public int minY = 0;
	public int maxY = (int) (Window.WINDOW_HEIGHT / RATIO_HEIGHT);

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

			for (int i = 0; i<monsters.size(); i++) {
				int x = monsters.get(i).getPosX();
				int y = monsters.get(i).getPosY();
				// g.setColor(Color.RED);
				// g.fillOval(x, y, 50, 50);
				Image img = Toolkit.getDefaultToolkit().getImage("data/BomberRun31.png");
				g.drawImage(img, x, y, ratioWidth, ratioHeight, this);
				g.setColor(Color.RED);
				//g.drawRect(x, y - 12, monsters.get(i).getHealth() / 2, 10);
				//g.fillRect(x, y - 12, monsters.get(i).getHealth() / 2, 10);
			}

			for (int i = 0; i<players.size(); i++) {
				int x = players.get(i).getPosX();
				int y = players.get(i).getPosY();
				// g.setColor(Color.cyan);
				// g.fillOval(x, y, sizeSquarre, sizeSquarre);
				Image img = Toolkit.getDefaultToolkit()
						.getImage("data/LinkRun" + Integer.toString(players.get(i).getDir()) + "1.png");
				g.drawImage(img, x, y, ratioWidth, ratioHeight, this);
				g.setColor(Color.RED);
				g.drawRect(750, 20, players.get(i).getMaxHealth() / 5, 20);
				g.fillRect(750, 20, players.get(i).getHealth() / 5, 20);

				g.setFont(fontPlayer);
				g.setColor(Color.WHITE);
				g.drawString(Integer.toString(players.get(i).getHealth()), 900, 38);

				g.setColor(Color.BLUE);
				g.drawRect(750, 50, players.get(i).getMaxHealth() / 5, 10);
				g.fillRect(750, 50, 200, 20);

				g.setFont(fontPlayer);
				g.setColor(Color.WHITE);
				g.drawString("1000", 900, 68);
			}

			for (int i = 0; i<projectiles.size(); i++) {
				int x = (int) projectiles.get(i).getHitbox().getX();
				int y = (int) projectiles.get(i).getHitbox().getY();
				// int width = (int) projectile.getHitbox().getWidth();
				// int height = (int) projectile.getHitbox().getHeight();
				// g.setColor(Color.YELLOW);
				// g.fillRect(x, y, width, height);
				Image img = Toolkit.getDefaultToolkit()
						.getImage("data/Arrow" + Integer.toString(projectiles.get(i).getDirection()) + "1.png");
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

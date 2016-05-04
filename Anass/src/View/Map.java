package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JPanel;

import Model.Block;
import Model.Game;
import Model.Game.GameState;
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

	public static double NORMAL_WIDTH = 816.0;
	public static double NORMAL_HEIGHT = 839.0;

	private final static double TILE_WIDTH = 40.0;
	private final static double TILE_HEIGHT = 40.0;

	public final static double RATIO_WIDTH = Window.WINDOW_WIDTH / NORMAL_WIDTH * TILE_WIDTH;
	public final static double RATIO_HEIGHT = Window.WINDOW_HEIGHT / NORMAL_HEIGHT * TILE_HEIGHT;

	public final static int ratioWidth = (int) RATIO_WIDTH;
	public final static int ratioHeight = (int) RATIO_HEIGHT;

	Image img = Toolkit.getDefaultToolkit().getImage("data/Background.png");
	Image img1 = Toolkit.getDefaultToolkit().getImage("data/Obstacle1.png");

	public Map() {
		this.setFocusable(true);
		this.requestFocusInWindow();
	}

	public void paintComponent(Graphics g) {
		if (Game.state == GameState.Running) {
			super.paintComponent(g);
			if (mapMatrix == null) {
			} else {
				for (int i = 0; i < mapMatrix.length; i++) {
					for (int j = 0; j < mapMatrix[0].length; j++) {
						int x = j * ratioWidth;
						int y = i * ratioHeight;

						char item = mapMatrix[i][j];

						g.drawImage(img, x, y, ratioWidth, ratioHeight, this);

						if (item == '1') {
							g.drawImage(img1, x, y, ratioWidth, ratioHeight, this);
						}
					}
				}

				for (int i = 0; i < monsters.size(); i++) {
					int x = monsters.get(i).getPosX();
					int y = monsters.get(i).getPosY();
					Image img = Toolkit.getDefaultToolkit().getImage("data/BomberRun31.png");
					g.drawImage(img, x, y, ratioWidth, ratioHeight, this);
					g.setColor(Color.RED);
					g.drawRect(x, y - 12, monsters.get(i).getHealth() / 2, 10);
					g.fillRect(x, y - 12, monsters.get(i).getHealth() / 2, 10);
				}

				for (int i = 0; i < players.size(); i++) {
					int x = players.get(i).getPosX();
					int y = players.get(i).getPosY();
					Image img = Toolkit.getDefaultToolkit()
							.getImage("data/LinkRun" + Integer.toString(players.get(i).getDir()) + "1.png");
					g.drawImage(img, x, y, ratioWidth, ratioHeight, this);
					g.setColor(Color.RED);
					g.drawRect(550, 20, players.get(i).getMaxHealth() / 5, 20);
					g.fillRect(550, 20, players.get(i).getHealth() / 5, 20);

					g.setFont(fontPlayer);
					g.setColor(Color.WHITE);
					g.drawString(Integer.toString(players.get(i).getHealth()), 500, 38);

					g.setColor(Color.BLUE);
					g.drawRect(550, 50, players.get(i).getMaxHealth() / 5, 10);
					g.fillRect(550, 50, 200, 20);

					g.setFont(fontPlayer);
					g.setColor(Color.WHITE);
					g.drawString("1000", 500, 68);
				}

				for (int i = 0; i < projectiles.size(); i++) {
					int x = (int) projectiles.get(i).getHitbox().getX();
					int y = (int) projectiles.get(i).getHitbox().getY();
					Image img = Toolkit.getDefaultToolkit()
							.getImage("data/Arrow" + Integer.toString(projectiles.get(i).getDirection()) + "1.png");
					g.drawImage(img, x, y, ratioWidth, ratioHeight, this);
				}
			}
		} else if (Game.state == GameState.Dead) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0,(int) Window.WINDOW_WIDTH,(int) Window.WINDOW_HEIGHT);
			g.setColor(Color.WHITE);
			g.drawString("Game Over", 360, 240);
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

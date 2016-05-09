package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JPanel;

import Model.Game;
import Model.Game.GameState;
import Model.Item;
import Model.Monster;
import Model.Player;
import Model.Projectile;

public class Map extends JPanel {

	private static final long serialVersionUID = 1L;

	private static double NORMAL_WIDTH = 816.0;
	private static double NORMAL_HEIGHT = 839.0;

	private final static double TILE_WIDTH = 40.0;
	private final static double TILE_HEIGHT = 40.0;

	public final static double RATIO_WIDTH = Window.WINDOW_WIDTH / NORMAL_WIDTH * TILE_WIDTH;
	public final static double RATIO_HEIGHT = Window.WINDOW_HEIGHT / NORMAL_HEIGHT * TILE_HEIGHT;

	public final static int ratioWidth = (int) RATIO_WIDTH;
	public final static int ratioHeight = (int) RATIO_HEIGHT;

	private Font fontPlayer = new Font(null, Font.BOLD, 20);

	private char[][] mapMatrix;
	private ArrayList<Player> players;
	private ArrayList<Monster> monsters;
	private ArrayList<Item> items;
	private ArrayList<Projectile> projectiles;

	Image background = Toolkit.getDefaultToolkit().getImage("data/Background.png");
	Image obstacle = Toolkit.getDefaultToolkit().getImage("data/Obstacle1.png");

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

						g.drawImage(background, x, y, ratioWidth, ratioHeight, this);

						if (item == '1') {
							g.drawImage(obstacle, x, y, ratioWidth, ratioHeight, this);
						}
					}
				}

				for (int i = 0; i < monsters.size(); i++) {
					int x = monsters.get(i).getPosX();
					int y = monsters.get(i).getPosY();
					Image monster = Toolkit.getDefaultToolkit().getImage("data/BomberRun3.png");
					g.drawImage(monster, x, y, ratioWidth, ratioHeight, this);

					g.setColor(Color.RED);
					// g.drawRect(x, y - 12, monsters.get(i).getHealth() / 4,
					// ratioHeight/4);
					g.fillRect(x, y - ratioHeight / 4, monsters.get(i).getHealth() * ratioWidth / 200, ratioHeight / 4);

					/*
					 * g.drawRect((int) monsters.get(i).getHitbox().getX(),
					 * (int) monsters.get(i).getHitbox().getY(),(int)
					 * monsters.get(i).getHitbox().getWidth(),(int)
					 * monsters.get(i).getHitbox().getHeight());
					 * g.setColor(Color.BLACK); g.drawRect((int)
					 * monsters.get(i).getRtop().getX(), (int)
					 * monsters.get(i).getRtop().getY(),(int)
					 * monsters.get(i).getRtop().getWidth(),(int)
					 * monsters.get(i).getRtop().getHeight()); g.drawRect((int)
					 * monsters.get(i).getRbot().getX(), (int)
					 * monsters.get(i).getRbot().getY(),(int)
					 * monsters.get(i).getRbot().getWidth(),(int)
					 * monsters.get(i).getRbot().getHeight()); g.drawRect((int)
					 * monsters.get(i).getRleft().getX(), (int)
					 * monsters.get(i).getRleft().getY(),(int)
					 * monsters.get(i).getRleft().getWidth(),(int)
					 * monsters.get(i).getRleft().getHeight()); g.drawRect((int)
					 * monsters.get(i).getRright().getX(), (int)
					 * monsters.get(i).getRright().getY(),(int)
					 * monsters.get(i).getRright().getWidth(),(int)
					 * monsters.get(i).getRright().getHeight());
					 */
				}

				for (int i = 0; i < players.size(); i++) {
					int x = players.get(i).getPosX();
					int y = players.get(i).getPosY();
					Image linkImage = Toolkit.getDefaultToolkit()
							.getImage("data/LinkRun" + Integer.toString(players.get(i).getDir()) + ".png");
					if (players.get(i).isInvincible()) {
						linkImage = Toolkit.getDefaultToolkit()
								.getImage("data/RedLinkRun" + Integer.toString(players.get(i).getDir()) + ".png");
					}
					g.drawImage(linkImage, x, y, ratioWidth, ratioHeight, this);

					g.setColor(Color.RED);
					g.drawRect(10, 20, players.get(i).getMaxHealth() / ratioWidth * 10, 20);
					g.fillRect(10, 20, players.get(i).getHealth() / ratioWidth * 10, 20);

					g.setFont(fontPlayer);
					g.setColor(Color.WHITE);
					g.drawString(Integer.toString(players.get(i).getHealth()), 14, 38);

					g.setColor(Color.BLUE);
					g.drawRect(10, 50, players.get(i).getMaxMana() / ratioWidth * 10, 20);
					g.fillRect(10, 50, players.get(i).getMana() / ratioWidth * 10, 20);

					g.setFont(fontPlayer);
					g.setColor(Color.WHITE);
					g.drawString(Integer.toString(players.get(i).getMana()), 14, 68);

					Image healPotion = Toolkit.getDefaultToolkit().getImage("data/potionHp.png");
					g.drawImage(healPotion, 15, 80, 22, 26, this);
					
					g.setFont(fontPlayer);
					g.setColor(Color.WHITE);
					g.drawString(Integer.toString(players.get(i).getInventory().getHealPotionsSize()), 45, 100);

					Image manaPotion = Toolkit.getDefaultToolkit().getImage("data/potionMana.png");
					g.drawImage(manaPotion, 15, 110, 22, 28, this);
					
					g.setFont(fontPlayer);
					g.setColor(Color.WHITE);
					g.drawString(Integer.toString(players.get(i).getInventory().getManaPotionsSize()), 45, 135);

					/*
					 * g.drawRect((int) players.get(i).getHitbox().getX(), (int)
					 * players.get(i).getHitbox().getY(),(int)
					 * players.get(i).getHitbox().getWidth(),(int)
					 * players.get(i).getHitbox().getHeight());
					 * g.setColor(Color.BLACK); g.drawRect((int)
					 * players.get(i).getRtop().getX(), (int)
					 * players.get(i).getRtop().getY(),(int)
					 * players.get(i).getRtop().getWidth(),(int)
					 * players.get(i).getRtop().getHeight()); g.drawRect((int)
					 * players.get(i).getRbot().getX(), (int)
					 * players.get(i).getRbot().getY(),(int)
					 * players.get(i).getRbot().getWidth(),(int)
					 * players.get(i).getRbot().getHeight()); g.drawRect((int)
					 * players.get(i).getRleft().getX(), (int)
					 * players.get(i).getRleft().getY(),(int)
					 * players.get(i).getRleft().getWidth(),(int)
					 * players.get(i).getRleft().getHeight()); g.drawRect((int)
					 * players.get(i).getRright().getX(), (int)
					 * players.get(i).getRright().getY(),(int)
					 * players.get(i).getRright().getWidth(),(int)
					 * players.get(i).getRright().getHeight());
					 */
				}

				for (int i = 0; i < projectiles.size(); i++) {
					int x = (int) projectiles.get(i).getHitbox().getX();
					int y = (int) projectiles.get(i).getHitbox().getY();
					Image img = Toolkit.getDefaultToolkit()
							.getImage("data/Arrow" + Integer.toString(projectiles.get(i).getDirection()) + ".png");
					g.drawImage(img, x - ratioWidth / 4, y - ratioHeight / 4, ratioWidth, ratioHeight, this);
					// g.setColor(Color.RED);
					// g.drawRect((int) projectiles.get(i).getHitbox().getX(),
					// (int) projectiles.get(i).getHitbox().getY(),(int)
					// projectiles.get(i).getHitbox().getWidth(),(int)
					// projectiles.get(i).getHitbox().getHeight());
				}

				for (int i = 0; i < items.size(); i++) {
					int x = (int) items.get(i).getHitbox().getX();
					int y = (int) items.get(i).getHitbox().getY();
					int width = 0;
					int height = 0;
					Image img = null;
					if (items.get(i).getType().equals("heal")) {
						img = Toolkit.getDefaultToolkit().getImage("data/potionHp.png");
						width = ratioWidth / 2;
						height = ratioHeight / 2;
					} else if (items.get(i).getType().equals("mana")) {
						img = Toolkit.getDefaultToolkit().getImage("data/potionMana.png");
						width = ratioWidth / 2;
						height = ratioHeight / 2;
					} else if (items.get(i).getType().equals("invincible")) {
						img = Toolkit.getDefaultToolkit().getImage("data/star.png");
						width = ratioWidth / 2;
						height = ratioHeight / 2;
					}
					g.drawImage(img, x, y, width, height, this);
				}
			}
		} else if (Game.state == GameState.Dead) {
			Image game = Toolkit.getDefaultToolkit().getImage("data/gameover.png");
			g.drawImage(game, 0, 0, (int) Window.WINDOW_WIDTH, (int) Window.WINDOW_HEIGHT, this);
		}
	}

	public void refresh(char[][] mapMatrix, ArrayList<Player> players, ArrayList<Monster> monsters,
			ArrayList<Item> items, ArrayList<Projectile> projectiles) {
		this.mapMatrix = mapMatrix;
		this.players = players;
		this.monsters = monsters;
		this.items = items;
		this.projectiles = projectiles;
	}
}

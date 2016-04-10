package game;

import java.awt.Image;
import java.awt.Rectangle;

public class Tile {

	private int tileX, tileY, speedX, type;
	public Image tileImage;

	private Background bg = StartingClass.getBg1();
	private Link link = StartingClass.getLink();
	private Rectangle r;

	public int getTileX() {
		return tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getType() {
		return type;
	}

	public Image getTileImage() {
		return tileImage;
	}

	public Background getBg() {
		return bg;
	}

	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	public void setTileY(int tileY) {
		this.tileY = tileY;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setTileImage(Image tileImage) {
		this.tileImage = tileImage;
	}

	public void setBg(Background bg) {
		this.bg = bg;
	}

	public Tile(int x, int y, int typeInt) {
		tileX = x * 40;
		tileY = y * 40;

		type = typeInt;

		r = new Rectangle();

		if (type == 5) {
			tileImage = StartingClass.tiledirt;
		} else if (type == 8) {
			tileImage = StartingClass.tilegrassTop;
		} else if (type == 4) {
			tileImage = StartingClass.tilegrassLeft;
		} else if (type == 6) {
			tileImage = StartingClass.tilegrassRight;
		} else if (type == 2) {
			tileImage = StartingClass.tilegrassBot;
		} else {
			type = 0;
		}
	}

	public void update() {
		speedX = bg.getSpeedX() * 5;
		tileX += speedX;
		r.setBounds(tileX, tileY, 40, 40);

		if (r.intersects(Link.yellowRed) && type != 0) {
			checkVerticalCollision(Link.rect, Link.rect2);
			checkSideCollision(Link.rect3, Link.rect4, Link.footleft, Link.footright);
		}
	}

	public void checkVerticalCollision(Rectangle rtop, Rectangle rbot) {
		if (rtop.intersects(r)) {

		}

		if (rbot.intersects(r) && type == 8) {
			link.setJumped(false);
			link.setSpeedY(0);
			link.setCenterY(tileY - 63);
		}
	}

	public void checkSideCollision(Rectangle rleft, Rectangle rright, Rectangle leftfoot, Rectangle rightfoot) {
		if (type != 5 && type != 2 && type != 0) {
			if (rleft.intersects(r)) {
				link.setCenterX(tileX + 102);

				link.setSpeedX(0);

			} else if (leftfoot.intersects(r)) {
				link.setCenterX(tileX + 85);
				link.setSpeedX(0);
			}

			if (rright.intersects(r)) {
				link.setCenterX(tileX - 62);

				link.setSpeedX(0);
			}

			else if (rightfoot.intersects(r)) {
				link.setCenterX(tileX - 45);
				link.setSpeedX(0);
			}
		}
	}
}

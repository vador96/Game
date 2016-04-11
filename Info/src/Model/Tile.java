package Model;

import java.awt.Image;

public class Tile {

	private int tileX, tileY, speedX, speedY, type;
	public Image tileImage;

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

	public Tile(int x, int y, int typeInt) {
		tileX = x ;
		tileY = y ;

		type = typeInt;

		if (type == 5) {
			tileImage = Game.tiledirt;
		} else if (type == 8) {
			tileImage = Game.tilegrassTop;
		} else if (type == 4) {
			tileImage = Game.tilegrassLeft;
		} else if (type == 6) {
			tileImage = Game.tilegrassRight;
		} else if (type == 2) {
			tileImage = Game.tilegrassBot;
		} else {
			type = 0;
		}
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}
}

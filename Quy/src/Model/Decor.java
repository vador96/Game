package Model;

import java.awt.*;

public abstract class Decor implements Collidable {

	private int posX;
	private int posY;
	private Rectangle hitbox;

	public Decor(int x, int y) {
		this.setPosX(x*ratioWidth);
		this.setPosY(y*ratioHeight);
		this.hitbox = new Rectangle(this.posX, this.posY, ratioWidth, ratioHeight);
	}

	public int getPosX() {
		return this.posX;
	}

	public void setPosX(int posX) {
		this.posX = posX; // *50 : en reference a la taille de la
										// map
	}

	public int getPosY() {
		return this.posY;
	}

	public void setPosY(int posY) {
		this.posY = posY; // *50 : en reference a la taille de la
										// map
	}

	@Override
	public Rectangle getHitbox() {
		return hitbox;
	}

	@Override
	public void setHitbox(int x, int y) {
		this.hitbox.setBounds(x, y, ratioWidth, ratioHeight);
	}

	public void setHitBox(int x, int y, int width, int heigth) {
		this.hitbox.setBounds(x, y, width, heigth);
	}
}

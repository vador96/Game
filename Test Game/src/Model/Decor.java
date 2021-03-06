package Model;

import java.awt.*;

public abstract class Decor implements Collidable {

	private int posX;
	private int posY;
	private Rectangle hitBox;
	private final int sizeSquarre = 40;

	public Decor(int x, int y) {
		this.setPosX(x);
		this.setPosY(y);
		this.hitBox = new Rectangle(this.posX, this.posY, sizeSquarre, sizeSquarre);
	}

	public int getPosX() {
		return this.posX;
	}

	public void setPosX(int posX) {
		this.posX = posX * sizeSquarre; // *50 : en reference a la taille de la
										// map
	}

	public int getPosY() {
		return this.posY;
	}

	public void setPosY(int posY) {
		this.posY = posY * sizeSquarre; // *50 : en reference a la taille de la
										// map
	}

	@Override
	public Rectangle getHitbox() {
		return hitBox;
	}

	@Override
	public void setHitBox(int x, int y) {
		this.hitBox.setBounds(x, y, sizeSquarre, sizeSquarre);
	}

	public void setHitBox(int x, int y, int width, int heigth) {
		this.hitBox.setBounds(x, y, width, heigth);
	}

	@Override
	public boolean collides(Collidable collidable) {
		boolean collision;
		Rectangle box = collidable.getHitbox();
		if (this.hitBox.intersects(box)) {
			collision = true;
		} else {
			collision = false;
		}
		return collision;
	}

	@Override
	public void applyCollision(Collidable collidable, int edge) {
		collidable.goBack(this.hitBox);
	}

	public void goBack(Rectangle hitbox) {

	}

	@Override
	public int collidesWith(Rectangle box) {
		return 0;
	}

	@Override
	public void getDamage(int damage) {

	}
}

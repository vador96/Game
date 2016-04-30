package Model;

import java.awt.*;

public abstract class Decor implements Collidable {

	private int posX;
	private int posY;
	private Rectangle hitbox;

	public Decor(int x, int y) {
		this.setPosX(x);
		this.setPosY(y);
		this.hitbox = new Rectangle(this.posX, this.posY, sizeSquare, sizeSquare);
	}

	public int getPosX() {
		return this.posX;
	}

	public void setPosX(int posX) {
		this.posX = posX * sizeSquare; // *50 : en reference a la taille de la
										// map
	}

	public int getPosY() {
		return this.posY;
	}

	public void setPosY(int posY) {
		this.posY = posY * sizeSquare; // *50 : en reference a la taille de la
										// map
	}

	@Override
	public Rectangle getHitbox() {
		return hitbox;
	}

	@Override
	public void setHitbox(int x, int y) {
		this.hitbox.setBounds(x, y, sizeSquare, sizeSquare);
	}

	public void setHitBox(int x, int y, int width, int heigth) {
		this.hitbox.setBounds(x, y, width, heigth);
	}

	@Override
	public boolean collides(Collidable collidable) {
		boolean collision;
		Rectangle box = collidable.getHitbox();
		if (this.hitbox.intersects(box)) {
			collision = true;
		} else {
			collision = false;
		}
		return collision;
	}

	@Override
	public void applyCollisionOn(Collidable collidable) {
		collidable.goBack(this.hitbox);
	}

	public void goBack(Rectangle hitbox) {

	}
	
	@Override
	public int collidesWith(Rectangle hitbox) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void getDamageFromMonster(int damage) {
		// TODO Auto-generated method stub
	}

	@Override
	public void getDamageFromPlayer(int damage) {
		// TODO Auto-generated method stub
	}
}

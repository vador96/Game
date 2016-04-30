package Model;

import java.awt.*;

public abstract class Decor implements Collidable {

	private int posX;
	private int posY;
	private Rectangle hitBox;

	public Decor(int x, int y) {
		this.setPosX(x);
		this.setPosY(y);
		this.hitBox = new Rectangle(this.posX, this.posY, sizeSquare, sizeSquare);
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
		return hitBox;
	}

	@Override
	public void setHitbox(int x, int y) {
		this.hitBox.setBounds(x, y, sizeSquare, sizeSquare);
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
	public void applyCollision(Collidable collidable, int damage) {
		collidable.getDamage(damage);
		collidable.goBack(this.hitBox);
	}


	@Override
	public void getDamage(int damage) {

	}
	


	@Override
	public int collidesWith(Collidable collidable) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void goBack(Rectangle hitBox) {
		// TODO Auto-generated method stub
		
	}
}

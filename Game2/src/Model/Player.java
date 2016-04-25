package Model;

import java.awt.*;

public class Player extends Character {

	private Rectangle hitBox;

	public Player(int x, int y, int hp) {
		this.setPosX(x);
		this.setPosY(y);
		this.hitBox = new Rectangle(this.posX, this.posY, 50, 50);
		this.setHealth(hp);

	}

	@Override
	public Rectangle getHitbox() {
		return hitBox;
	}

	@Override
	public void setHitBox(int x, int y) {
		this.hitBox.setRect(x, y, 50, 50);
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
	public void applyCollision(Collidable collidable) {
		System.out.println("collision");
	}

	public void update() {
		moveDown = true;
		moveLeft = true;
		moveRight = true;
		moveUp = true;
		move(speedX, speedY);
		setHitBox(this.posX, this.posY);
	}
}
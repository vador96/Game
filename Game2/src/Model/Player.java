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
		if (speedX < 0) {
			moveLeft = false;
			/*if (collidable instanceof Monster) {
				System.out.println("collision");
				move(1, 0);
			} else {*/
				move(0, 0);
				posX = (int) collidable.getHitbox().getX() + 50;
			//}
		} else if (speedX > 0) {
			moveRight = false;
			/*if (collidable instanceof Monster) {
				System.out.println("collision");
				move(-1, 0);
			} else {*/
				move(0, 0);
				posX = (int) collidable.getHitbox().getX() - 50;
			//}
		} else if (speedY < 0) {
			moveUp = false;
			/*if (collidable instanceof Monster) {
				System.out.println("collision");
				move(0, 1);
			} else {*/
				move(0, 0);
				posY = (int) collidable.getHitbox().getY() + 50;
			//}
		} else if (speedY > 0) {
			moveDown = false;
			/*if (collidable instanceof Monster) {
				System.out.println("collision");
				move(0, -1);
			} else {*/
				move(0, 0);
				posY = (int) collidable.getHitbox().getY() - 50;
			//}
		}
	}

	public void update() {
		move(speedX, speedY);
		setHitBox(this.posX, this.posY);
		moveDown = true;
		moveLeft = true;
		moveRight = true;
		moveUp = true;
	}
}
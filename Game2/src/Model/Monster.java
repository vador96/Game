package Model;

import java.awt.*;
import java.util.Random;

public class Monster extends Character {

	private Rectangle hitBox;
	private Rectangle fieldOfView;
	private int vision = 100;
	protected Random random;

	public Monster(int x, int y, int hp) {
		this.setPosX(x);
		this.setPosY(y);
		this.setSpeed(2);
		this.hitBox = new Rectangle(posX, posY, 50, 50);
		this.fieldOfView = new Rectangle(posX - vision, posY - vision, 3 * vision, 3 * vision);
		this.setHealth(hp);
		random = new Random();
	}

	@Override
	public Rectangle getHitbox() {
		return hitBox;
	}

	@Override
	public void setHitBox(int x, int y) {
		this.hitBox.setRect(x, y, 50, 50);
	}

	public Rectangle getFieldOfView() {
		return fieldOfView;
	}

	public void setFieldOfView(int x, int y) {
		this.fieldOfView.setRect(x - vision, y - vision, 3 * vision, 3 * vision);
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
	}

	public void update() {
		setHitBox(this.posX, this.posY);
		setFieldOfView(this.posX, this.posY);
	}

	public void lookForPlayer(Player target) {
		if (this.fieldOfView.intersects(target.getHitbox())) {
			huntTarget(target);
		} else {
			patrol();
		}
	}

	private void huntTarget(Player target) {
		int x = target.getPosX();
		int y = target.getPosY();
		int dx = 0;
		int dy = 0;

		if (posX <= x) {
			dx = 1;
		} else if (posY <= y) {
			dy = 1;
		} else if (posX > x + 50) {
			dx = -1;
		} else if (posY > y + 50) {
			dy = -1;
		}
		move(dx, dy);

	}

	private void patrol() {
		if (random.nextInt(10) == 7) {
			dir = random.nextInt(4);
		}

		if (dir == 2) {
			move(1, 0);
		} else if (dir == 0) {
			move(-1, 0);
		} else if (dir == 3) {
			move(0, 1);
		} else if (dir == 1) {
			move(0, -1);
		} else if (dir == 4) {
			move(0, 0);
		}
	}
}

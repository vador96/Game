package game;

import java.awt.Rectangle;

public class Projectile {

	private int x, y, speedX;
	private boolean visible;

	private Rectangle r;

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSpeedX() {
		return speedX;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Projectile(int startX, int startY) {
		x = startX;
		y = startY;
		speedX = 7;
		visible = true;

		r = new Rectangle(0, 0, 0, 0);
	}

	public void update() {
		x += speedX;
		r.setBounds(x, y, 10, 5);
		if (x > 800) {
			visible = false;
			r = null;
		}
		if (x < 801) {
			checkCollision();
		}
	}

	private void checkCollision() {
		if (r.intersects(StartingClass.hb.r)) {
			visible = false;
			StartingClass.score += 1;
		}

		if (r.intersects(StartingClass.hb2.r)) {
			visible = false;
			StartingClass.score += 1;
		}
	}
}

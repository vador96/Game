package Model;

import java.awt.*;

public abstract class Character implements Collidable {

	protected int posX;
	protected int posY;
	protected int speedX = 0; // vecteurs
	protected int speedY = 0; // directeurs
	protected int speed = 3;

	protected final int sizeSquarre = 40;

	protected int dir;

	private int health;
	private Rectangle hitBox;

	private boolean movingLeft;
	private boolean movingRight;
	private boolean movingUp;
	private boolean movingDown;

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	public boolean isMovingUp() {
		return movingUp;
	}

	public void setMovingUp(boolean movingUp) {
		this.movingUp = movingUp;
	}

	public boolean isMovingDown() {
		return movingDown;
	}

	public void setMovingDown(boolean movingDown) {
		this.movingDown = movingDown;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX * sizeSquarre; // *50 : en reference a la taille de la
										// map
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY * sizeSquarre;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		if (health >= 100) {
			this.health = 100;
		} else if (health <= 0) {
			this.health = 0;
		} else {
			this.health = health;
		}
	}

	public void move(int dx, int dy) {
		if (dx < 0) {
			this.posX = posX + speed * dx;
			dir = 0;
			setMovingLeft(true);
		} else if (dx > 0) {
			this.posX = posX + speed * dx;
			dir = 2;
			setMovingRight(true);
		} else if (dy < 0) {
			this.posY = posY + speed * dy;
			dir = 1;
			setMovingUp(true);
		} else if (dy > 0) {
			this.posY = posY + speed * dy;
			dir = 3;
			setMovingDown(true);
		}
		this.speedX = dx;
		this.speedY = dy;
	}

	public void stop() {
		if (isMovingRight() == false && isMovingLeft() == false && isMovingUp() == false && isMovingDown() == false) {
			move(0, 0);
		} else if (isMovingRight() == false && isMovingLeft() == true) {
			move(-1, 0);
		} else if (isMovingRight() == true && isMovingLeft() == false) {
			move(1, 0);
		} else if (isMovingUp() == false && isMovingDown() == true) {
			move(0, 1);
		} else if (isMovingUp() == true && isMovingDown() == false) {
			move(0, -1);
		}
	}
}

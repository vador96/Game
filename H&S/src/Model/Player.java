package Model;

public class Player {
	private int posX;
	private int posY;
	private int speed = 5;
	private int speedX;
	private int speedY;

	public Player(int X, int Y) {
		this.posX = X;
		this.posY = Y;
	}

	public int getPosX() {
		return this.posX;
	}

	public int getPosY() {
		return this.posY;
	}

	public void move(int X, int Y) {
		this.speedX = X * speed;
		this.speedY = Y * speed;
	}

	public void update() {
		if (this.speedX != 0) {
			this.posX += this.speedX;
		}

		if (this.speedY != 0) {
			this.posY += this.speedY;
		}
	}
}

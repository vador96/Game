package Model;

public class Player {
	private int posX;
	private int posY;
	private int speed = 15;

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
		this.posX += X * speed;
		this.posY += Y * speed;
	}
}

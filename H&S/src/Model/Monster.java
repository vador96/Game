package Model;

import java.awt.Rectangle;

public class Monster {
	private int posX;
	private int posY;
    private int health = 100;

	private Rectangle hitBox;

	public Monster(int X, int Y) {
		this.posX = X;
		this.posY = Y;

		this.hitBox = new Rectangle(X, Y, 50, 50);
		hitBox.setBounds(X*50, Y*50, 50, 50);
	}

	public int getPosX() {
		return this.posX;
	}

	public int getPosY() {
		return this.posY;
	}

	public Rectangle getHitBox() {
		return hitBox;
	}

    public int getHealth() { return health;}

    public void setHealth(int hp) {
        this.health = hp;
    }

	public void setHitBox(Rectangle hitBox) {
		this.hitBox = hitBox;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public void damage(int damage) {
		this.setHealth(this.health - damage);
	}
}

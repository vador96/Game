package Model;

public class Monster extends Character {
	
	public Monster(int health, int X, int Y, int direction){
		this.setHealth(health);
		this.setPosX(X);
		this.setPosY(Y);
		this.setDirection(direction);
	}
}

package Model;

public class Player extends Character {
    //private Inventory inventory;

    public Player(int health, int X, int Y, int direction) {
        this.setHealth(health);
        this.setPosX(X);
        this.setPosY(Y);
        this.setDirection(direction);
    }
}
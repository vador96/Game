package Model;

import java.awt.*;

public abstract class Character implements Collidable{

    protected int posX;
    protected int posY;
    protected int speedX = 0; // vecteurs
    protected int speedY = 0; // directeurs
    protected int speed = 5;
    
    protected int dir;
    
    private int health;
    private Rectangle hitBox;

    protected boolean moveLeft = true;
    protected boolean moveRight = true;
    protected boolean moveUp = true;
    protected boolean moveDown = true;

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX*50; //  *50 : en reference a la taille de la map
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY*50;
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
        if (dx < 0 && moveLeft) {
            this.posX = posX + speed * dx;
        } else if (dx > 0 && moveRight) {
            this.posX = posX + speed * dx;
        } else if (dy < 0 && moveUp) {
            this.posY = posY + speed * dy;
        } else if (dy > 0 && moveDown) {
            this.posY = posY + speed * dy;
        }
        this.speedX = dx;
        this.speedY = dy;
    }
}

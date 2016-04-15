package Model;

import java.awt.Rectangle;

public class Player {
    private int posX;
    private int posY;
    private int speed = 5;
    private int speedX;
    private int speedY;
    private int health = 100;
    public boolean isCollision = false;
    public Rectangle attackBox;
    private Rectangle hitBox;

    public Player(int X, int Y) {
        this.posX = X;
        this.posY = Y;
        this.hitBox = new Rectangle(X, Y, 70, 70);
        this.attackBox = new Rectangle(X+70, Y+70, 20, 70);
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
        hitBox.setRect(this.posX, this.posY, 70, 70);
        attackBox.setRect(this.posX +70, this.posY +70, 20, 70);

        if (isCollision) {
            damage(10);
        }
    }

    public void damage(int damage) {
        this.setHealth(this.health - damage);
        this.isCollision = false;
    }

    public void attack() {
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public Rectangle getHitBox() {
        return hitBox;
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

    public int getHealth() { return health;}

    public void setHealth(int hp) {
        this.health = hp;
    }
}

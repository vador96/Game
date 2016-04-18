package Model;

import java.awt.*;

public class Monster extends Character{

    private Rectangle hitBox;

    public Monster(int x, int y, int hp) {
        this.setPosX(x);
        this.setPosY(y);
        this.hitBox = new Rectangle(this.posX, this.posY, 50, 50);
        this.setHealth(hp);
    }

    @Override
    public Rectangle getHitbox() {
        return hitBox;
    }

    @Override
    public void setHitBox(int x, int y) {
        this.hitBox.setRect(x, y, 50, 50);

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
    }
}

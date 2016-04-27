package Model;

import java.awt.*;

public class Projectile implements Damage, Collidable {

    private int damage;
    private int speed;
    private int direction;
    public boolean visible = true;
    private Rectangle hitBox;

    public Projectile(int posX, int posY, int direction, int speed, int damage) {
        this.hitBox = new Rectangle(posX, posY, 10, 10);
        this.speed = speed;
        this.damage = damage;
        this.direction = direction;
    }

    @Override
    public void doDamage(int damage, Collidable collidable) {
        collidable.getDamage(damage);
    }

    @Override
    public void explode() {
        this.visible = false;
    }

    @Override
    public Rectangle getHitbox() {
        return hitBox;
    }

    @Override
    public void setHitBox(int x, int y) {
        if (visible) {
            hitBox.translate(x, y);
        }
        else {
            hitBox.setRect(0,0,0,0);
        }
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
    public void applyCollision(Collidable collidable, int edge) {
        if (visible) {
            doDamage(damage, collidable);
        }
    }

    @Override
    public int collidesWith(Collidable collidable) {
        return 0;
    }

    @Override
    public void getDamage(int damage) {
        this.explode();
    }

    public void update() {
        if (direction == 0) {
            this.setHitBox(-speed, 0);
        } else if (direction == 1) {
            this.setHitBox(0, -speed);
        } else if (direction == 2) {
            this.setHitBox(speed,0);
        } else if (direction == 3) {
            this.setHitBox(0, speed);
        }
    }

}

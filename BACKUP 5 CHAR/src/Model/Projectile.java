package Model;

import java.awt.*;

public class Projectile implements Damage, Collidable, Collision, Runnable {

	private int damage = 10;
	private int speed;
	private int direction;
	public boolean visible = true;
	public boolean possessed;
	private Rectangle hitbox;
	private Thread thread;

	public Projectile(int posX, int posY, int direction, int speed, int damage) {
		generateHitbox(posX, posY, direction);
		this.speed = speed;
		this.damage = damage;
		this.direction = direction;
		this.thread = new Thread(this);
		this.thread.start();
	}

    public void setPossessed(boolean b) {
        this.possessed = b;
    }

	public void generateHitbox(int posX, int posY, int direction) {
		int x = 0;
		int y = 0;
		switch (direction) {
		case 0:
			x = posX - 10;
			y = posY + 20;
			break;
		case 1:
			x = posX + 10;
			y = posY - 10;
			break;
		case 2:
			x = posX + 40;
			y = posY + 20;
			break;
		case 3:
			x = posX + 10;
			y = posY + 40;
			break;
		}
		this.hitbox = new Rectangle(x, y, 10, 10);
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public int getSpeed(){
		return this.speed;
	}


	@Override
	public void doDamage(int damage, Collidable collidable) {

	}

	@Override
	public void explode() {
		this.visible = false;
	}

	@Override
	public Rectangle getHitbox() {
		return hitbox;
	}

	@Override
	public void setHitbox(int x, int y) {
		if (visible) {
			hitbox.translate(x, y);
		} else {
			hitbox.setRect(0, 0, 0, 0);
		}
	}


	public void update() {
		if (direction == 0) {
			this.setHitbox(-speed, 0);
		} else if (direction == 1) {
			this.setHitbox(0, -speed);
		} else if (direction == 2) {
			this.setHitbox(speed, 0);
		} else if (direction == 3) {
			this.setHitbox(0, speed);
		}
	}

	@Override
	public void run() {
		while (visible) {
			try {
				update();
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void applyCollisionOn(Monster monster) {
        monster.getDamage(damage);
        this.explode();
	}

	@Override
	public void applyCollisionOn(Player player) {
	}

    @Override
    public void applyCollisionOn(Block block) {
        this.explode();
    }

    @Override
	public void applyCollisionOn(Projectile projectile) {
        this.explode();
	}

	@Override
	public void applyCollisionOn(Gate gate) {
        this.explode();
	}

    @Override
    public boolean collides(Collidable collidable) {
        boolean isCollision;
        Rectangle box = collidable.getHitbox();
        if (this.hitbox.intersects(box)) {
            isCollision = true;
        } else {
            isCollision = false;
        }
        return isCollision;
    }
	@Override
	public void acceptCollision(Collision collision) {
        collision.applyCollisionOn(this);
	}

	@Override
	public void applyCollisionOn(Potion potion) {
		// TODO Auto-generated method stub
	}
}

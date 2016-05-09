package Model;

import java.awt.*;

public class Projectile implements Collidable, Collision, Runnable {

	private int speed;
	private int direction;
	private int damage = 10;
	private boolean visible = true;
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
	
	private void generateHitbox(int posX, int posY, int direction) {
		//generate the hitbox of the projectile
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

	private void explode() {
		// set the attribute visible to false to remove the projectile of the game
		this.visible = false;
	}

    @Override
    public boolean collides(Collidable collidable) {
    	// return true if the projectile collide with something
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
	// accepte the collision and send a message to the object that has collide with to apply
	// the method when the object collides a projectile
        collision.applyCollisionOn(this);
	}

	@Override
	public void applyCollisionOn(Potion potion) {
		
	}
	
	@Override
	public void applyCollisionOn(Monster monster) {
	// send a message to the monster that the projectile had collided with and tell to the monster to lose HP 
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

	private void update() {
		// move the projectile
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
}

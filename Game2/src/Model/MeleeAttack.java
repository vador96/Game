package Model;

import java.awt.Rectangle;

public class MeleeAttack implements Damage, Collidable {

	private int damage;
	private Rectangle hitbox;

	public MeleeAttack(int damage) {
		this.damage = damage;
		this.hitbox = new Rectangle();
	}

	@Override
	public void doDamage(int damage, Collidable collidable) {

	}

	@Override
	public void explode() {
		// TODO Auto-generated method stub

	}

	@Override
	public Rectangle getHitbox() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setHitBox(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean collides(Collidable collidable) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void applyCollision(Collidable collidable) {
		// TODO Auto-generated method stub

	}
}

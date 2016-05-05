package Model;

public class Potion extends Item {

	public Potion(int x, int y, int hp, int mana, boolean invincible) {
		super(x, y, hp, mana, invincible);
	}

	@Override
	public void acceptCollision(Collision collision) {
		collision.applyCollisionOn(this);
	}
}

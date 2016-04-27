package Model;

import java.awt.*;

public interface Collidable {

	Rectangle getHitbox();

	void setHitBox(int x, int y);

	boolean collides(Collidable collidable);

	void applyCollision(Collidable collidable, int edge);

	int collidesWith(Collidable collidable);

	public void getDamage(int damage);
}

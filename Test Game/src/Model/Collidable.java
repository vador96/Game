package Model;

import java.awt.*;

public interface Collidable {

	Rectangle getHitbox();

	void setHitBox(int x, int y);

	boolean collides(Collidable collidable);

	void applyCollision(Collidable collidable, int edge);

	int collidesWith(Rectangle box);

	void getDamage(int damage);

	void goBack(Rectangle hitBox);
}

package Model;

import java.awt.*;

public interface Collidable {
	
	public final int sizeSquare = 40;
	
	Rectangle getHitbox();

	void setHitbox(int x, int y);

	boolean collides(Collidable collidable);

	void applyCollision(Collidable collidable, int edge);

	int collidesWith(Collidable collidable);

	void getDamage(int damage);

	void goBack(Rectangle hitBox);
}

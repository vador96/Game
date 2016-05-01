package Model;

import java.awt.*;

public interface Collidable {
	
	public final int sizeSquare = 40;
	
	Rectangle getHitbox();

	void setHitbox(int x, int y);
	
	void checkKey();

	boolean collides(Collidable collidable);

	void applyCollisionOn(Collidable collidable);

	int collidesWith(Rectangle box);

	void getDamageFromMonster(int damage);
	
	void getDamageFromPlayer(int damage);

	void goBack(Rectangle hitBox);
}

package Model;

import java.awt.*;

public interface Collidable {

	int sizeSquare = 40;

	Rectangle getHitbox();

	void setHitbox(int x, int y);

	void acceptCollision(Collision collision);
}

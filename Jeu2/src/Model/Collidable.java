package Model;

import java.awt.*;

import View.Map;

public interface Collidable {

	int ratioWidth = Map.ratioWidth; // global sprite size
	int ratioHeight = Map.ratioHeight; // global sprite size

	Rectangle getHitbox();

	void setHitbox(int x, int y);

	void acceptCollision(Collision collision);
}

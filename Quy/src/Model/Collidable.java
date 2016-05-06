package Model;

import java.awt.*;

import View.Map;

public interface Collidable {

	int ratioWidth = Map.ratioWidth;
	int ratioHeight = Map.ratioHeight;

	Rectangle getHitbox();

	void setHitbox(int x, int y);

	void acceptCollision(Collision collision);
}

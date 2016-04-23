package Model;

import java.awt.*;

public interface Collidable {

    Rectangle getHitbox();
    void setHitBox(int x, int y); // pas nécessaire
    boolean collides(Collidable collidable);
    void applyCollision(Collidable collidable);

}

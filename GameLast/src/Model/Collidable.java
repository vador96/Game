package Model;

import java.awt.*;

public interface Collidable {

    public Rectangle getHitbox();
    public void setHitBox(int x, int y); // pas nécessaire
    public boolean collides(Collidable collidable);
    public void applyCollision(Collidable collidable);

}

package Model;


public interface Collision {

    void applyCollisionOn(Monster monster);

    void applyCollisionOn(Player player);

    void applyCollisionOn(Block block);
    
    void applyCollisionOn(Potion potion);

    void applyCollisionOn(Gate gate);

    void applyCollisionOn(Projectile projectile);

    boolean collides(Collidable collidable);

}
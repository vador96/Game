package Model;


public class Block extends Decor {

    public Block(int x, int y){
        super(x,y);
    }

    @Override
    public void acceptCollision(Collision collision) {
        collision.applyCollisionOn(this);
    }
}

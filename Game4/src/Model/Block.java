package Model;

import java.awt.*;

public class Block implements Collidable {
    private int posX;
    private int posY;
    private Rectangle hitBox;
    private final int sizeSquarre = 40;

    public Block(int x, int y){
        this.setPosX(x);
        this.setPosY(y);
        this.hitBox = new Rectangle(this.posX, this.posY, sizeSquarre, sizeSquarre);
    }

    public int getPosX(){
        return this.posX;
    }

    public void setPosX(int posX) {
        this.posX = posX*sizeSquarre; //  *50 : en reference a la taille de la map
    }

    public int getPosY(){
        return this.posY;
    }

    public void setPosY(int posY) {
        this.posY = posY*sizeSquarre; //  *50 : en reference a la taille de la map
    }

    @Override
    public Rectangle getHitbox() {
        return hitBox;
    }

    @Override
    public void setHitBox(int x, int y) {
        this.hitBox.setBounds(x, y,sizeSquarre, sizeSquarre);
    }

    @Override
    public boolean collides(Collidable collidable) {
        return false;
    }

	@Override
	public void applyCollision(Collidable collidable, int edge) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int collidesWith(Collidable collidable) {
		// TODO Auto-generated method stub
		return 0;
	}

    @Override
    public void getDamage(int damage) {

    }
}

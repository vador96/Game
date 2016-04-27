package Model;

import java.awt.*;

public class Block implements Collidable {
    private int posX;
    private int posY;
    private Rectangle hitBox;

    public Block(int x, int y){
        this.setPosX(x);
        this.setPosY(y);
        this.hitBox = new Rectangle(this.posX, this.posY, 50, 50);
    }

    public int getPosX(){
        return this.posX;
    }

    public void setPosX(int posX) {
        this.posX = posX*50; //  *50 : en reference a la taille de la map
    }

    public int getPosY(){
        return this.posY;
    }

    public void setPosY(int posY) {
        this.posY = posY*50; //  *50 : en reference a la taille de la map
    }

    @Override
    public Rectangle getHitbox() {
        return hitBox;
    }

    @Override
    public void setHitBox(int x, int y) {
        this.hitBox.setRect(x, y, 50, 50);
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
}

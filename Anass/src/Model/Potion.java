package Model;

import java.awt.Rectangle;

import View.Map;

public class Potion {

    public boolean consummed;
    private int posX;
    private int posY;
    private Rectangle hitbox;
    private int healHp;

    public Potion(int x, int y) {
		this.setPosX(x*Map.ratioWidth);
		this.setPosY(y*Map.ratioHeight);
		this.hitbox = new Rectangle(this.posX, this.posY, Map.ratioWidth/4, Map.ratioHeight/4);
	}
    
    
    public void setConsummed(boolean b) {
        this.consummed = b;
    }



	public int getPosX() {
		return posX;
	}



	public void setPosX(int posX) {
		this.posX = posX;
	}



	public int getPosY() {
		return posY;
	}



	public void setPosY(int posY) {
		this.posY = posY;
	}



	public Rectangle getHitbox() {
		return hitbox;
	}



	public void setHitbox(Rectangle hitbox) {
		this.hitbox = hitbox;
	}



	public int getHealHp() {
		return healHp;
	}



	public void setHealHp(int healHp) {
		this.healHp = healHp;
	}



	public boolean isConsummed() {
		return consummed;
	}
}

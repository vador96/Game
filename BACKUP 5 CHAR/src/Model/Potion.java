package Model;

import java.awt.Rectangle;

import View.Map;

public class Potion extends Item{

    public boolean consummed;
    private int posX;
    private int posY;
    private Rectangle hitbox;
    private int bonusHeal;
    private int bonusMana;
    private boolean bonusInvincible;

    public Potion(int x, int y, int hp, int mana, boolean invincible) {
		this.setPosX(x*Map.ratioWidth);
		this.setPosY(y*Map.ratioHeight);
		this.bonusHeal = hp;
		this.bonusMana = mana;
		this.bonusInvincible = invincible;
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
	
	public boolean isConsummed() {
		return consummed;
	}

	public int getBonusHeal() {
		return bonusHeal;
	}

	public void setBonusHeal(int bonusHeal) {
		this.bonusHeal = bonusHeal;
	}

	public int getBonusMana() {
		return bonusMana;
	}

	public void setBonusMana(int bonusMana) {
		this.bonusMana = bonusMana;
	}

	public boolean isBonusInvincible() {
		return bonusInvincible;
	}

	public void setBonusInvincible(boolean bonusInvincible) {
		this.bonusInvincible = bonusInvincible;
	}
	
	@Override
	public void acceptCollision(Collision collision) {
		collision.applyCollisionOn(this);
	}
}

package Model;

import java.awt.Rectangle;

import View.Map;

public abstract class Item implements Collidable {
	private int posX;
	private int posY;
	private Rectangle hitbox;
	private String type;
	private int bonusHealth;
	private int bonusMana;
	private boolean bonusInvincible;
	public boolean consummed = false;

	public Item(int x, int y, int hp, int mana, boolean invincible) {
		this.setPosX(x);
		this.setPosY(y);
		this.bonusHealth = hp;
		this.bonusMana = mana;
		this.bonusInvincible = invincible;
		this.defType();
		this.hitbox = new Rectangle(this.posX, this.posY, Map.ratioWidth / 4, Map.ratioHeight / 4);
	}
	
	public void setConsummed(boolean b) {
		this.consummed = b;
	}

	public boolean isConsummed() {
		return consummed;
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

	public void defType() {
		if (this.bonusHealth != 0) {
			this.type = "heal";
		} else if (this.bonusMana != 0) {
			this.type = "mana";
		} else if (this.bonusInvincible) {
			this.type = "invincible";
		}
	}

	public String getType() {
		return this.type;
	}

	public int getBonusHealth() {
		return bonusHealth;
	}

	public void setBonusHealth(int bonusHeal) {
		this.bonusHealth = bonusHeal;
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
	public void setHitbox(int x, int y) {
		// TODO Auto-generated method stub
		
	}
}

package Model;

public abstract class Character {

    String name;
    private int health;
    private int maxHealth;
    private int mana;
    private int maxMana;
    private int posX;
    private int posY;
    private int direction;
    protected boolean invincible;

    public int getHealth() {
        return this.health;
    }
    public int getMana() {
        return this.mana;
    }
    public int getPosX() {
        return this.posX;
    }
    public int getPosY() {
        return this.posY;
    }

    public void setHealth(int hp) {
        if (hp >= 0 && hp <= maxHealth) {
            this.health = hp;
        } else if (hp > maxHealth) {
            this.health = maxHealth;
        } else if (hp < 0) {
            this.health = 0;
        }
    }
    public void setMana(int mana) {
        if (mana >= 0 && mana <= maxMana) {
            this.mana = mana;
        } else if (mana > maxMana) {
            this.mana = maxMana;
        } else if (mana < 0) {
            this.mana = 0;
        }
    }
    public void setPosX(int x) {
        this.posX = x;
        // condition ?
    }
    public void setPosY(int y) {
        this.posY = y;
        // condition ?
    }
    
	public String getDirection(){
		String direction = new String();
		int dir = this.direction;
		if(dir == 1){
			direction = "Left";
		}else if(dir == 2){
			direction = "Up";
		}else if(dir == 3){
			direction = "Right";
		}else if(dir == 4){
			direction = "Down";
		}
		return direction;
	}
	
	public void setDirection(int direction){
		this.direction = direction;
	}

    public void getDamage(int damage) {
        if (!this.invincible) {
            int health = this.health;
            health -= damage;
            this.setHealth(health);
        }
    }

	public void move(int X, int Y){
		this.posX = this.posX + X;
		this.posY = this.posY + Y;
		
		if(X > 0){
			this.direction = 3;
		}else if (X < 0) {
			this.direction = 1;
		}else if (Y > 0) {
			this.direction = 4;
		}else if (Y < 0){
			this.direction = 2;
		}
	}
}
package Model;


public abstract class Character {

    String name;
    private int health;
    private int maxHealth;
    private int mana;dnjkfvndkv
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

    public void getDamage(int damage) {
        if (!this.invincible) {
            int health = this.health;
            health -= damage;
            this.setHealth(health);
        }
    }


    public void move(int dx, int dy) {
        this.posX = this.posX + dx;
        this.posY = this.posY + dy;
    }
}


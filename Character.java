package Model;


public abstract class Character {

    protected String name;
    protected int health;
    protected int maxHealth;
    protected int mana;
    protected int maxMana;
    protected int posX;
    protected int posY;
    protected int invincible; // set 0 for invincible

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
    }
    public void setPosY(int y) {
        this.posY = y;
    }

    public void move(int dx, int dy) {
        this.posX = this.posX + dx;
        this.posY = this.posY + dy;
    }
}

package Model;

public class Link extends Character{

    private int attack;
    private int defense;
    private int speed;

    public Link(int hp, int x, int y, int speed, int defense, int attack, String name) {
        this.setHealth(hp);
        this.setPosX(x);
        this.setPosY(y);
        this.speed = speed;
        this.defense = defense;
        this.attack = attack;
        this.name = name;
        this.invincible = false;
    }

    public int getAttack() {
        return this.attack;
    }
    public int getDefense() {
        return this.defense;
    }
    public int getSpeed() {
        return this.speed;
    }

}

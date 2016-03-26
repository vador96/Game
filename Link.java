package Model;

public class Link extends Character{

    private int attack;
    private int defense;
    private int speed;

    public Link(int hp, int x, int y, int speed, int defense, int attack, String name) {
        this.health = hp;
        this.posX = x;
        this.posY = y;
        this.speed = speed;
        this.defense = defense;
        this.attack = attack;
        this.name = name;
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

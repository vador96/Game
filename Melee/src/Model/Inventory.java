package Model;

import java.util.ArrayList;

public class Inventory {

    private int capacity;
    private ArrayList<Projectile> projectiles;
    private ArrayList<Potion> potions;

    public Inventory(int capacity) {
        this.capacity = capacity;
        this.buildProjectiles();
        this.buildPotions();
    }

    private void buildProjectiles() {
        for (int i = 0; i<capacity; i++) {
            projectiles.add(new Projectile(0,0,0,0,0));
        }
    }
    private void buildPotions() {
        for (int i = 0; i <capacity; i++) {
            projectiles.add(new Projectile(0,0,0,0,0));
        }
    }

    private int countProjectiles(ArrayList<Projectile> projectiles) {
        int n = 0;
        for (Projectile projectile : projectiles) {
            if (!projectile.possessed) {
                n +=1;
            }
        }
        return n;
    }

    private int countPotions(ArrayList<Potion> potions) {
        int n = 0;
        for (Potion potion : potions) {
            if (!potion.consummed) {
                n +=1;
            }
        }
        return n;
    }

    public void addProjectiles(int n) {
        int k = 0;
        for (int i = 0; i<projectiles.size(); i++) {
            if (! projectiles.get(i).possessed && k < n && countProjectiles(projectiles) < capacity) {
                projectiles.get(i).setPossessed(true);
            }
            else {
                break;
            }
        }
    }

    public void addPotions(int n) {
        int k = 0;
        for (int i = 0; i<potions.size(); i++) {
            if (! potions.get(i).consummed && k < n && countPotions(potions) < capacity) {
                potions.get(i).setConsummed(true);
            }
            else {
                break;
            }
        }
    }
}

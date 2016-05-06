package Model;

import java.util.ArrayList;

public class Inventory {

	private int capacity;
	private ArrayList<Potion> healPotions = new ArrayList<>();
	private ArrayList<Potion> manaPotions = new ArrayList<>();

	public Inventory(int capacity) {
		this.capacity = capacity;
	}

	public ArrayList<Potion> getHealPotions() {
		return healPotions;
	}

	public ArrayList<Potion> getManaPotions() {
		return manaPotions;
	}

	public void addPotion(Potion potion) {
		if (getHealPotionsSize() + getManaPotionsSize() < capacity && potion.getType().equals("heal")) {
			healPotions.add(potion);
		} else if(getHealPotionsSize() + getManaPotionsSize() < capacity && potion.getType().equals("mana")){
			manaPotions.add(potion);
		}
	}
	
	public boolean notFull(){
		return (getHealPotionsSize() + getManaPotionsSize() < capacity);
	}

	public int getHealPotionsSize() {
		return healPotions.size();
	}

	public int getManaPotionsSize() {
		return manaPotions.size();
	}
}

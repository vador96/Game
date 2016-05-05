package Model;

import java.awt.Rectangle;

public abstract class Item implements Collidable{

	public Item() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Rectangle getHitbox() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setHitbox(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	public void giveBonus(Player player) {
		// TODO Auto-generated method stub
		
	}
}

package Model;

import View.Map;

public class Gate extends Decor {

	private boolean open;
	public char type;

	public Gate(int x, int y, char type) {
		super(x, y);
		this.type = type;
		generateHitbox(type);
		this.open = true;
	}
	
	public void generateHitbox(char type){
		if(type == 'N'){
			setHitBox(this.getPosX(), this.getPosY(), Map.ratioWidth, Map.ratioHeight/4);
		}else if(type == 'S'){
			setHitBox(this.getPosX(), this.getPosY()+Map.ratioHeight, Map.ratioWidth, Map.ratioHeight/4);
		}else if(type == 'W'){
			setHitBox(this.getPosX(), this.getPosY(), Map.ratioWidth/4, Map.ratioHeight);
		}else if(type == 'E'){
			setHitBox(this.getPosX()+(Map.ratioWidth-Map.ratioWidth/4), this.getPosY(), Map.ratioWidth/4, Map.ratioHeight);
		}
	}


	public boolean isOpen() {
		return this.open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	@Override
	public void acceptCollision(Collision collision) {
		collision.applyCollisionOn(this);
	}
}

// changer le point de la matrice world lors d un changement de level
// on choisit le point de la matrice juste au dessus si l attribut type = N par exemple

package Model;

public class Gate extends Decor {

	private boolean open;
	public char type;

	public Gate(int x, int y, char type) {
		super(x, y);
		setHitBox(x * 40 + 37, y * 40, 3, 40);
		this.type = type;
		this.open = true;
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

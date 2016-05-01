package Model;

public class Gate extends Decor {

	private boolean open;
	private String nameLevel; // this is the level the door will load

	public Gate(int x, int y, String nameLevel) {
		super(x, y);
		setHitBox(x * 40 + 37, y * 40, 3, 40);
		this.nameLevel = nameLevel;
		this.open = true;
	}


	public boolean isOpen() {
		return this.open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public String getNameLevel() {
		return nameLevel;
	}

	public void setNameLevel(String nameLevel) {
		this.nameLevel = nameLevel;
	}

	@Override
	public void acceptCollision(Collision collision) {
		collision.applyCollisionOn(this);
	}
}

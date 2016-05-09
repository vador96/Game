package Model;

public class Gate extends Decor {

	private boolean open;
	public char type;

	public Gate(int x, int y, char type) {
		super(x, y);
		this.type = type;
		generateHitbox(type);
		this.open = true;
	}

	private void generateHitbox(char type) {
		// generate the hitbox according to the type-value
        // type can be North('N'), South('S'), East('E') or West('W')
		if (type == 'N') {
			setHitBox(this.getPosX(), this.getPosY(), ratioWidth, ratioHeight / 4);
		} else if (type == 'S') {
			setHitBox(this.getPosX(), this.getPosY() + ratioHeight, ratioWidth, ratioHeight / 4);
		} else if (type == 'W') {
			setHitBox(this.getPosX(), this.getPosY(), ratioWidth / 4, ratioHeight);
		} else if (type == 'E') {
			setHitBox(this.getPosX() + (ratioWidth - ratioWidth / 4), this.getPosY(), ratioWidth / 4, ratioHeight);
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
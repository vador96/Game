package Model;


public class Player extends Character {

	private boolean readyToAttack = true;

	public Player(int x, int y, int speed, int hp, Game game) {
		super(x, y, speed, hp, game);
	}

	public void getDamage(int damage) {
		int hp = this.getHealth();
		this.setHealth(hp - damage);
	}

	public void attack() {
		game.addProjectile(posX, posY, dir, speed * 3, 10);
	}

	public void update() {
		move(speedX, speedY);
		setHitbox(this.posX, this.posY);
		rtop.setBounds(this.posX + 10, this.posY, 20, 10);
		rbot.setBounds(this.posX + 10, this.posY + 30, 20, 10);
		rleft.setBounds(this.posX, this.posY + 10, 10, 20);
		rright.setBounds(this.posX + 30, this.posY + 10, 10, 20);
		notifyObserver(game);
	}

	public boolean isReadyToAttack() {
		return readyToAttack;
	}

	public void setReadyToAttack(boolean readyToAttack) {
		this.readyToAttack = readyToAttack;
	}


	@Override
	public void notifyObserver(Observer observer) {
		observer.update();
	}

	@Override
	public void run() {
		while (true) {
			try {
				update();
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

    @Override
    public void acceptCollision(Collision collision) {
        collision.applyCollisionOn(this);
    }

    @Override
    public void applyCollisionOn(Block block) {
        this.goBack(block);

    }

    @Override
    public void applyCollisionOn(Gate gate) {
        gate.setOpen(true);
        this.setPosX(5);
        this.setPosY(5);
        game.changeLevelTo(gate.type); // raccourci, vaut mieux checker d abord si y a une clef
    }
}
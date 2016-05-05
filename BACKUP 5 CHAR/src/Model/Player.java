package Model;

import View.Window;

public class Player extends Character {

	private boolean readyToAttack = true;
	private boolean invincible = false;
	private int maxMana;
	private int mana;

	public Player(int x, int y, int speed, int hp, int mana, Game game) {
		super(x, y, speed, hp, game);
		this.maxMana = mana;
		this.mana = maxMana;
	}

	public int getMana() {
		return this.mana;
	}

	public void setMana(int mana) {
		if (mana >= maxMana) {
			this.mana = maxMana;
		} else if (mana <= 0) {
			this.mana = 0;
		} else {
			this.mana = mana;
		}
	}

	public int getMaxMana() {
		return this.maxMana;
	}

	public void setMaxMana(int maxMana) {
		this.maxMana = maxMana;
	}

	public void getDamage(int damage) {
		int hp = this.getHealth();
		this.setHealth(hp - damage);
	}

	public void useMana(int mana) {
		setMana(this.getMana() - mana);
	}

	public void attack() {
		if (this.getMana() >= 5) {
			game.addProjectile(this.getPosX(), this.getPosY(), dir, speed * 3, 10);
			useMana(5);
		}
	}

	public void meleeAttack() {
		game.addProjectile(this.getPosX() + 10, this.getPosY() + 10, dir, 0, 10);
	}

	public void update() {
		super.update();
		move(speedX, speedY);
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
	public void applyCollisionOn(Potion potion) {
		this.getBonus(potion);
		this.game.removeItem(potion);
	}

	public void getBonus(Potion potion) {
		this.setHealth(this.getHealth() + potion.getBonusHeal());
		this.setMana(this.getMana() + potion.getBonusHeal());
		this.setInvincible(potion.isBonusInvincible());
	}

	@Override
	public void applyCollisionOn(Gate gate) {
		this.setInvincible(true);
		gate.setOpen(true);
		if (gate.type == 'W') {
			this.setPosX((int) (Window.WINDOW_WIDTH - 2 * this.getHitbox().getWidth()));
		} else if (gate.type == 'E') {
			this.setPosX((int) (this.getHitbox().getWidth() / 4.0));
		} else if (gate.type == 'N') {
			this.setPosY((int) (Window.WINDOW_HEIGHT - 2 * this.getHitbox().getWidth()));
		} else if (gate.type == 'S') {
			this.setPosY((int) (this.getHitbox().getWidth() / 4.0));
		}
		game.changeLevelTo(gate.type); // raccourci, vaut mieux checker d abord
		// si y a une clef
		this.setInvincible(false);
	}

	public boolean isInvincible() {
		return invincible;
	}

	public void setInvincible(boolean invicible) {
		this.invincible = invicible;
	}
}
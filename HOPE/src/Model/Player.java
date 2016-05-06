package Model;

import View.Window;

public class Player extends Character {

	private boolean readyToAttack = true;
	private boolean invincible = false;
	private boolean changingMap = false;
	private int tick1;
	private int tick2;
	private int limitTick;
	private final int TICK_CHANGE_MAP = 3;
	private int attackStat = 10;
	private int attack = 10;
	private int manaAttack = 100;

	public Player(int x, int y, int speed, int hp, int mana, Game game) {
		super(x, y, speed, hp, mana, game);
		this.tick1 = 0;
		this.tick2 = 0;
	}

	public void getDamage(int damage) {
		int hp = this.getHealth();
		if (!this.isInvincible()) {
			this.setHealth(hp - damage);
		}
	}
	
	public void attack() {
		if (this.getMana() >= manaAttack) {
			game.addProjectile(this.getPosX(), this.getPosY(), dir, speed * 3, this.attack);
			useMana(manaAttack);
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
				if (this.isInvincible() == true) {
					tick1 += 1;
				}
				if (tick1 >= limitTick && this.isInvincible() == true) {
					this.becomeNormal();
				}
				if (this.isChangingMap() == true) {
					tick2 += 1;
				}
				if (tick2 >= TICK_CHANGE_MAP && this.isChangingMap() == true) {
					this.setChangingMap(false);
				}
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
		potion.setConsummed(true);
	}

	public void getBonus(Potion potion) {
		this.setHealth(this.getHealth() + potion.getBonusHealth());
		this.setMana(this.getMana() + potion.getBonusMana());
		if (potion.isBonusInvincible() == true) {
			becomeInvincible(500);
		}
	}

	void becomeInvincible(int tickNumber) {
		this.setInvincible(true);
		this.setAttack(2 * attackStat);
		this.tick1 = 0;
		this.limitTick = tickNumber;
	}

	void becomeNormal() {
		setInvincible(false);
		this.setAttack(attackStat);
	}
	
	void changeMap(){
		this.setChangingMap(true);
		this.tick2 = 0;
	}

	@Override
	public void applyCollisionOn(Gate gate) {
		this.changeMap();
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
		game.changeLevelTo(gate.type);
	}

	public boolean isInvincible() {
		return invincible;
	}

	public void setInvincible(boolean invicible) {
		this.invincible = invicible;
	}

	public boolean isChangingMap() {
		return changingMap;
	}

	public void setChangingMap(boolean changingMap) {
		this.changingMap = changingMap;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}
}
package Model;

import java.awt.Rectangle;

public abstract class Character implements Collidable, Subject, Runnable {

	protected int posX;
	protected int posY;
	protected int speedX = 0; // vecteurs
	protected int speedY = 0; // directeurs
	protected int speed = 3;

	protected int dir = 3;
	private int maxHealth;
	private int health;

	protected Rectangle hitbox;
	protected Rectangle rtop;
	protected Rectangle rbot;
	protected Rectangle rleft;
	protected Rectangle rright;

	private Thread thread;
	private Game game;

	// private Rectangle hitBox;

	public boolean dead = false;

	private boolean movingLeft;
	private boolean movingRight;
	private boolean movingUp;
	private boolean movingDown;

	public Character(int x, int y, int speed, int hp, Game game) {
		this.setPosX(x);
		this.setPosY(y);
		this.setSpeed(speed);

		generateHitbox();

		this.setMaxHealth(hp);
		this.setHealth(hp);
		this.game = game;
		this.thread = new Thread(this);
		this.thread.start();
	}

	public void generateHitbox() {
		this.hitbox = new Rectangle(this.posX, this.posY, sizeSquare, sizeSquare);
		this.rtop = new Rectangle(this.posX + 10, this.posY, 20, 10);
		this.rbot = new Rectangle(this.posX + 10, this.posY + 30, 20, 10);
		this.rleft = new Rectangle(this.posX, this.posY + 10, 10, 20);
		this.rright = new Rectangle(this.posX + 30, this.posY + 10, 10, 20);
	}

	public Game getGame() {
		return this.game;
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	public boolean isMovingUp() {
		return movingUp;
	}

	public void setMovingUp(boolean movingUp) {
		this.movingUp = movingUp;
	}

	public boolean isMovingDown() {
		return movingDown;
	}

	public void setMovingDown(boolean movingDown) {
		this.movingDown = movingDown;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX * sizeSquare; // *50 : en reference a la taille de la
										// map
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY * sizeSquare;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	@Override
	public Rectangle getHitbox() {
		return hitbox;
	}

	@Override
	public void setHitbox(int x, int y) {
		this.hitbox.setBounds(x, y, sizeSquare, sizeSquare);
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		if (health >= maxHealth) {
			this.health = maxHealth;
		} else if (health <= 0) {
			this.health = 0;
			this.dead = true;
		} else {
			this.health = health;
		}
	}

	public void move(int dx, int dy) {
		if (dx < 0) {
			this.posX = posX + speed * dx;
			dir = 0; // LEFT
			setMovingLeft(true);
		} else if (dx > 0) {
			this.posX = posX + speed * dx;
			dir = 2; // RIGHT
			setMovingRight(true);
		} else if (dy < 0) {
			this.posY = posY + speed * dy;
			dir = 1; // UP
			setMovingUp(true);
		} else if (dy > 0) {
			this.posY = posY + speed * dy;
			dir = 3; // DOWN
			setMovingDown(true);
		}
		this.speedX = dx;
		this.speedY = dy;
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	@Override
	public boolean collides(Collidable collidable) {
		boolean collision;
		Rectangle box = collidable.getHitbox();
		if (this.hitbox.intersects(box)) {
			collision = true;
		} else {
			collision = false;
		}
		return collision;
	}

	public void attack() {
		game.addProjectile(posX, posY, dir, speed * 3, 10);
	}

	public void getDamage(int damage) {
		int hp = this.getHealth();
		this.setHealth(hp - damage);
	}

	public void update() {
		setHitbox(this.posX, this.posY);
		rtop.setBounds(this.posX + 10, this.posY, 20, 10);
		rbot.setBounds(this.posX + 10, this.posY + 30, 20, 10);
		rleft.setBounds(this.posX, this.posY + 10, 10, 20);
		rright.setBounds(this.posX + 30, this.posY + 10, 10, 20);
		notifyObserver(game);
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
}

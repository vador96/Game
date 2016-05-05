package Model;

import java.awt.Rectangle;

import View.Window;

public abstract class Character implements Collidable, Collision, Subject, Runnable {

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

	protected Thread thread;
	protected Game game;

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
		this.hitbox = new Rectangle(this.posX + 5, this.posY, 25, 40);
		this.rtop = new Rectangle(this.posX + 10, this.posY, 15, 4);
		this.rbot = new Rectangle(this.posX + 7, this.posY + 35, 15, 4);
		this.rleft = new Rectangle(this.posX + 5, this.posY + 11, 4, 18);
		this.rright = new Rectangle(this.posX + 26, this.posY + 10, 4, 18);
	}

	public void update() {
		setHitbox(this.posX, this.posY);
		rtop.setBounds(this.posX + 10, this.posY, (int) this.rtop.getWidth(), (int) this.rtop.getHeight());
		rbot.setBounds(this.posX + 10, this.posY + 35, (int) this.rbot.getWidth(), (int) this.rbot.getHeight());
		rleft.setBounds(this.posX + 5, this.posY + 11, (int) this.rleft.getWidth(), (int) this.rleft.getHeight());
		rright.setBounds(this.posX + 26, this.posY + 11, (int) this.rright.getWidth(), (int) this.rright.getHeight());
		notifyObserver(game);
	}

	@Override
	public void setHitbox(int x, int y) {
		this.hitbox.setBounds(x + 5, y, (int) this.getHitbox().getWidth(), (int) this.getHitbox().getHeight());
	}

	/**
	 * Replace le personnage à coté du bloc/monstre avec lequel il a collisionné
	 * 
	 * @param collidable
	 * L'entité collisionné
	 */
	public void goBack(Collidable collidable) {
		/**
		 * edge nous permet de connaitre de quel coté s'est passé la collision
		 * 
		 */
		int edge = collidesOnSide(collidable);
		Rectangle box = collidable.getHitbox();
		int xTarget = (int) box.getX();
		int yTarget = (int) box.getY();
		int widthTarget = (int) box.getWidth();
		int heightTarget = (int) box.getHeight();
		if (edge == 6) {
			setPosX(xTarget - (widthTarget));
		} else if (edge == 4) {
			setPosX(xTarget + (widthTarget));
		} else if (edge == 2) {
			setPosY(yTarget - (heightTarget));
		} else if (edge == 8) {
			setPosY(yTarget + (heightTarget));
		}
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
		if (posX > 0 && posX < Window.WINDOW_WIDTH) {
			this.posX = posX;
		} else {
			this.posX = 100;
		}
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		if (posY > 0 && posY < Window.WINDOW_WIDTH) {
			this.posY = posY;
		} else {
			this.posY = 100;
		}
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
			setPosX(this.posX + speed * dx);
			dir = 0; // LEFT
			setMovingLeft(true);
		} else if (dx > 0) {
			setPosX(this.posX + speed * dx);
			dir = 2; // RIGHT
			setMovingRight(true);
		} else if (dy < 0) {
			setPosY(this.posY + speed * dy);
			dir = 1; // UP
			setMovingUp(true);
		} else if (dy > 0) {
			setPosY(this.posY + speed * dy);
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
		boolean isCollision;
		Rectangle box = collidable.getHitbox();
		if (this.hitbox.intersects(box)) {
			isCollision = true;
		} else {
			isCollision = false;
		}
		return isCollision;
	}

	public int collidesOnSide(Collidable collidable) {

		Rectangle box = collidable.getHitbox();
		int edge = 0;
		if (this.rbot.intersects(box)) {
			edge = 2; // DOWN
		} else if (this.rtop.intersects(box)) {
			edge = 8; // UP
		} else if (this.rleft.intersects(box)) {
			edge = 4; // LEFT
		} else if (this.rright.intersects(box)) {
			edge = 6; // RIGHT
		}
		return edge;
	}

	@Override
	public void applyCollisionOn(Monster monster) {

	}

	@Override
	public void applyCollisionOn(Player player) {

	}

	@Override
	public void applyCollisionOn(Gate gate) {

	}

	@Override
	public void applyCollisionOn(Projectile projectile) {

	}

	@Override
	public void applyCollisionOn(Block block) {

	}

	public void attack() {
		game.addProjectile(posX, posY, dir, speed * 3, 10);
	}

	public void getDamage(int damage) {
		int hp = this.getHealth();
		this.setHealth(hp - damage);
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

	public void stop() {
		if (isMovingRight() == false && isMovingLeft() == false && isMovingUp() == false && isMovingDown() == false) {
			move(0, 0);
		} else if (isMovingRight() == false && isMovingLeft() == true) {
			move(-1, 0);
		} else if (isMovingRight() == true && isMovingLeft() == false) {
			move(1, 0);
		} else if (isMovingUp() == false && isMovingDown() == true) {
			move(0, 1);
		} else if (isMovingUp() == true && isMovingDown() == false) {
			move(0, -1);
		}
	}

	public Rectangle getRtop() {
		return rtop;
	}

	public void setRtop(Rectangle rtop) {
		this.rtop = rtop;
	}

	public Rectangle getRbot() {
		return rbot;
	}

	public void setRbot(Rectangle rbot) {
		this.rbot = rbot;
	}

	public Rectangle getRleft() {
		return rleft;
	}

	public void setRleft(Rectangle rleft) {
		this.rleft = rleft;
	}

	public Rectangle getRright() {
		return rright;
	}

	public void setRright(Rectangle rright) {
		this.rright = rright;
	}
}

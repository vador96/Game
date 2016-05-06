package Model;

import java.awt.Rectangle;

public abstract class Character implements Collidable, Collision, Subject, Runnable {

	protected int posX;
	protected int posY;
	protected int speedX = 0;
	protected int speedY = 0;
	protected int speed = 3;
	protected int dir = 3;

	private int maxHealth;
	private int health;
	private int maxMana;
	private int mana;
	public boolean dead = false;

	private boolean movingLeft;
	private boolean movingRight;
	private boolean movingUp;
	private boolean movingDown;

	private Rectangle hitbox;
	private Rectangle rtop;
	private Rectangle rbot;
	private Rectangle rleft;
	private Rectangle rright;
	private Rectangle fieldOfAction;

	private Thread thread;
	protected Game game;

	public Character(int x, int y, int speed, int hp, int mana, Game game) {
		this.setPosX(x);
		this.setPosY(y);
		this.setSpeed(speed);

		this.setMaxHealth(hp);
		this.setHealth(hp);
		this.setMaxMana(mana);
		this.setMana(mana);

		generateHitbox();

		this.game = game;
		this.thread = new Thread(this);
		this.thread.start();
	}

	private void generateHitbox() {
		this.hitbox = new Rectangle(this.posX, this.posY, ratioWidth,  ratioHeight);
		this.rtop = new Rectangle(this.posX + 10, this.posY, 20, 10);
		this.rbot = new Rectangle(this.posX + 10, this.posY + 30, 20, 10);
		this.rleft = new Rectangle(this.posX, this.posY + 10, 10, 20);
		this.rright = new Rectangle(this.posX + 30, this.posY + 10, 10, 20);
		this.fieldOfAction = new Rectangle(this.posX, this.posY + ratioHeight, ratioWidth, ratioHeight);
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX; // *50 : en reference a la taille de la
							// map
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getSpeed() {
		return speed;
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
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

	public void regenerateMana(int mana) {
		setMana(this.mana + mana);
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

	@Override
	public Rectangle getHitbox() {
		return hitbox;
	}

	@Override
	public void setHitbox(int x, int y) {
		this.hitbox.setBounds(x, y, ratioWidth, ratioHeight);
	}
	
	public void getDamage(int damage) {
		int hp = this.getHealth();
		this.setHealth(hp - damage);
	}
	
	public void useMana(int mana) {
		setMana(this.getMana() - mana);
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

	public void stop() {
		if (! isMovingRight() && ! isMovingLeft() && ! isMovingUp() && ! isMovingDown()) {
			move(0, 0);
		} else if (! isMovingRight() && isMovingLeft()) {
			move(-1, 0);
		} else if (isMovingRight() && ! isMovingLeft()) {
			move(1, 0);
		} else if (! isMovingUp() && isMovingDown()) {
			move(0, 1);
		} else if (isMovingUp() && ! isMovingDown()) {
			move(0, -1);
		}
	}
	
	public void setFieldOfAction() {
        if(dir == 0) {
            this.fieldOfAction.setBounds(posX - ratioWidth/4, posY, ratioWidth/4, ratioHeight);
        } else if (dir == 2) {
            this.fieldOfAction.setBounds(posX + ratioWidth, posY, ratioWidth/4, ratioHeight);
        } else if (dir == 1) {
            this.fieldOfAction.setBounds(posX, posY - ratioHeight/4, ratioWidth, ratioHeight/4);
        } else if (dir == 3) {
            this.fieldOfAction.setBounds(posX, posY + ratioHeight, ratioWidth, ratioHeight/4);
        }
    }

    public boolean targetSpotted(Character target) {
        boolean spotted;
        if (this.fieldOfAction.intersects(target.getHitbox())) {
            spotted = true;
        } else {
            spotted = false;
        }
        return spotted;
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

	private int collidesOnSide(Collidable collidable) {

		Rectangle box = collidable.getHitbox();
		int edge = 0;
		if (this.rbot.intersects(box)) {
			edge = 2;
		} else if (this.rtop.intersects(box)) {
			edge = 8;
		} else if (this.rleft.intersects(box)) {
			edge = 4;
		} else if (this.rright.intersects(box)) {
			edge = 6;
		}
		return edge;
	}

	public void goBack(Collidable collidable) {
		int edge = collidesOnSide(collidable);
		Rectangle box = collidable.getHitbox();
		int xTarget = (int) box.getX();
		int yTarget = (int) box.getY();
		if (edge == 6) {
			posX = xTarget - (ratioWidth);
		} else if (edge == 4) {
			posX = xTarget + (ratioWidth);
		} else if (edge == 2) {
			posY = yTarget - (ratioHeight);
		} else if (edge == 8) {
			posY = yTarget + (ratioHeight);
		}
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

	public void update() {
		setHitbox(this.posX, this.posY);
		rtop.setBounds(this.posX + 10, this.posY, 20, 10);
		rbot.setBounds(this.posX + 10, this.posY + 30, 20, 10);
		rleft.setBounds(this.posX, this.posY + 10, 10, 20);
		rright.setBounds(this.posX + 30, this.posY + 10, 10, 20);
		this.setFieldOfAction();
		notifyObserver(game);
		regenerateMana(1);
	}

	@Override
	public void notifyObserver(Observer observer) {
		observer.update();
	}

	@Override
	public void run() {
		while (!dead) {
			try {
				update();
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

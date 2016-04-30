package Model;

import java.awt.*;

public class Player extends Character {

	private Rectangle hitBox;
	private Rectangle rtop;
	private Rectangle rbot;
	private Rectangle rleft;
	private Rectangle rright;
	private boolean readyToAttack = true;

	private Thread thread;
	private Game game;

	public Player(int x, int y, int hp, Game game) {
		this.setPosX(x);
		this.setPosY(y);
		this.setSpeed(4);

		this.hitBox = new Rectangle(this.posX, this.posY, sizeSquare, sizeSquare);
		this.rtop = new Rectangle(this.posX + 10, this.posY, 20, 10);
		this.rbot = new Rectangle(this.posX + 10, this.posY + 30, 20, 10);
		this.rleft = new Rectangle(this.posX, this.posY + 10, 10, 20);
		this.rright = new Rectangle(this.posX + 30, this.posY + 10, 10, 20);

		this.setMaxHealth(hp);
		this.setHealth(hp);
		this.game = game;
		this.thread = new Thread(this);
		this.thread.start();
	}

	@Override
	public Rectangle getHitbox() {
		return hitBox;
	}

	@Override
	public void setHitbox(int x, int y) {
		this.hitBox.setBounds(x, y, sizeSquare, sizeSquare);
	}

	@Override
	public boolean collides(Collidable collidable) {
		boolean collision;
		Rectangle box = collidable.getHitbox();
		if (this.hitBox.intersects(box)) {
			collision = true;
		} else {
			collision = false;
		}
		return collision;
	}

	@Override
	public int collidesWith(Collidable collidable) {
		int edge = 0;
		Rectangle box = collidable.getHitbox();
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

	@Override
	public void getDamage(int damage) {
		int hp = this.getHealth();
		this.setHealth(hp - damage);
	}

	public void attack() {
		game.addProjectile(posX, posY, dir, speed * 3, 10);
	}

	@Override
	public void applyCollision(Collidable collidable, int edge) {
		
	}
	
	public int collidesWith2(Rectangle box) {
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

	public void goBack(Rectangle hitBox) {
		int edge = collidesWith2(hitBox);
		int xTarget = (int) hitBox.getX();
		int yTarget = (int) hitBox.getY();
		
		if (edge == 6) {
			posX = xTarget - (sizeSquare - 1);
		} else if (edge == 4) {
			posX = xTarget + (sizeSquare - 1);
		} else if (edge == 2) {
			posY = yTarget - (sizeSquare - 1);
		} else if (edge == 8) {
			posY = yTarget + (sizeSquare - 1);
		}
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

	public Rectangle getHitBox() {
		return hitBox;
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

	public boolean isReadyToAttack() {
		return readyToAttack;
	}

	public void setReadyToAttack(boolean readyToAttack) {
		this.readyToAttack = readyToAttack;
	}
}
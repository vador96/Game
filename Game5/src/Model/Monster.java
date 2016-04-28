package Model;

import java.awt.*;
import java.util.Random;

public class Monster extends Character {

	private Rectangle hitBox;
	private Rectangle rtop;
	private Rectangle rbot;
	private Rectangle rleft;
	private Rectangle rright;
	private Rectangle fieldOfView;
	private int vision = 100;
	protected Random random;

	private Thread thread;
    private Game game;

	public Monster(int x, int y, int hp, Game game) {
		this.setPosX(x);
		this.setPosY(y);
		this.setSpeed(1);
		this.hitBox = new Rectangle(posX, posY, sizeSquare, sizeSquare);
		this.rtop = new Rectangle(this.posX + 10, this.posY, 20, 10);
		this.rbot = new Rectangle(this.posX + 10, this.posY + 30, 20, 10);
		this.rleft = new Rectangle(this.posX, this.posY + 10, 10, 20);
		this.rright = new Rectangle(this.posX + 30, this.posY + 10, 10, 20);
		this.fieldOfView = new Rectangle(posX - vision, posY - vision, 3 * vision, 3 * vision);
		this.setHealth(hp);
		random = new Random();
        this.game = game;
        this.thread = new Thread(this);
        this.thread.start();
	}

	@Override
	public Rectangle getHitbox() {
		return hitBox;
	}

	@Override
	public void setHitBox(int x, int y) {
		this.hitBox.setBounds(x, y, sizeSquare, sizeSquare);
	}

	public Rectangle getFieldOfView() {
		return fieldOfView;
	}

	public void setFieldOfView(int x, int y) {
		this.fieldOfView.setBounds(x - vision, y - vision, 3 * vision, 3 * vision);
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

	@Override
	public void applyCollision(Collidable collidable, int edge) {
		int xTarget = (int) collidable.getHitbox().getX();
		int yTarget = (int) collidable.getHitbox().getY();

		if (collidable instanceof Block || collidable instanceof Monster) {
			if (edge == 6) {
				posX = xTarget - sizeSquare;
			} else if (edge == 4) {
				posX = xTarget + sizeSquare;
			} else if (edge == 2) {
				posY = yTarget - sizeSquare;
			} else if (edge == 8) {
				posY = yTarget + sizeSquare;
			}
		} else if (collidable instanceof Player) {
			/*
			 * if (edge == 6) { move(0,0); } else if (edge == 4) { move(0,0); }
			 * else if (edge == 2) { move(0,0); } else if (edge == 8) {
			 * move(0,0); }
			 */
		}
	}

	public void update() {
        lookForPlayer(game);
		setHitBox(this.posX, this.posY);
		rtop.setBounds(this.posX + 10, this.posY, 20, 10);
		rbot.setBounds(this.posX + 10, this.posY + 30, 20, 10);
		rleft.setBounds(this.posX, this.posY + 10, 10, 20);
		rright.setBounds(this.posX + 30, this.posY + 10, 10, 20);
		setFieldOfView(this.posX, this.posY);
        notifyObserver(game);
	}

	public void lookForPlayer(Game game) {
        Player target = game.getPlayers().get(0);
		if (this.fieldOfView.intersects(target.getHitbox())) {
			huntTarget(target);
		} else {
			patrol();
		}
	}

	private void huntTarget(Player target) {
		int x = target.getPosX();
		int y = target.getPosY();
		int dx = 0;
		int dy = 0;

		if (posX <= x - sizeSquare) {
			dx = 1;
		} else if (posY <= y - sizeSquare) {
			dy = 1;
		} else if (posX >= x + sizeSquare) {
			dx = -1;
		} else if (posY >= y + sizeSquare) {
			dy = -1;
		} else if (posX == x - (sizeSquare - 1) && posY < y) {
			dy = 1;
		} else if (posX == x + (sizeSquare - 1) && posY < y) {
			dy = 1;
		} else if (posX == x - (sizeSquare - 1) && posY > y) {
			dy = -1;
		} else if (posX == x + (sizeSquare - 1) && posY > y) {
			dy = -1;
		} else if (posY == y - (sizeSquare - 1) && posX < x) {
			dx = 1;
		} else if (posY == y + (sizeSquare - 1) && posX < x) {
			dx = 1;
		} else if (posY == y - (sizeSquare - 1) && posX > x) {
			dx = -1;
		} else if (posY == y + (sizeSquare - 1) && posX > x) {
			dx = -1;
		}
		move(dx, dy);
	}

	private void patrol() {
		if (random.nextInt(10) == 7) {
			dir = random.nextInt(4);
		}

		if (dir == 2) {
			move(1, 0);
		} else if (dir == 0) {
			move(-1, 0);
		} else if (dir == 3) {
			move(0, 1);
		} else if (dir == 1) {
			move(0, -1);
		} else if (dir == 4) {
			move(0, 0);
		}
	}

    @Override
    public void run() {
        while (!dead) {
            try {
                update();
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void notifyObserver(Observer observer) {
        observer.update();
    }
}

package Model;

import java.awt.*;
import java.util.Random;

public class Monster extends Character {

	private Rectangle fieldOfView;
	private int vision = 100;

	public Monster(int x, int y, int speed, int hp, Game game) {
		super(x, y, speed, hp, game);
		this.fieldOfView = new Rectangle(posX - vision, posY - vision, 3 * vision, 3 * vision);
	}

	public Rectangle getFieldOfView() {
		return fieldOfView;
	}

	public void setFieldOfView(int x, int y) {
		this.fieldOfView.setBounds(x - vision, y - vision, 3 * vision, 3 * vision);
	}

	public void update() {
		lookForPlayer(game);
		setHitbox(this.posX, this.posY);
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

		if (posX < x - sizeSquare) {
			dx = 1;
		} else if (posY < y - sizeSquare) {
			dy = 1;
		} else if (posX > x + sizeSquare) {
			dx = -1;
		} else if (posY > y + sizeSquare) {
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
		Random random = new Random();
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
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void notifyObserver(Observer observer) {
		observer.update();
	}


	@Override
	public void applyCollisionOn(Monster monster) {
        monster.goBack(this);

	}
	@Override
	public void applyCollisionOn(Player player) {
        player.goBack(this);
        player.getDamage(10);

	}

    @Override
    public void applyCollisionOn(Projectile projectile) {
        this.goBack(projectile);
    }

    @Override
	public void applyCollisionOn(Block block) {
        this.goBack(block);
	}

	@Override
	public void acceptCollision(Collision collision) {
		collision.applyCollisionOn(this);
	}
}

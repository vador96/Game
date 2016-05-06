package Model;

import java.awt.*;
import java.util.Random;

public class Monster extends Character {

	private int vision = 100;
	private int attack = 50;
	private int manaAttack = 100;

	private Rectangle fieldOfView;

	public Monster(int x, int y, int speed, int hp, int mana, Game game) {
		super(x, y, speed, hp, mana, game);
		this.fieldOfView = new Rectangle(posX - vision, posY - vision, 3 * vision, 3 * vision);
	}

	public Rectangle getFieldOfView() {
		return fieldOfView;
	}

	public void setFieldOfView(int x, int y) {
		this.fieldOfView.setBounds(x - vision, y - vision, 3 * vision, 3 * vision);
	}

	private void lookForPlayer(Game game) {
		Player target = game.getPlayers().get(0);
		if (this.fieldOfView.intersects(target.getHitbox())) {
			huntTarget(target);
		} else {
			patrol();
		}
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

	private void huntTarget(Player target) {
		int x = target.getPosX();
		int y = target.getPosY();
		int dx = 0;
		int dy = 0;

		if (posX < x - ratioWidth) {
			dx = 1;
		} else if (posY < y - ratioHeight) {
			dy = 1;
		} else if (posX > x + ratioWidth) {
			dx = -1;
		} else if (posY > y + ratioHeight) {
			dy = -1;
		} else if (posX == x - (ratioWidth - 1) && posY < y) {
			dy = 1;
		} else if (posX == x + (ratioWidth - 1) && posY < y) {
			dy = 1;
		} else if (posX == x - (ratioWidth - 1) && posY > y) {
			dy = -1;
		} else if (posX == x + (ratioWidth - 1) && posY > y) {
			dy = -1;
		} else if (posY == y - (ratioHeight - 1) && posX < x) {
			dx = 1;
		} else if (posY == y + (ratioHeight - 1) && posX < x) {
			dx = 1;
		} else if (posY == y - (ratioHeight - 1) && posX > x) {
			dx = -1;
		} else if (posY == y + (ratioHeight - 1) && posX > x) {
			dx = -1;
		}
		move(dx, dy);
	}

	private void attack(Game game) {
		Player target = game.getPlayers().get(0);
		if (this.getMana() >= manaAttack && targetSpotted(target)) {
			System.out.println("monstre attaque " + attack);
			useMana(manaAttack);
			target.getDamage(attack);
		}
	}

	@Override
	public void applyCollisionOn(Monster monster) {
		monster.goBack(this);

	}

	@Override
	public void applyCollisionOn(Player player) {
		player.goBack(this);
		player.getDamage(1);
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

	@Override
	public void applyCollisionOn(Potion potion) {
		// TODO Auto-generated method stub

	}

	public void update() {
		super.update();
		lookForPlayer(game);
		setFieldOfView(this.posX, this.posY);
		this.attack(game);
	}

	@Override
	public void notifyObserver(Observer observer) {
		observer.update();
	}

}

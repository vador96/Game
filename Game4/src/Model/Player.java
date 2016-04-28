package Model;

import java.awt.*;

public class Player extends Character {

	private Rectangle hitBox;
	private Rectangle rtop;
	private Rectangle rbot;
	private Rectangle rleft;
	private Rectangle rright;

	public Player(int x, int y, int hp) {
		this.setPosX(x);
		this.setPosY(y);

		this.hitBox = new Rectangle(this.posX, this.posY, 50, 50);
		this.rtop = new Rectangle(this.posX + 10, this.posY, 20, 10);
		this.rbot = new Rectangle(this.posX + 10, this.posY + 30, 20, 10);
		this.rleft = new Rectangle(this.posX, this.posY + 10, 10, 20);
		this.rright = new Rectangle(this.posX + 30, this.posY + 10, 10, 20);

		this.setHealth(hp);
	}

	@Override
	public Rectangle getHitbox() {
		return hitBox;
	}

	@Override
	public void setHitBox(int x, int y) {
		this.hitBox.setBounds(x, y, sizeSquarre, sizeSquarre);
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
	public void applyCollision(Collidable collidable, int edge) {
		int xTarget = (int) collidable.getHitbox().getX();
		int yTarget = (int) collidable.getHitbox().getY();

		if (collidable instanceof Block) {
			if (edge == 6) {
				posX = xTarget - (sizeSquarre - 1);
			} else if (edge == 4) {
				posX = xTarget + (sizeSquarre - 1);
			} else if (edge == 2) {
				posY = yTarget - (sizeSquarre - 1);
			} else if (edge == 8) {
				posY = yTarget + (sizeSquarre - 1);
			}
		} else if (collidable instanceof Monster /*
													 * || collidable instanceof
													 * PojectileOfMonster
													 */) {
			// Ce srait cool de coder des attaques à distance des monstres, ici
			// c'est juste ajouter "collidable instanceof PojectileOfMonster" et
			// les monstres ont les meme objets projectiles
			// Pour l'instant j'ai fait collision sans répulsion mais quand le
			// montre te colle tu perds de la vie

			if (edge == 6) {
				posX = xTarget - (sizeSquarre - 1);
			} else if (edge == 4) {
				posX = xTarget + (sizeSquarre - 1);
			} else if (edge == 2) {
				posY = yTarget - (sizeSquarre - 1);
			} else if (edge == 8) {
				posY = yTarget + (sizeSquarre - 1);
			}
		}
	}

	public void update() {
		move(speedX, speedY);
		setHitBox(this.posX, this.posY);
		rtop.setBounds(this.posX + 10, this.posY, 20, 10);
		rbot.setBounds(this.posX + 10, this.posY + 30, 20, 10);
		rleft.setBounds(this.posX, this.posY + 10, 10, 20);
		rright.setBounds(this.posX + 30, this.posY + 10, 10, 20);
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

}
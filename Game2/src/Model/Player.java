package Model;

import java.awt.*;
import java.util.ArrayList;

public class Player extends Character {

	private Rectangle hitBox;
	private Rectangle rtop;
	private Rectangle rbot;
	private Rectangle rleft;
	private Rectangle rright;

	public int munitions = 15;

	public Player(int x, int y, int hp) {
		this.setPosX(x);
		this.setPosY(y);
		this.setSpeed(4);

		this.hitBox = new Rectangle(this.posX, this.posY, 50, 50);
		this.rtop = new Rectangle(this.posX + 20, this.posY, 10, 10);
		this.rbot = new Rectangle(this.posX + 20, this.posY + 40, 10, 10);
		this.rleft = new Rectangle(this.posX, this.posY + 20, 10, 10);
		this.rright = new Rectangle(this.posX + 40, this.posY + 20, 10, 10);

		this.setHealth(hp);
	}

	@Override
	public Rectangle getHitbox() {
		return hitBox;
	}

	@Override
	public void setHitBox(int x, int y) {
		this.hitBox.setRect(x, y, 50, 50);
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

		if (edge == 6) {
			posX = xTarget - 50;
		} else if (edge == 4) {
			posX = xTarget + 50;
		} else if (edge == 2) {
			posY = yTarget - 50;
		} else if (edge == 8) {
			posY = yTarget + 50;
		}
	}

    @Override
    public void getDamage(int damage) {
        int hp = this.getHealth();
        this.setHealth(hp - damage);
    }

    public boolean attack() {
        boolean attack;
        if (munitions != 0) {
            munitions -= 1;
            attack = true;
        } else {
            attack = false;
        }
        return attack;
    }

    public void reload() {
        if (munitions == 0) {
            munitions = 15;
        }
    }

	public void update() {
		moveDown = true;
		moveLeft = true;
		moveRight = true;
		moveUp = true;
		move(speedX, speedY);
		setHitBox(this.posX, this.posY);
		rtop.setRect(this.posX + 20, this.posY, 10, 10);
		rbot.setRect(this.posX + 20, this.posY + 40, 10, 10);
		rleft.setRect(this.posX, this.posY + 20, 10, 10);
		rright.setRect(this.posX + 40, this.posY + 20, 10, 10);
        reload();
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
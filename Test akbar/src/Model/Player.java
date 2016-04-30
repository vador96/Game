package Model;

import java.awt.Rectangle;

public class Player extends Character {

	private boolean readyToAttack = true;

	public Player(int x, int y, int speed, int hp, Game game) {
		super(x,y,speed,hp,game);
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

	public boolean isReadyToAttack() {
		return readyToAttack;
	}

	public void setReadyToAttack(boolean readyToAttack) {
		this.readyToAttack = readyToAttack;
	}
	
	public void update(){
		super.update();
		move(speedX,speedY);
	}

	@Override
	public void applyCollisionOn(Collidable collidable) {
		collidable.goBack(this.hitbox);
	}
	
	public void getDamageFromMonster(int damage) {
		getDamage(1);
	}

	@Override
	public void getDamageFromPlayer(int damage) {
		
	}

	public int collidesWith(Rectangle box) {
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

	@Override
	public void goBack(Rectangle hitbox) {
		int edge = collidesWith(hitbox);
		
		int xTarget = (int) hitbox.getX();
		int yTarget = (int) hitbox.getY();
		if (edge == 6) {
			posX = xTarget - (sizeSquare );
		} else if (edge == 4) {
			posX = xTarget + (sizeSquare);
		} else if (edge == 2) {
			posY = yTarget - (sizeSquare );
		} else if (edge == 8) {
			posY = yTarget + (sizeSquare );
		}
	}
}
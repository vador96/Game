package Model;

import java.awt.*;

public class Player extends Character{

	private boolean readyToAttack = true;
	
	public Player(int x, int y, int speed, int hp, Game game) {
		super(x,y,speed,hp,game);
	}

	public void getDamage(int damage) {
		int hp = this.getHealth();
		this.setHealth(hp - damage);
	}

	public void attack() {
		game.addProjectile(posX, posY, dir, speed * 3, 10);
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

	public boolean isReadyToAttack() {
		return readyToAttack;
	}

	public void setReadyToAttack(boolean readyToAttack) {
		this.readyToAttack = readyToAttack;
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

	@Override
	public void applyCollisionOn(Collidable collidable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getDamageFromPlayer(int damage) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void getDamageFromMonster(int damage) {
		getDamage(damage);
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
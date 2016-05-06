package Controller;

import Model.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {

	private Game game;

	public Controller(Game game) {
		this.game = game;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent event) {

		int key = event.getKeyCode();

		switch (key) {
		case KeyEvent.VK_RIGHT:
			game.movePlayerRight();
			break;
		case KeyEvent.VK_LEFT:
			game.movePlayerLeft();
			break;
		case KeyEvent.VK_DOWN:
			game.movePlayerDown();
			break;
		case KeyEvent.VK_UP:
			game.movePlayerUp();
			break;
		case KeyEvent.VK_SPACE:
			if (game.getPlayers().get(0).isReadyToAttack()) {
				game.playerRangedAttack();
				game.getPlayers().get(0).setReadyToAttack(false);
			}
			break;
		case KeyEvent.VK_H:
			game.playerUseHealPotion();
			break;
		case KeyEvent.VK_M:
			game.playerUseManaPotion();
		case KeyEvent.VK_A:
			game.switchWeapon();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		switch (key) {
		case KeyEvent.VK_RIGHT:
			game.stopRight();
			break;
		case KeyEvent.VK_LEFT:
			game.stopLeft();
			break;
		case KeyEvent.VK_DOWN:
			game.stopDown();
			break;
		case KeyEvent.VK_UP:
			game.stopUp();
			break;
		case KeyEvent.VK_SPACE:
			game.getPlayers().get(0).setReadyToAttack(true);
			break;
		}
	}
}

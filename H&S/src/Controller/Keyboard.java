package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Model.Game;

public class Keyboard implements KeyListener {
	private Game game;

	public Keyboard(Game game) {
		this.game = game;
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
			game.setPlayerAttacking(true);
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		switch (key) {
		case KeyEvent.VK_RIGHT:
			game.movePlayerStop();
			break;
		case KeyEvent.VK_LEFT:
			game.movePlayerStop();
			break;
		case KeyEvent.VK_DOWN:
			game.movePlayerStop();
			break;
		case KeyEvent.VK_UP:
			game.movePlayerStop();
			break;
		case KeyEvent.VK_SPACE:
            game.setPlayerAttacking(false);
			break;
		}
	}
}

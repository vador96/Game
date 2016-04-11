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
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
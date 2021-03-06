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
            	game.playerAttack();
                System.out.println("hit");
                break;
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_RIGHT:
                game.stopPlayer();
                break;
            case KeyEvent.VK_LEFT:
                game.stopPlayer();
                break;
            case KeyEvent.VK_DOWN:
                game.stopPlayer();
                break;
            case KeyEvent.VK_UP:
                game.stopPlayer();
                break;
            case KeyEvent.VK_SPACE:
                break;
        }
    }
}

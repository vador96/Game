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
                System.out.println("RIGHT");
                game.movePlayerRight();
                break;
            case KeyEvent.VK_LEFT:
                System.out.println("LEFT");
                game.movePlayerLeft();
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("DOWN");
                game.movePlayerDown();
                break;
            case KeyEvent.VK_UP:
                System.out.println("UP");
                game.movePlayerUp();
                break;
            case KeyEvent.VK_SPACE:
                System.out.println("HIT");
                break;
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {

    }
}

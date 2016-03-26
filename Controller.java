package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import Model.Game;

public class Controller implements KeyListener{

    private Game game;

    public Controller(Game game) {
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        int key = event.getKeyCode();

        if (key == KeyEvent.VK_RIGHT) {
            game.movePlayerRight();
        }
        else if (key == KeyEvent.VK_LEFT) {
            game.movePlayerLeft();
        }
        else if (key == KeyEvent.VK_UP) {
            game.movePlayerUp();
        }
        else if (key == KeyEvent.VK_DOWN) {
            game.movePlayerDown();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}


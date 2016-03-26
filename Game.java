package Model;

import View.Window;
import java.util.ArrayList;

public class Game {
    private Link link;
    private ArrayList<Character> people = new ArrayList<Character>();
    private ArrayList<Decor> decors = new ArrayList<Decor>();
    private Window window;

    public Game(Window window) {
        this.window = window;
    }

    public void movePlayerRight() {
        link.move(1,0);
        // repaint the map
    }
    public void movePlayerLeft() {
        link.move(-1,0);
    }
    public void movePlayerUp() {
        link.move(0,-1);
    }
    public void movePlayerDown() {
        link.move(0, 1);
    }


}

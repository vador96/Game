package Model;

import View.Window;

import javax.swing.*;
import java.util.ArrayList;

public class Game {

    private Level level;
    private ArrayList<Player> players = new ArrayList<Player>();
    private Window window;
    // faire une liste des levels
    public Game(Window window) {
        this.window = window;
        this.level = new Level();
        this.players.add(new Player(100,10,10,4));
        this.level.addPlayer(players.get(0));
        window.draw(this.level.getMap());
    }

    public Level getLevel() {
        return this.level;
    }

    public void setLevel(Level lvl) {
        this.level = lvl;
    }

    public void movePlayerRight() {
        this.players.get(0).move(1,0);
        window.draw(this.level.getMap());
    }
    public void movePlayerLeft() {
        this.players.get(0).move(-1,0);
        window.draw(this.level.getMap());

    }
    public void movePlayerDown() {
        this.players.get(0).move(0,1);
        window.draw(this.level.getMap());

    }
    public void movePlayerUp() {
        this.players.get(0).move(0,-1);
        window.draw(this.level.getMap());
    }



}

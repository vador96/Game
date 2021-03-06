package Model;

import View.Window;

import java.util.ArrayList;

public class Game implements Runnable {

    private Window window;
    private Thread thread;
    private Level level;
    private char[][] map;
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Monster> monsters = new ArrayList<>();
    private ArrayList<Block> blocks = new ArrayList<>();
    private ArrayList<Collidable> collidables = new ArrayList<>();

    public Game(Window window, Level level) {
        this.window = window;
        this.level = level;
        this.map = level.mapMatrix;
        players.add(new Player(1 ,1, 100)); // pos < dimension matrice
        this.generateCollidables();
        this.window.draw(map, players, monsters);
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                players.get(0).update();
                moveMonsters();
                checkCollision();
                window.draw(map, players, monsters);
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void generateCollidables() {
        for (int i = 0; i<map.length; i++) {
            for (int j = 0; j<map[0].length; j++) {
                char item = map[i][j];
                if(item == '1') {
                    Block block = new Block(i, j);
                    blocks.add(block);
                    collidables.add(block);
                } else if (item == '2') {
                    Monster monster = new Monster(i, j, 100);
                    monsters.add(monster);
                    collidables.add(monster);
                }
            }

        }
    }

    public void checkCollision() {
        for (Collidable collidable : collidables) {
            if (players.get(0).collides(collidable)) {
                players.get(0).applyCollision(collidable);
            }
        }
    }

    public void movePlayerRight() {
        players.get(0).move(1,0);
    }
    public void movePlayerLeft() {
        players.get(0).move(-1,0);
    }
    public void movePlayerUp() {
        players.get(0).move(0,-1);
    }
    public void movePlayerDown() {
        players.get(0).move(0,1);
    }
    public void stopPlayer() {
        players.get(0).move(0,0);
    }

    public void moveMonsters() {
        for (Monster monster : monsters) {
            monster.update();
            monster.lookForPlayer(players.get(0));
        }
    }
}

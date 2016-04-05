package Model;

import java.util.ArrayList;

public class Level {
    private int length = 100;
    private int width = 100;
    public static ArrayList<Monster> monsters = new ArrayList<Monster>();
    public static ArrayList<Player> players = new ArrayList<Player>();  // PLAYER APPARTIENT A GAME ET PAS A LEVEL
    public ArrayList<Block> blocks = new ArrayList<Block>();

    public Level() {
        this.buildLevel();
    }

    private void buildLevel() {

        for(int i = 0; i < length; i++){
            blocks.add(new Block(i,0));
            blocks.add(new Block(0,i));
            blocks.add(new Block(i, length-1));
            blocks.add(new Block(length-1, i));
        }

        for (int i = 0; i<10; i++) {
            monsters.add(new Monster(100,1+7*i,1+3*i,3));
        }

        // Creating one Player at position (1,1)
        //players.add(new Player(100,10,10,4));
    }

    public static ArrayList<Player> getPlayers() {
        return players;
    }
    public static ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public int[][] getMap() {

        int[][] mapMatrix = new int[this.length][this.width];

        for (int i = 0; i<this.length; i++) {
            for (int j = 0; j<this.width; j++) {
                mapMatrix[i][j] = 0; // 0 = BlockVide
            }
        }
        for (Monster monster : this.monsters) {
            int x = monster.getPosX();
            int y = monster.getPosY();
            mapMatrix[x][y] = 1; // envisager les differents types de monstres + types de Blocks
        }
        for (Player player : this.players) {
            int x = player.getPosX();
            int y = player.getPosY();
            mapMatrix[x][y] = 2;
        }
        return mapMatrix;
    }


}
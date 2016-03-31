package Model;
import View.Window;

import java.util.ArrayList;

public class Game {
	private static ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<Block> blocks = new ArrayList<Block>();
	private Window window;
	private int size = 20;
	
	public Game(Window window){
		this.window = window;
		// Map building 
		for(int i = 0; i < size; i++){
			blocks.add(new Block(i,0));
			blocks.add(new Block(0,i));
			blocks.add(new Block(i, size-1));
			blocks.add(new Block(size-1, i));
		}
		
		// Creating one Player at position (1,1)
		players.add(new Player(10,10,4));

		window.draw(this.getMap());
	}
	
	public static ArrayList<Player> getPlayers(){
		return players;
	}
	
	public void movePlayerLeft(){
		players.get(0).move(-1, 0);
		window.draw(this.getMap());
	}
	public void movePlayerRight(){
		players.get(0).move(1,0);	
		window.draw(this.getMap());	
	}
	public void movePlayerDown(){
		players.get(0).move(0, 1);
		window.draw(this.getMap());
	}
	public void movePlayerUp(){
		players.get(0).move(0, -1);
		window.draw(this.getMap());
	}
	
	public int[][] getMap(){
		int[][] map = new int[this.size][this.size];
		for(int i = 0; i<this.size; i++)
			for(int j = 0; j<this.size; j++)
				map[i][j] = 0;
		
		for(Player player : players){
			int x = player.getPosX();
			int y = player.getPosY();
			map[x][y] = 2;
		}
		for(Block block : blocks){
			int x = block.getPosX();
			int y = block.getPosY();
			map[x][y] = 1;
		}
		System.out.println(map);
		return map;
	}
}

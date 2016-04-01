package Model;
import View.Window;

import java.util.*;

public class Game {
	private static ArrayList<Player> players = new ArrayList<Player>();
	private static ArrayList<Monster> monsters = new ArrayList<Monster>();
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
		players.add(new Player(100,10,10,4));
		for(int i = 0 ; i<20 ; i++){
		Random generator = new Random();
		monsters.add(new Monster(50,1+generator.nextInt(18),1+generator.nextInt(18),1+generator.nextInt(4)));
		}
		window.draw(this.getMap());
	}
	
	public static ArrayList<Player> getPlayers(){
		return players;
	}
	
	public static ArrayList<Monster> getMonsters(){
		return monsters;
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
	public void moveMonsters(ArrayList<Monster> monsters){
		for(Monster monster : monsters){
			Random generator = new Random();
			monster.move(generator.nextInt(3)-1,generator.nextInt(3)-1);
			window.draw(this.getMap());
		}
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
		for(Monster monster : monsters){
			int x = monster.getPosX();
			int y = monster.getPosY();
			map[x][y] = 3;
		}
		System.out.println(map);
		return map;
	}
}

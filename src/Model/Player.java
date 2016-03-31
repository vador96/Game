package Model;

public class Player {
	private int posX;
	private int posY;
	private int direction;
	
	public Player(int X, int Y, int direction){
		this.posX = X;
		this.posY = Y;
		this.direction = direction;
	}
	
	public int getPosX(){
		return this.posX;
	}
	
	public int getPosY(){
		return this.posY;
	}
	
	public String getDirection(){
		String direction = new String();
		int dir = this.direction;
		if(dir == 1){
			direction = "Left";
		}else if(dir == 2){
			direction = "Up";
		}else if(dir == 3){
			direction = "Right";
		}else if(dir == 4){
			direction = "Down";
		}
		return direction;
	}
	
	public void setDirection(int direction){
		this.direction = direction;
	}
	
	public void move(int X, int Y){
		this.posX = this.posX + X;
		this.posY = this.posY + Y;
		
		if(X > 0){
			this.direction = 3;
		}else if (X < 0) {
			this.direction = 1;
		}else if (Y > 0) {
			this.direction = 4;
		}else if (Y < 0){
			this.direction = 2;
		}
	}
}

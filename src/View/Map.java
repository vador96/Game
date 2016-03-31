package View;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;
import Model.*;

public class Map extends JPanel {
	private int[][] mapMatrix;

	public Map(){
		this.setFocusable(true);
		this.requestFocusInWindow();
	}
	
	public void paint(Graphics g) {
		if(mapMatrix == null){
		}else{
			for(int i = 0; i<mapMatrix.length; i++){
				for(int j = 0; j<mapMatrix.length; j++){
					int x = i;
					int y = j;
					int color = mapMatrix[i][j];
					
					if(color == 0 || color == 2){
						g.setColor(Color.GRAY);
						g.fillRect(x*50, y*50, 48, 48); 
						g.setColor(Color.BLACK);
						g.drawRect(x*50, y*50, 48, 48);

					}else if(color == 1){
						g.setColor(Color.DARK_GRAY);
						g.fillRect(x*50, y*50, 48, 48); 
						g.setColor(Color.BLACK);
						g.drawRect(x*50, y*50, 48, 48);}}}
						
			for(Player player : Game.getPlayers()){
				int posX = player.getPosX();
				int posY = player.getPosY();
				String direction = player.getDirection();
				Image img = Toolkit.getDefaultToolkit().getImage("res/LinkRun"+direction+"1.png");
				g.drawImage(img, posX*50, posY*50, this);
			}
		}
	}
	
	public void setMapMatrix(int[][] mapMatrix){
		this.mapMatrix = mapMatrix;
		this.repaint();
	}
}
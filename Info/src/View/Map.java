package View;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Map extends JPanel {

	private static final long serialVersionUID = 1L;
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
					
					if(color == 0){
						g.setColor(Color.GRAY);
					}else if(color == 1){
						g.setColor(Color.BLUE);
					}else if(color == 2){
						g.setColor(Color.RED);
					}

					g.fillRect(x*50, y*50, 48, 48); 

					g.setColor(Color.BLACK);
					g.drawRect(x*50, y*50, 48, 48); 
					System.out.print(color);
					System.out.print(" ");
				}
				System.out.println("");
			}
		}
	}
	
	public void setMapMatrix(int[][] mapMatrix){
		this.mapMatrix = mapMatrix;
		this.repaint();
	}

}

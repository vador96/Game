package View;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import Model.Game;

public class Map extends JPanel {

	private static final long serialVersionUID = 1L;
	private int[][] mapMatrix;

	public Map() {
		this.setFocusable(true);
		this.requestFocusInWindow();
	}

	public void paint(Graphics g) {
		if (mapMatrix == null) {
		} else {
			for (int i = 0; i < mapMatrix.length; i++) {
				for (int j = 0; j < mapMatrix.length; j++) {
					int x = i;
					int y = j;
					int type = mapMatrix[i][j];
					Image tile = null;
					
					System.out.println(mapMatrix.length);

					if (type == 2) {
						tile = Game.tilegrassBot;
					} else if (type == 4) {
						tile = Game.tilegrassLeft;
					} else if (type == 6) {
						tile = Game.tilegrassRight;
					} else if (type == 8) {
						tile = Game.tilegrassTop;
					} else if (type == 5){
						tile = Game.tiledirt;
					}
					g.drawImage(tile, x * 40, y * 40, this);
				}
			}
		}
	}

	public void setMapMatrix(int[][] mapMatrix) {
		this.mapMatrix = mapMatrix;
		this.repaint();
	}

}

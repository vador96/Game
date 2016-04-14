package View;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Map extends JPanel {
	private static final long serialVersionUID = 1L;
	private int[][] mapMatrix;
	private int playerPosX;
	private int playerPosY;

	public Map() {
		this.setFocusable(true);
		this.requestFocusInWindow();

		/*Timer timer = new Timer(10, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				repaint();
			}
		});
		timer.start();*/
	}

	public void paint(Graphics g) {
		if (mapMatrix == null) {
		} else {
			for (int i = 0; i < mapMatrix.length; i++) {
				for (int j = 0; j < mapMatrix.length; j++) {
					int x = i;
					int y = j;
					int color = mapMatrix[i][j];

					if (color == 0) {
						g.setColor(Color.GRAY);
						g.fillRect(x * 50, y * 50, 50, 50);
					} else if (color == 1) {
						g.setColor(Color.DARK_GRAY);
						g.fillRect(x * 50, y * 50, 50, 50);
					} else if (color == 3) {
						g.setColor(Color.RED);
						g.fillRect(x * 50, y * 50, 50, 50);
						g.setColor(Color.BLACK);
						g.drawRect(x * 50, y * 50, 50, 50);
					}

					g.setColor(Color.cyan);
					g.fillOval(playerPosX, playerPosY, 70, 70);
					g.setColor(Color.BLACK);
					g.drawOval(playerPosX, playerPosY, 70, 70);
				}
			}
		}
	}

	private void setMapMatrix(int[][] mapMatrix) {
		this.mapMatrix = mapMatrix;
	}

	private void setPlayerPos(int x, int y) {
		this.playerPosX = x;
		this.playerPosY = y;
	}
    public void refresh(int[][] mapMatrix, int x, int y) {
        this.setMapMatrix(mapMatrix);
        this.setPlayerPos(x,y);
        repaint();
    }
}

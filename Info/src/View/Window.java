package View;

import java.awt.Color;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Window {
	private Map map = new Map();

	public Window() {
		JFrame window = new JFrame("Game");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(975, 1000);
		window.setLocationRelativeTo(null);
		window.getContentPane().setBackground(Color.BLACK);
		window.getContentPane().add(this.map);
		window.setVisible(true);
	}

	public void draw(int[][] mapMatrix) {
		map.setMapMatrix(mapMatrix);
	}

	public void setKeyListener(KeyListener keyboard) {
		this.map.addKeyListener(keyboard);
	}
}

package View;

import java.awt.Color;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Window extends JFrame {

	private static final long serialVersionUID = 1L;
	private Map map = new Map();

	public Window() {
		this.setTitle("Akbar");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(0, 0, 1000, 1020);
		this.getContentPane().setBackground(Color.gray);
		this.getContentPane().add(this.map);
		this.setVisible(true);
	}
	
	public void draw(){
		
	}

	public void setKeyListener(KeyListener keyboard) {
		this.map.addKeyListener(keyboard);
	}
}
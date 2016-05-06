package View;

import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import Model.*;

public class Window extends JFrame implements Observer {
	
	private static final long serialVersionUID = 1L;
	
	public static final double WINDOW_WIDTH = 816.0;
	public static final double WINDOW_HEIGHT = 839.0;
	
	private Map map = new Map();

    public Window() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize((int)WINDOW_WIDTH,(int)WINDOW_HEIGHT);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().add(this.map);
        this.setVisible(true);
    }

    public void draw(char[][] mapMatrix, ArrayList<Player> players, ArrayList<Monster> monsters, ArrayList<Item> items, ArrayList<Projectile> projectiles) {
        map.refresh(mapMatrix, players, monsters, items, projectiles);
    }

    public void setKeyListener(KeyListener controller) {
        this.map.addKeyListener(controller);
    }

    @Override
    public void update() {
        this.map.repaint();
    }
}

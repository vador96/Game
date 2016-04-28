package View;

import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import Model.*;

public class Window extends JFrame implements Observer {
	
	private static final long serialVersionUID = 1L;
	
	private Map map = new Map();

    public Window() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1016,1039);
        this.setLocationRelativeTo(null);
        this.getContentPane().add(this.map);
        this.setVisible(true);
    }

    public void draw(char[][] mapMatrix, ArrayList<Player> players, ArrayList<Monster> monsters, ArrayList<Block> blocks, ArrayList<Projectile> projectiles) {
        map.refresh(mapMatrix, players, monsters, blocks, projectiles);
    }

    public void setKeyListener(KeyListener controller) {
        this.map.addKeyListener(controller);
    }

    @Override
    public void update() {
        this.map.repaint();
    }
}

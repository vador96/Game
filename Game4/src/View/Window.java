package View;

import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import Model.Block;
import Model.Monster;
import Model.Player;

public class Window extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private Map map = new Map();

    public Window() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,1000);
        this.setLocationRelativeTo(null);
        this.getContentPane().add(this.map);
        this.setVisible(true);
    }

    public void draw(char[][] mapMatrix, ArrayList<Player> players, ArrayList<Monster> monsters, ArrayList<Block> blocks) {
        map.refresh(mapMatrix, players, monsters, blocks);
    }

    public void setKeyListener(KeyListener controller) {
        this.map.addKeyListener(controller);
    }
}

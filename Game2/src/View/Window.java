package View;

import javax.swing.*;
import java.awt.event.KeyListener;
import Model.Monster;
import Model.Player;
import java.util.ArrayList;

public class Window extends JFrame {
    private Map map = new Map();

    public Window() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,1000);
        this.setLocationRelativeTo(null);
        this.getContentPane().add(this.map);
        this.setVisible(true);
    }

    public void draw(char[][] mapMatrix, ArrayList<Player> players, ArrayList<Monster> monsters) {
        map.refresh(mapMatrix, players, monsters);
    }

    public void setKeyListener(KeyListener controller) {
        this.map.addKeyListener(controller);
    }
}

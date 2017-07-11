package View;

import Model.Game;

import javax.swing.*;
import java.awt.event.KeyListener;

public class Window extends JFrame{

    private Map map = new Map();

    public Window() {
        this.setTitle("Test Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,1000);
        this.setLocationRelativeTo(null);
        this.getContentPane().add(map);
        this.setVisible(true);
    }

    public void draw(int[][] mapMatrix) {
        map.setMapMatrix(mapMatrix);
    }

    public void setKeyListener(KeyListener controller){
        this.map.addKeyListener(controller);
    }


}

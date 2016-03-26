package View;

import javax.swing.*;

public class Window {
    private Map map = new Map();

    public Window() {
        JFrame window = new JFrame("Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(0,0,1000,1000); // attention taille ajustable
        window.getContentPane().add(this.map);
        window.setVisible(true);
    }

    public void draw(String[][] mapMatrix) {
        map.setMapMatrix(mapMatrix);
    }

}

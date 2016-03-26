package View;

import java.awt.Graphics;
import javax.swing.JPanel;

public class Map extends JPanel{
    private String[][] mapMatrix;

    public Map() {
        this.setFocusable(true);
        this.requestFocusInWindow(); // se rensigner
    }

    public void paint(Graphics g) {
        // dessine la map

    }

    public void setMapMatrix(String[][] mapMatrix) {
        this.mapMatrix = mapMatrix;
        this.repaint();

    }

}

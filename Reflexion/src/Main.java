import Controller.Controller;
import Model.Game;
import Model.Level;
import View.Window;

import java.util.ArrayList;

public class Main {

    private void loadlevels(String folderName, ArrayList<Level> levels) {
        for (int i =0; i<10; i++) {
            levels.add(new Level(String.format(folderName + "/game_%s", i)));
        }
    }

    public static void main(String[] args) {
        Window window = new Window();
        Level level = new Level("data/game_0.txt");
        Game game = new Game(window, level);
        Controller controller = new Controller(game);
        window.setKeyListener(controller);
    }
}

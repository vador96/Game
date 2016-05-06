import Controller.Controller;
import Model.Game;
import Model.Level;
import View.Window;

public class Main {

    public static void main(String[] args) {
        Window window = new Window();
        Level level = new Level("data/game_0.txt");
        Game game = new Game(window, level);
        Controller controller = new Controller(game);
        window.setKeyListener(controller);
    }
}
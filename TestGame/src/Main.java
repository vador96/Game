import Model.Game;
import View.Window;
import Controller.Controller;

public class Main {

    public static void main(String[] args) {
        Window window = new Window();
        Game game = new Game(window);
        Controller controller = new Controller(game);
        window.setKeyListener(controller);

    }
}

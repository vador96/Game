package Model;

import View.Window;

public class Game implements Runnable {
	private static Link link = new Link(0, 0, 10, 10);
	private Window window;
	private Thread t;
	private Level level;

	public Game(Window window) {
		this.window = window;
		t = new Thread(this);
		t.start();
	}

	public void run() {
		while (true) {
			link.update();
			window.draw(this.level);
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void movePlayerLeft() {
		link.move(-1, 0);
	}

	public void movePlayerRight() {
		link.move(1, 0);
	}

	public void movePlayerDown() {
		link.move(0, 1);
	}

	public void movePlayerUp() {
		link.move(0, -1);
	}

	public static Link getLink() {
		return link;
	}

	public static void setLink(Link link) {
		Game.link = link;
	}

	public Window getWindow() {
		return window;
	}

	public void setWindow(Window window) {
		this.window = window;
	}

	public Thread getT() {
		return t;
	}

	public void setT(Thread t) {
		this.t = t;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
}

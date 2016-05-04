package Model;

public class World {

    int x_level = 0;
    int y_level = 0;
    public int[][] matrix;
    public World() {
        this.loadWorld();
    }

    private void loadWorld() {
        this.matrix = new int[][]{{0, 1, 2, 3}, {4, 5, 6, 7}, {8, 9, 10, 11}, {12, 13, 14, 15}};
    }

    public int getLevel() {
        return this.matrix[x_level][y_level];
    }

    public void changeLevel(char direction) {
        if (direction == 'N') {
            goNorth();
        } else if (direction == 'S') {
            goSouth();
        } else if (direction == 'E') {
            goEast();
        } else if (direction == 'W') {
            goWest();
        }
    }

    public void goNorth() {
        x_level += 1;
    }
    public void goSouth() {
        x_level -= 1;
    }
    public void goEast() {
        y_level += 1;
    }
    public void goWest() {
        y_level -= 1;
    }

}

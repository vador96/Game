package Model;

public class Block {
    private int posX;
    private int posY;

    public Block(int X, int Y){
        this.posX = X;
        this.posY = Y;
    }

    public int getPosX(){
        return this.posX;
    }

    public int getPosY(){
        return this.posY;
    }
}
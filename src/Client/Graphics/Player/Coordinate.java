package Client.Graphics.Player;

import Server.GameClasses.Direction;

public class Coordinate {
    private int x,y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setCoordinates(int x,int y){
        this.x = x;
        this.y = y;
    }



}

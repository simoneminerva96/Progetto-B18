package Client.Graphics.Player;

import Server.GameClasses.Direction;

public class Coordinate {
    private int x,y;
    private final int MINMOVEMENT = 2;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }

    public int getY() { return y; }

    public void calculate(Coordinate minime,Coordinate massime,int die,Direction direction){

        if (direction == Direction.FORWARD) {
            checkForward(minime, massime, die);
        } else {
            checkBack(minime, massime, die);
        }
    }

    private Coordinate checkForward (Coordinate minime, Coordinate massime, int die) {

        for(int i = 0; i<die; i++) {
            if (y == minime.getY() && x < massime.getX()) {
                x += MINMOVEMENT;
            }
            else
                if (y < massime.getY() && x == massime.getX()) {
                y += MINMOVEMENT;
            } else
                if (y == massime.getY() && x > minime.getX()) {
                x -= MINMOVEMENT;
                } else
                    if (x == minime.getX() && y > minime.getY()) {
                    y -= MINMOVEMENT;
            }
        }
        return new Coordinate(x,y);
    }

    private Coordinate checkBack (Coordinate minime, Coordinate massime, int die) {

        for(int i = 0; i<die; i++) {
            if (x == massime.getX() && y > minime.getY()) {
                y -= MINMOVEMENT;
            }
            else
                if (y == minime.getY() && x > minime.getX()) {
                    x -= MINMOVEMENT;
                } else
                    if (x == minime.getX() && y < massime.getY()) {
                        y += MINMOVEMENT;
                    } else
                        if (y == massime.getY() && x < massime.getX()) {
                            x += MINMOVEMENT;
                        }
        }
        return new Coordinate(x,y);
    }

    public void setX(int x) { this.x = x; }

    public void setY(int y) { this.y = y; }
}

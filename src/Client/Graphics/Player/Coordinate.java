package Client.Graphics.Player;

import Server.GameClasses.Direction;

public class Coordinate {
    private int x,y;
    private final int MINMOVEMENT = 2;

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

    public void calculate(Coordinate minime,Coordinate massime,int die,Direction direction){
        /*int minx=minime.getX();
        int miny=minime.getY();
        int maxx=massime.getX();
        int maxy=massime.getY();
        int mul=1,confront,temp;*/

        if (direction == Direction.FORWARD) {
            checkForward(minime, massime, die);
        } else {
            checkYBack(minime, massime, die);
        }

        /*if(y==miny){
            if(direction==Direction.FORWARD){
                mul=-1;
                confront=maxx*mul;
                temp=(x+2*die)*mul;
            }else{
                confront=minx*mul;
                temp=(x-2*die)*mul;
            }
            if(temp<confront){
                temp=confront-temp;
                y+=temp;
                x=confront*mul;
            }else{
                x=temp*mul;
            }
        }else if(x==maxx){
            if(direction==Direction.FORWARD){
                mul=-1;
                confront=maxy*mul;
                temp=(y+2*die)*mul;
            }else{
                confront=miny*mul;
                temp=(y-2*die)*mul;
            }
            if(temp<confront){
                temp=confront-temp;
                x-=temp;
                y=confront*mul;
            }else{
                y=temp*mul;
            }
        }else if(y==maxy){
            if(direction==Direction.FORWARD){
                mul=-1;
                confront=minx*mul;
                temp=(x-2*die)*mul;
            }else{
                confront=miny*mul;
                temp=(x+2*die)*mul;
            }
            if(temp>confront){
                temp=temp-confront;
                x=confront*mul;
                y-=temp;
            }else{
                x=temp*mul;
            }
        }else if(x==minx){
            if(direction==Direction.FORWARD){
                mul=-1;
                confront=miny*mul;
                temp=(y-2*die)*mul;
            }else{
                confront=maxy*mul;
                temp=(y+2*die)*mul;
            }
            if(temp>confront){
                temp=temp-confront;
                y=confront*mul;
                x+=temp;
            }else{
                y=temp*mul;
            }

        }*/
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

    private Coordinate checkYBack (Coordinate minime, Coordinate massime, int die) {

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

}

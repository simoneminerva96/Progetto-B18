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
            checkYForward(minime, massime, die);
            //checkXForward(minime,massime,die);
        } else {
            checkXBack(minime,massime,die);
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

    private Coordinate checkYForward (Coordinate minime, Coordinate massime, int die) {
        int t = 0;
        for(int i = 0; i<die; i++) {

            if (y == minime.getY() && x < massime.getX()) {
                x += MINMOVEMENT;
                System.out.println("vai3");
            }
            if (x==minime.getX() && y> minime.getY()) {
                y -= MINMOVEMENT;
                System.out.println("vai1");
            }
            if (y==massime.getY() && x >minime.getX()) {
                x -= MINMOVEMENT;
                System.out.println("vai2");
            }
            if (y < massime.getY() && x== massime.getX()) {

                if (t == 0){
                    System.out.println("EH VOLEVI");
                }
                else {
                    t = 1;
                    y += MINMOVEMENT;
                    System.out.println("vai4");
                }

            }


            /*if (y < massime.getY() && x <= massime.getX()) {
                y -= MINMOVEMENT;
            }*/
            /*if (y == minime.getY()) {
                if (x < massime.getX())
                    x += MINMOVEMENT;
                else
                    y += MINMOVEMENT;
            }
            if (y == massime.getY()) {
                if (x > minime.getX())
                    x -= MINMOVEMENT;
                else
                    y -= MINMOVEMENT;
            }
            System.out.println("X - checkY:" +  x);
            System.out.println("Y - checkY: " + y);
        */
        }
        return new Coordinate(x,y);
    }

    private Coordinate checkXForward (Coordinate minime, Coordinate massime, int die) {

        for (int i=0; i<die; i++) {
            if (x == massime.getX()) {
                if (y < massime.getY())
                    y += MINMOVEMENT;
                else
                    x -= MINMOVEMENT;
            }
            if (x == minime.getX()) {
                if (y > minime.getY())
                    y -= MINMOVEMENT;
                else
                    x += MINMOVEMENT;
            }
            System.out.println("X - checkx:" +  x);
            System.out.println("Y - checkX: " + y);

        }
        return new Coordinate(x, y);
    }

    private Coordinate checkYBack (Coordinate minime, Coordinate massime, int die) {

        for(int i = 0; i<die; i++) {
            if (y == minime.getY()) {
               if (x > minime.getX())
                   x -= MINMOVEMENT;
               else
                   y += MINMOVEMENT;
            }
            if (y == massime.getY()) {
                if (x < massime.getX())
                    x += MINMOVEMENT;
                else
                    y -= MINMOVEMENT;
            }
        }
        return new Coordinate(x,y);
    }

    private Coordinate checkXBack (Coordinate minime, Coordinate massime, int die) {

        for (int i=0; i<die; i++) {
            if (x == massime.getX()) {
                if (y > minime.getY())
                    y -= MINMOVEMENT;
                else
                    x -= MINMOVEMENT;
            }
            if (x == minime.getX()) {
                if (y < massime.getY())
                    y += MINMOVEMENT;
                else
                    x += MINMOVEMENT;
            }
        }
        return new Coordinate(x, y);
    }

}

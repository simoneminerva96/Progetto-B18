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

    public void calculate(Coordinate minime,Coordinate massime,int die,Direction direction){
        int minx=minime.getX();
        int miny=minime.getY();
        int maxx=massime.getX();
        int maxy=massime.getY();

        int mul=1,confront,temp;
        if(y==miny){
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

        }
    }



}

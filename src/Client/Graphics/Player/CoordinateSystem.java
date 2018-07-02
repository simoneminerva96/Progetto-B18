package Client.Graphics.Player;

import Server.GameClasses.Direction;

public class CoordinateSystem {
    private int x,y,minx,miny,maxx,maxy;



    public CoordinateSystem(int minx,int miny,int maxx,int maxy){

        this.minx=minx;
        this.miny=miny;
        this.maxx=maxx;
        this.maxy=maxy;
    }

    public void calculate(int xT,int yT,int die, Direction direction){
        int mul=1,confront,temp;
        this.x=xT;this.y=yT;
        if(y==miny){
            if(direction==Direction.FORWARD){
                mul=-1;confront=maxx*mul;temp=(x+2*die)*mul;

            }else{
                confront=minx*mul;temp=(x-2*die)*mul;

            }
            if(temp<confront){
                temp=confront-temp;y+=temp;x=confront*mul;
            }else{
                x=temp*mul;
            }
        }else if(x==maxx){
            if(direction==Direction.FORWARD){
                mul=-1;confront=maxy*mul;temp=(y+2*die)*mul;
            }else{
                confront=miny*mul;temp=(y-2*die)*mul;
            }
            if(temp<confront){
                temp=confront-temp;x-=temp;y=confront*mul;
            }else{
                y=temp*mul;
            }
        }else if(y==maxy){
            if(direction==Direction.FORWARD){
                mul=-1;confront=minx*mul;temp=(x-2*die)*mul;
            }else{
                confront=miny*mul;temp=(x+2*die)*mul;
            }
            if(temp>confront){
                temp=temp-confront;x=confront*mul;y-=temp;
            }else{
                x=temp*mul;
            }
        }else if(x==minx){
            if(direction==Direction.FORWARD){
                mul=-1;confront=miny*mul;temp=(y-2*die)*mul;
            }else{
                confront=maxy*mul;temp=(y+2*die)*mul;
            }
            if(temp>confront){
                temp=temp-confront;y=confront*mul;x+=temp;
            }else{
                y=temp*mul;
            }

        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

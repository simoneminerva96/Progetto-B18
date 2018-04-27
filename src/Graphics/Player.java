package Graphics;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Player{
    int x,y,id,minx,miny,maxx,maxy;
    String name;
    int mulsize;
    float xGUI=0,yGUI=0,xGUItemp=0,yGUItemp=0;
    public SpriteSheet mvup,mvdwn,mvlft,mvrght;
    public Animation playerGUI;
    public Boolean isOnleft=false;

    int mx,my;

    public Player(String name,int id,Map map,SpriteSheet mvlft){
        this.id=id;
        this.mvlft=mvlft;
        mulsize=map.tilesize;
        playerGUI=new Animation(mvlft,400);
        mx=map.getXmax();
        my=map.getYmax();

        if(id==1){
            minx=0;
            miny=0;
            maxx=map.xmax-2;
            maxy=map.ymax-2;
        }else if(id==2){
            minx=0;
            miny=1;
            maxx=map.xmax-2;
            maxy=map.ymax-1;
        }else if(id==3){
            minx=1;
            miny=0;
            maxx=map.xmax-1;
            maxy=map.ymax-2;
        }else if(id==4){
            minx=1;
            miny=1;
            maxx=map.xmax-1;
            maxy=map.ymax-1;
        }
        x=minx; //switch + x y da rinominare
        y=miny;

        xGUI=(x*mulsize)-5;
        yGUI=(y*mulsize)-5;
        xGUItemp=xGUI;
        yGUItemp=yGUI;
        this.name=name;
    }

    public void addMvup(String path,int tw,int th) throws SlickException {
        mvup=new SpriteSheet(path,tw,th);
    }
    public void addMvdwn(String path,int tw,int th) throws SlickException {
        mvdwn=new SpriteSheet(path,tw,th);
    }
    public void addMvlft(String path,int tw,int th) throws SlickException {
        mvlft=new SpriteSheet(path,tw,th);
    }
    public void addMvrght(String path,int tw,int th) throws SlickException {
        mvrght=new SpriteSheet(path,tw,th);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public String getName() {
        return name;
    }

    public void setY(int y) {
        this.y = y;

    }

    public int getMinx() {
        return minx;
    }

    public int getMiny() {
        return miny;
    }

    public int getMaxx() {
        return maxx;
    }

    public int getMaxy() {
        return maxy;
    }

    public void update(int die){

        if(y==miny){
            isOnleft=false;
            int tempx=x+(2*die);
            if(tempx>maxx){
                tempx=tempx-maxx;
                x=maxx;
                y=tempx;
                playerGUI=new Animation(mvdwn,150);
            }else{
                x+=2*die;
                playerGUI=new Animation(mvrght,150);
            }
        }else if(x==maxx){
            int tempy=y+(2*die);
            if(tempy>maxy){
                tempy=tempy-maxy;
                x-=tempy;
                y=maxy;

                playerGUI=new Animation(mvlft,150);
            }else{
                y+=2*die;
                playerGUI=new Animation(mvdwn,150);
            }
        }else if(y==maxy){
            int tempx=x-(2*die);
            if(tempx<minx){
                tempx=minx-tempx;
                x=minx;
                y-=tempx;
                playerGUI=new Animation(mvup,150);
            }else{
                x-=2*die;
                playerGUI=new Animation(mvlft,150);
            }
        }else if(x==minx){
            int tempy=y-(2*die);
            isOnleft=true;
            System.out.println("TEMPY: "+tempy+" MAXY: "+maxy);
            if(tempy<miny){
                tempy=miny-tempy;
                x+=tempy;
                y=miny;
                System.out.println("QUA"+ y);
                playerGUI=new Animation(mvrght,150);
            }else{
                y-=2*die;
                playerGUI=new Animation(mvup,150);
            }
        }
        xGUI=(x*mulsize)-5;
        yGUI=(y*mulsize)-5;

    }
}

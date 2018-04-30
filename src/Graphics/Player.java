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
    boolean direction=true;
    boolean isOnLeft=false,isOnRight=false;

    int mx,my;

    public Player(String name,int id,Map map){
        this.id=id;
        this.mvlft=mvlft;
        mulsize=map.tilesize;
        mx=map.getXmax();
        my=map.getYmax();

        switch(id){
            case 1:
                minx=0;
                miny=0;
                maxx=map.xmax-2;
                maxy=map.ymax-2;
                break;

            case 2:
                minx=0;
                miny=1;
                maxx=map.xmax-2;
                maxy=map.ymax-1;
                break;

            case 3:
                minx=1;
                miny=0;
                maxx=map.xmax-1;
                maxy=map.ymax-2;
                break;

            case 4:
                minx=1;
                miny=1;
                maxx=map.xmax-1;
                maxy=map.ymax-1;
                break;
        }
        x=maxx;
        y=maxy;
        this.name=name;
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

    public boolean getDirection(){return direction;}

    public void update(int die,boolean direction) {
        this.direction=direction; //direction==true spostamento in avanti; altrimenti indietro
        if (y == miny) {
            if(this.direction){
                isOnLeft=false;
                int tempx = x + (2 * die);
                if (tempx > maxx) {
                    tempx = tempx - maxx;
                    x = maxx;
                    y += tempx;
                } else {
                    x += 2 * die;
                }
            }else{
                int tempx = x - (2 * die);
                if (tempx < minx) {
                    tempx = minx - tempx;
                    x = minx;
                    y += tempx;
                } else {
                    x -= 2 * die;
                }
            }

        } else if (x == maxx) {
            if(this.direction){
                int tempy = y + (2 * die);
                if (tempy > maxy) {
                    tempy = tempy - maxy;
                    x -= tempy;
                    y = maxy;
                } else {
                    y += 2 * die;
                }
            }else{
                isOnRight=true;
                int tempy = y - (2 * die);
                if (tempy < miny) {
                    tempy = miny-tempy;
                    x -= tempy;
                    y = miny;
                } else {
                    y -= 2 * die;
                }
            }

        } else if (y == maxy) {
            if(this.direction){
                int tempx = x - (2 * die);
                if (tempx < minx) {
                    tempx = minx - tempx;
                    x = minx;
                    y -= tempx;
                } else {
                    x -= 2 * die;
                }
            }else{
                int tempx = x + (2 * die);
                if (tempx > maxx) {
                    tempx = tempx - maxx;
                    x = maxx;
                    y -= tempx;
                } else {
                    x += 2 * die;
                }
            }

        } else if (x == minx) {
            if(this.direction){
                int tempy = y - (2 * die);
                isOnLeft=true;

                if (tempy < miny) {
                    tempy = miny - tempy;
                    x += tempy;
                    y = miny;
                } else {
                    y -= 2 * die;
                }
            }else{
                int tempy = y + (2 * die);
                if (tempy > maxy) {
                    tempy = tempy-maxy;
                    x += tempy;
                    y = maxy;
                } else {
                    y += 2 * die;
                }
            }

        }
        System.out.println("X: "+x+" Y:"+y);
    }
}

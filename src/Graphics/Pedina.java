package Graphics;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Pedina {
    public SpriteSheet mvup,mvdwn,mvlft,mvrght;
    public Animation currentImage,up,dwn,sx,dx;

    public Pedina(String pathup,String pathdwn,String pathsx,String pathdx,int height,int width){
        try {
            mvup=new SpriteSheet(pathup,width,height);
            mvdwn=new SpriteSheet(pathdwn,width,height);
            mvrght=new SpriteSheet(pathdx,width,height);
            mvlft=new SpriteSheet(pathsx,width,height);
            up=new Animation(mvup,100);
            dwn=new Animation(mvdwn,100);
            sx=new Animation(mvlft,100);
            dx=new Animation(mvrght,100);
            currentImage=sx;
        } catch (SlickException e) {
            e.printStackTrace();
        }

    }
    public void setMvdwn() {
        currentImage=dwn;
        currentImage.start();
    }
    public void setMvup() {
        currentImage=up;
        currentImage.start();
    }
    public void setMvsx() {
        currentImage=sx;
        currentImage.start();
    }
    public void setMvdx() {
        currentImage=dx;
        currentImage.start();
    }
    public Animation getCurrentImage() {
        return currentImage;
    }
}


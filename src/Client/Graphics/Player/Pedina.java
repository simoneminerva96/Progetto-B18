package Client.Graphics.Player;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * @author Stefano
 * La classe Pedina rappresenta la pedina associata al Player e che si muoverà sul tabellone.
 *
 * - mvup, mvdwn, mvlft, mvrght: Spritesheet delle varie direzioni in cui si muoverà la pedina
 * - currentImage: animazione corrente della pedina
 * - up, dwn, sx, dx: animazione nelle varie direzioni possibili
 */
public class Pedina {
    private SpriteSheet mvup,mvdwn,mvlft,mvrght;
    private Animation currentImage,up,dwn,sx,dx;

    public Pedina(String pathup,String pathdwn,String pathsx,String pathdx,int height,int width){
        try {
            mvup = new SpriteSheet(pathup,width,height);
            mvdwn = new SpriteSheet(pathdwn,width,height);
            mvrght = new SpriteSheet(pathdx,width,height);
            mvlft = new SpriteSheet(pathsx,width,height);
            up = new Animation(mvup,100);
            dwn = new Animation(mvdwn,100);
            sx = new Animation(mvlft,100);
            dx = new Animation(mvrght,100);
            currentImage = sx;
        } catch (SlickException e) {
            e.printStackTrace();
        }

    }
    public void setMvdwn() {
        currentImage = dwn;
        currentImage.start();
    }
    public void setMvup() {
        currentImage = up;
        currentImage.start();
    }
    public void setMvsx() {
        currentImage = sx;
        currentImage.start();
    }
    public void setMvdx() {
        currentImage = dx;
        currentImage.start();
    }
    public Animation getCurrentImage() {
        return currentImage;
    }
}


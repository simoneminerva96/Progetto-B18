package Client.Graphics.com.sticky;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Shape;
import Client.Graphics.com.sticky.events.*;
import org.newdawn.slick.state.StateBasedGame;


/**
 * A button with graphics and sound effects which reacts to the mouse.
 *
 * @author Alexander Schearer <aschearer@gmail.com>
 */
public class NumberButton extends ButtonSkeleton implements ButtonListener,
        ClickListener {

    private ClickListener clickListener;
    private ButtonListener buttonListener;

    private Shape shape;
    private Image current,up,down;
    private Sound click;
    private int number;
    float minx, maxx, maxy, miny;

    public NumberButton(Shape s, Image up, Image down,Sound click,int number) {
        shape = s;
        setShape(s);
        this.up = up;
        this.down = down;
        this.number=number;


        current = up;
        this.click = click;

        clickListener = NullListener.getSingleton();
        buttonListener = NullListener.getSingleton();
        super.addListener((ButtonListener) this);
        super.addListener((ClickListener) this);
        minx = shape.getX();
        maxx = shape.getMaxX();
        miny = shape.getY();
        maxy = shape.getMaxY();
    }

    @Override
    public void addListener(ClickListener l) {
        clickListener = l;
    }

    @Override
    public void addListener(ButtonListener b) {
        buttonListener = b;
    }

    @Override
    public void render(GameContainer container, Graphics g) {
        current.draw(shape.getX(), shape.getY());
    }

    public void onClick(Button clicked, float mx, float my) {}
    public int onClickGetNumber(float mx, float my,int i,boolean[]array) throws SlickException {

        if(mx>=minx&&mx<=maxx&&my>=miny&&my<=maxy) {
            for(int p=0;p<array.length;p++){
                if(p!=number){
                    array[p]=false;
                }else{
                    array[p]=true;
                    current=down;
                }
            }
            return number;
        }else{
            return i;
        }

    }

    public void onDoubleClick(Button clicked, float mx, float my) {}

    public void onRightClick(Button clicked, float mx, float my) {}

    public void onMouseEnter(Button clicked, float mx, float my) {}

    public void onMouseEnterAndClick(Button b,float mx, float my,boolean[]array) {

        if(mx>=minx&&mx<=maxx&&my>=miny&&my<=maxy||array[number]==true) {
            buttonListener.onMouseEnter(this,mx,my);
            current = down;
        }else{
            current=up;
        }
    }

    public void onMouseExit(Button b) {
        buttonListener.onMouseExit(this);
        current = up;
    }

}


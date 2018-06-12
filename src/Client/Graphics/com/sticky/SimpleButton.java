package Client.Graphics.com.sticky;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Shape;

import Client.Graphics.com.sticky.events.*;


/**
 * A button with graphics and sound effects which reacts to the mouse.
 * 
 * @author Alexander Schearer <aschearer@gmail.com>
 */
public class SimpleButton extends ButtonSkeleton implements ButtonListener,
        ClickListener {

    private ClickListener clickListener;
    private ButtonListener buttonListener;

    private Shape shape;
    private Image current, up, down,press;
    private Sound click;

    public SimpleButton(Shape s, Image up, Image down,Image press, Sound click) {
        shape = s;
        setShape(s);
        this.up = up;
        this.down = down;
        this.press=press;

        current = up;
        this.click = click;

        clickListener = NullListener.getSingleton();
        buttonListener = NullListener.getSingleton();
        super.addListener((ButtonListener) this);
        super.addListener((ClickListener) this);

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

    public void onClick(Button clicked, float mx, float my) {
        float minx =shape.getX();
        float miny=shape.getY();
        float maxx=shape.getMaxX();
        float maxy=shape.getMaxY();

        if(mx>=minx&&mx<=maxx&&my>=miny&&my<=maxy) {
            current = press;
        }

    }

    public void onDoubleClick(Button clicked, float mx, float my) {
        clickListener.onDoubleClick(this, mx, my);
        click.play();
    }

    public void onRightClick(Button clicked, float mx, float my) {
        clickListener.onRightClick(this, mx, my);
        click.play();
    }

    public void onMouseEnter(Button b,float mx, float my) {
        float minx =shape.getX();
        float miny=shape.getY();
        float maxx=shape.getMaxX();
        float maxy=shape.getMaxY();

        if(mx>=minx&&mx<=maxx&&my>=miny&&my<=maxy) {
            buttonListener.onMouseEnter(this,mx,my);
            // click.play();
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

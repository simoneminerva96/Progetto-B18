package Client.Graphics.com.sticky;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Shape;
import Client.Graphics.Escape;
import Client.Graphics.com.sticky.events.*;
import org.newdawn.slick.state.StateBasedGame;

/**
 * A button with graphics and sound effects which reacts to the mouse.
 *
 * @author Alexander Schearer <aschearer@gmail.com>
 */
public class FormButton extends ButtonSkeleton implements ButtonListener, ClickListener {

    private ClickListener clickListener;
    private ButtonListener buttonListener;
    private Shape shape;
    private Image current, up, down,press;
    private Sound click;
    private float minx, maxx, miny, maxy;
    private boolean clicked = false;

    public FormButton(Shape s, Image up, Image down,Image press, Sound click) {
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

        minx = shape.getX();
        miny = shape.getY();
        maxx = shape.getMaxX();
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

    public void onClick(Button clicked, float mx, float my) {

        if(mx>=minx && mx<=maxx && my>=miny && my<=maxy) {
            current = press;
        }
    }
    public void onCLickFormExit (float mx, float my) {
        if (mx >= minx && mx <= maxx && my >= miny && my <= maxy) {
            current = press;
            System.exit(0);
        }
    }

    public void onClickFormMenu (float mx, float my, StateBasedGame stateBasedGame, int n) {
        if (mx >= minx && mx <= maxx && my >= miny && my <= maxy) {
            current = press;
            stateBasedGame.enterState(n);
        }
    }

    public void onClickFormResume (float mx, float my, Escape esc){
        if (mx >= minx && mx <= maxx && my >= miny && my <= maxy) {
            current = press;
            esc.setQuit(false);
        }
    }

    public boolean onClickForm(float mx, float my) {
        if (mx >= minx && mx <= maxx && my >= miny && my <= maxy) {
            current = press;
            clicked = true;
        }
        return clicked;
    }

    public void onDoubleClick(Button clicked, float mx, float my) { }

    public void onRightClick(Button clicked, float mx, float my) { }

    public void onMouseEnter(Button b,float mx, float my) {

        if(mx>=minx && mx<=maxx && my>=miny && my<=maxy) {
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

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }
}

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
public class StateButton extends ButtonSkeleton implements ButtonListener,
        ClickListener {

    private ClickListener clickListener;
    private ButtonListener buttonListener;

    private Shape shape;
    private Image current, up, down,press;
    private Sound click;
    float minx, maxx, maxy, miny;

    public StateButton(Shape s, Image up, Image down,Image press, Sound click) {
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
    public boolean onClickState(float mx, float my,StateBasedGame stateBasedGame,int i) throws SlickException {

        if(mx>=minx&&mx<=maxx&&my>=miny&&my<=maxy) {
            current = press;
            stateBasedGame.enterState(i);
            return true;
        }else{
            return false;
        }
    }

    public void onClickStateSound (float mx, float my, GameContainer gc, boolean on) {
        if(mx>=minx&&mx<=maxx&&my>=miny&&my<=maxy) {
            current = press;
            gc.setMusicOn(!on);

        }
    }

    public boolean onClickBoolean(float mx,float my){
        if(mx>=minx&&mx<=maxx&&my>=miny&&my<=maxy) {
            return true;
        }
        return false;

    }

    public void onDoubleClick(Button clicked, float mx, float my) {}

    public void onRightClick(Button clicked, float mx, float my) {}

    public void onMouseEnter(Button b,float mx, float my) {

        if(mx>=minx&&mx<=maxx&&my>=miny&&my<=maxy) {
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

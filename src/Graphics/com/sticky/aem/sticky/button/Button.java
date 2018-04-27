package javagame.com.sticky.aem.sticky.button;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import javagame.com.sticky.aem.sticky.*;
import javagame.com.sticky.aem.sticky.button.events.*;

/**
 * @author Alexander Schearer <aschearer@gmail.com>
 */
public interface Button extends Clickable {

    /**
     * Set a single click listener for this button.
     * 
     * @param l
     */
    public void addListener(ClickListener l);
    
    /**
     * Set a single button listener for this button.
     * @param b
     */
    public void addListener(ButtonListener b);

    /**
     * @param container
     * @param g
     */
    public void render(GameContainer container, Graphics g);

    /**
     * @param container
     * @param delta
     */
    public void update(GameContainer container, int delta);
}

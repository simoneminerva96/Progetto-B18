package javagame.com.sticky.aem.sticky.button;

import javagame.com.sticky.aem.sticky.button.events.*;

/**
 * @author Alexander Schearer <aschearer@gmail.com>
 */
public interface Draggable extends Button {

    /**
     * Set a single click listener for this button.
     * 
     * @param d
     */
    public void addListener(DragListener d);
}

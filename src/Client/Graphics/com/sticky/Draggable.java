package Client.Graphics.com.sticky;

import Client.Graphics.com.sticky.events.*;

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

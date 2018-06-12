package Client.Graphics.com.sticky.events;

import Client.Graphics.com.sticky.Button;

/**
 * Capture events related to buttons.
 *
 * @author Alexander Schearer <aschearer@gmail.com>
 */
public interface ButtonListener {

    /**
     * @param b
     */
    public void onMouseEnter(Button b,float mx, float my);
    
    /**
     * @param b
     */
    public void onMouseExit(Button b);
}

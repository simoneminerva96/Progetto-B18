package javagame.com.sticky.aem.sticky.button.events;

import javagame.com.sticky.aem.sticky.button.Button;

/**
 * Capture events related to buttons.
 *
 * @author Alexander Schearer <aschearer@gmail.com>
 */
public interface ButtonListener {

    /**
     * @param b
     */
    public void onMouseEnter(Button b);
    
    /**
     * @param b
     */
    public void onMouseExit(Button b);
}

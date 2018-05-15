package GameClasses;

import java.awt.*;
/**
 * classe corrispondente a una pedina
 * @author Ansaldi Jacopo <jacopo.ansaldi01@universitadipavia.it>
 */

public class Piece {
    private Color AssociatedColor;  //colore corrispondente

    public Piece(Color color){
        AssociatedColor=color;
    }

    public String getAssociatedColor() {
        if(AssociatedColor.equals(Color.RED)) return "red";
        if(AssociatedColor.equals(Color.BLUE)) return "blue";
        if(AssociatedColor.equals(Color.YELLOW)) return "yellow";
        else return "green";
    }

}

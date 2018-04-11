package GameClasses;

import java.awt.*;
/*
    classe corrispondente a una pedina
 */

public class Piece {
    private Color AssociatedColor;  //colore corrispondente
    private int ActualPosition;     //posizione attuale corrispondente all'indice della casella su cui si trova

    public Piece(Color color){
        AssociatedColor=color;
        ActualPosition=0;       //quando viene istanziata una pedina viene inizializzata sulla casella iniziale(index=0)
    }

    public int getActualPosition() {
        return ActualPosition;
    }

    public void setActualPosition(int actualPosition) {
        ActualPosition = actualPosition;
    }
}

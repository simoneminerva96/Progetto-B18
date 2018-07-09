package Server.GameClasses.GameClasses;

import java.util.ArrayList;
/**
 * Classe corrispondente a un singolo giocatore che partecipa alla partita
 * - initialRollResult: risultato del lancio del dado iniziale
 * - actualPosition: posizione attuale del player sul tabellone
 * - slicesObtained: arrayList di spicchi ottenuti
 * @author Ansaldi Jacopo <jacopo.ansaldi01@universitadipavia.it>
 */
    public class Player {
    private String nickname;
    private int initialRollResult;
    private int actualPosition;
    private ArrayList<Slice> slicesObtained;

    public Player(String nickname){
        this.nickname=nickname;
        initialRollResult=0;
        actualPosition=0;   //quando viene istanziato un giocatore viene inizializzato sulla casella iniziale(index=0)
        slicesObtained=new ArrayList<>();
    }

    /**
     * Metodo per assegnare uno spicchio di una categoria solo se non è stato già ottenuto.
     * @param category categoria dello spicchio da verificare
     *
     */
     public void obtainSlice(Categories category){
        if(!checkSliceAlreadyObtained(category))
            slicesObtained.add(new Slice(category));
    }

    /**ritorna true se il giocatore ha gia vinto lo spicchio di questa categoria*/
    private boolean checkSliceAlreadyObtained(Categories c){
        boolean check=false;
        for (Slice slice: slicesObtained) {
            if (slice.getCategory().equals(c))
                check = true;
        }
        return check;
    }

    public int getInitialRollResult() { return initialRollResult; }

    public void setInitialRollResult(int initialRollResult) { this.initialRollResult = initialRollResult; }

    public String getNickname() { return nickname; }

    public int getActualPosition() { return actualPosition; }

    public void setActualPosition(int actualPosition) { this.actualPosition = actualPosition; }

    public ArrayList<Slice> getSlicesObtained() { return slicesObtained; }

}

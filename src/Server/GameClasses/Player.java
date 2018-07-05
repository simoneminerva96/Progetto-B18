package Server.GameClasses;

import java.util.ArrayList;
/**
 * Classe corrispondente a un singolo giocatore che partecipa alla partita
 * - initialRollResult: risultato del lancio del dado iniziale
 * - actualPosition: posizione attuale del player sul tabellone
 * - slicesObtained: arrayList di diamanti ottenuti
 * @author Ansaldi Jacopo <jacopo.ansaldi01@universitadipavia.it>
 */
    class Player {
    private String nickname;
    private int initialRollResult;
    private int actualPosition;
    private ArrayList<Slice> slicesObtained;

    Player(String nickname){
        this.nickname=nickname;
        initialRollResult=0;
        actualPosition=0;   //quando viene istanziato un giocatore viene inizializzato sulla casella iniziale(index=0)
        slicesObtained=new ArrayList<>();
    }

    /**
     * Metodo per assegnare uno spicchio di una categoria solo se non è stato già ottenuto.
     * - check: flag che indica se lo spicchio è già presente
     * @param category categoria dello spicchio da verificare
     *
     */
     void obtainSlice(Categories category){
        boolean check=false;

        for (Slice slice: slicesObtained) {
            if (slice.getCategory().equals(category))
                check = true;
        }
        if(!check)
            slicesObtained.add(new Slice(category));
    }

    int getInitialRollResult() { return initialRollResult; }

    void setInitialRollResult(int initialRollResult) { this.initialRollResult = initialRollResult; }

    String getNickname() { return nickname; }

    int getActualPosition() { return actualPosition; }

    void setActualPosition(int actualPosition) { this.actualPosition = actualPosition; }

    ArrayList<Slice> getSlicesObtained() { return slicesObtained; }

}

package GameClasses;

import java.util.ArrayList;
import java.util.Random;

/**
 * classe corrispondente a un singolo giocatore che partecipa alla partita
 * @author Ansaldi Jacopo <jacopo.ansaldi01@universitadipavia.it>
 */
public class Player {
    private String nickname;
    private Piece chosenPiece;
    private int initialRollResult;
    private int actualPosition;
    private ArrayList<Slice> slicesObtained;

    public Player(String nickname){
        this.nickname=nickname;
        chosenPiece=null;   //quando viene istanziato un giocatore esso non ha ancora nessuna pedina associata
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
    public void obtainSlice(Categories category){
        boolean check=false;

        for(int i=0; i<slicesObtained.size(); i++){
            if(slicesObtained.get(i).getCategory().equals(category)){
                System.out.println("il giocatore ha gia ottenuto lo spicchio di questa categoria!\n");
                check=true;
            }
        }
        if(check==false){
            slicesObtained.add(new Slice(category));
        }
    }

    public int getInitialRollResult() {
        return initialRollResult;
    }

    public void setInitialRollResult(int initialRollResult) {
        this.initialRollResult = initialRollResult;
    }

    public String getNickname() {
        return nickname;
    }

    public void setChosenPiece(Piece chosenPiece) {
        this.chosenPiece = chosenPiece;
    }

    public int getActualPosition() {
        return actualPosition;
    }

    public void setActualPosition(int actualPosition) {
        this.actualPosition = actualPosition;
    }

    public ArrayList<Slice> getSlicesObtained() {
        return slicesObtained;
    }

}

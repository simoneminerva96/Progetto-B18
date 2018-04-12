package GameClasses;
/*
     classe corrispondente a un singolo giocatore che partecipa alla partita
 */
public class Player {
    private String nickname;
    private Piece chosenPiece;
    private int initialRollResult;
    private int actualPosition;

    public Player(String nickname){
        this.nickname=nickname;
        chosenPiece=null;        //quando viene istanziato un giocatore esso non ha ancora nessuna pedina associata
        initialRollResult=0;
        actualPosition=0;       //quando viene istanziato un giocatore viene inizializzato sulla casella iniziale(index=0)
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

    public Piece getChosenPiece() {
        return chosenPiece;
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
}

package GameClasses;
/*
     classe corrispondente a un singolo giocatore che partecipa alla partita
 */
public class Player {
    private String nickname;
    private Piece chosenPiece;

    public Player(String nickname){
       this.nickname=nickname;
       chosenPiece=null;        //quando viene istanziato un giocatore esso non ha ancora nessuna pedina associata
    }

    public Piece getChosenPiece() {
        return chosenPiece;
    }

    public void setChosenPiece(Piece chosenPiece) {
        this.chosenPiece = chosenPiece;
    }
}

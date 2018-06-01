package GameClasses;

import GameClasses.Squares.BonusMalusSquare;
import GameClasses.Squares.FinalQuestionSquare;

/**
 * CLASSE CHE CORRISPONDE A UN TURNO DI GIOCO, effettua la movimentazione delle pedine sul tabellone
 * @author Ansaldi Jacopo <jacopo.ansaldi01@universitadipavia.it>
 */

public class Turn {
    private final static int NSQUARES=36;
    //private BoardProva playBoard;    //tabellone di gioco di prova
    private BoardProva playBoard;    //tabellone di gioco connesso al db
    private Player playerOnTurn;        //giocatore di turno
    private Die die;        //dado per determinare di quanto spostarsi
    private int dieresult;  //risultato del lancio del dado
    private String chosenDirection;

    public Turn(Player playerOnTurn,BoardProva playBoard){
        this.playerOnTurn=playerOnTurn;
        this.playBoard=playBoard;
        die=new Die();
        dieresult=0;
        chosenDirection="";
    }
    //metodo da chiamare nella classe trivialgame per cambiar turno
    public void setPlayerOnTurn(Player playerOnTurn) {
        this.playerOnTurn = playerOnTurn;
    }

    //metodo che effettua il lancio del dado
    public int dieLaunch(){
        dieresult=die.Launch();
        return dieresult;
    }

    //setta la direzione scelta dal giocatore
    public void setChosenDirection(String direction){
        this.chosenDirection=direction;
    }

    //metodo che muove la pedina del risultato del dado nella direzione scelta
    public void movePlayer(){
        System.out.println("turno di " + playerOnTurn.getNickname());
        System.out.println("posizione di partenza : " + playerOnTurn.getActualPosition());
        if(chosenDirection.equalsIgnoreCase("cw")){
            if(playerOnTurn.getActualPosition() + dieresult >= NSQUARES){
                Integer position=playerOnTurn.getActualPosition() + dieresult -NSQUARES;
                playerOnTurn.setActualPosition(position);
            }
            else playerOnTurn.setActualPosition(playerOnTurn.getActualPosition() + dieresult);
        }
        if(chosenDirection.equalsIgnoreCase("ccw")){
            if(playerOnTurn.getActualPosition() - dieresult < 0){
                Integer excess= -1*(playerOnTurn.getActualPosition() - dieresult);
                playerOnTurn.setActualPosition(NSQUARES-excess);
            }
            else playerOnTurn.setActualPosition(playerOnTurn.getActualPosition() - dieresult);
        }
        System.out.println("posizione attuale: " + playerOnTurn.getActualPosition() + "\n");
    }

    //metodo che esegue il bonus/malus corrispondente alla casella in cui si trova il giocatore
    public void executeBonusMalus(){
        int currentPosition=playerOnTurn.getActualPosition();
        Square currentSquare=playBoard.getSquares().get(currentPosition);
        if(currentSquare instanceof BonusMalusSquare){
            ((BonusMalusSquare) currentSquare).executeBonusMalus(this);
        }
    }

    public Question visualizeQuestion(){
        int currentPosition=playerOnTurn.getActualPosition();
        return this.playBoard.getSquares().get(currentPosition).visualizeQuestion();
    }

    //metodo che permette al giocatore di rispondere
    public Boolean AnswerQuestion(int indexOfAnswer){
        int currentPosition=playerOnTurn.getActualPosition();
        return this.playBoard.getSquares().get(currentPosition).goOnIt(indexOfAnswer);
    }
    //RITORNA TRUE SE IL GIOCATORE HA OTTENUTO LO SPICCHIO
    public boolean obtainSlice(){
        Square actualSquare=playBoard.getSquares().get(playerOnTurn.getActualPosition());
        if(actualSquare instanceof FinalQuestionSquare){
            Categories categoryOfTheSlice=((FinalQuestionSquare)actualSquare).getCategory();
            System.out.println(playerOnTurn.getNickname() + " ha ottenuto lo spicchio di " +categoryOfTheSlice );
            playerOnTurn.obtainSlice(categoryOfTheSlice);
            return true;
        }
        else return false;
    }

    public Boolean verifyVictory(){
        if(playerOnTurn.getSlicesObtained().size() == 6 && playerOnTurn.getActualPosition() ==0){
            return true;
        }
        else return false;
    }

}

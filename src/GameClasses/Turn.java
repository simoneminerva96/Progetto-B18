package GameClasses;

import GameClasses.Squares.BonusMalusSquare;
import GameClasses.Squares.FinalQuestionSquare;
import Interface.BonusMalusRandom;

import java.util.ArrayList;

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
    private boolean correctAnswer= true; //booleano che mi indica se la risposta data dall'utente è giusta o sbagliata

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

    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
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
        //se finisco sulla casella random estraggo un effetto
        if(playerOnTurn.getActualPosition() == 18){
            BonusMalusSquare currentSquare= (BonusMalusSquare) playBoard.getSquares().get(playerOnTurn.getActualPosition());
            currentSquare.extractEffectType();
        }
    }

    public boolean checkBonusMalus(){
        int currentPosition=playerOnTurn.getActualPosition();
        Square currentSquare=playBoard.getSquares().get(currentPosition);
        if(currentSquare instanceof BonusMalusSquare){
            return true;
        }
        else return false;
    }

    //ritorna l'effetto che viene eseguito dalla casella
    public BonusMalusRandom executeBonusMalus(){
        int currentPosition=playerOnTurn.getActualPosition();
        Square currentSquare=playBoard.getSquares().get(currentPosition);
        if(currentSquare instanceof BonusMalusSquare){
            return ((BonusMalusSquare) currentSquare).executeBonusMalus(this);
        }
        else return null;
    }

    public Question visualizeQuestion(){
        int currentPosition=playerOnTurn.getActualPosition();
        return this.playBoard.getSquares().get(currentPosition).visualizeQuestion();
    }

    //metodo che permette al giocatore di rispondere
    public Boolean AnswerQuestion(int indexOfAnswer){
        int currentPosition=playerOnTurn.getActualPosition();
        correctAnswer=this.playBoard.getSquares().get(currentPosition).goOnIt(indexOfAnswer);
        if(correctAnswer){
            this.obtainSlice();//METODO CHE AGGIUNGE AL GIOCATORE LO SPICCHIO DELLA CATEGORIA SE LA DOMANDA è UNA DOM. FINALE
        }
        return correctAnswer;
    }

    public boolean getCorrectAnswer(){
        return this.correctAnswer;
    }

    //RITORNA TRUE SE IL GIOCATORE HA OTTENUTO LO SPICCHIO
    public void obtainSlice(){
        Square actualSquare=playBoard.getSquares().get(playerOnTurn.getActualPosition());
        if(actualSquare instanceof FinalQuestionSquare){
            Categories categoryOfTheSlice=((FinalQuestionSquare)actualSquare).getCategory();
            System.out.println(playerOnTurn.getNickname() + " ha ottenuto lo spicchio di " +categoryOfTheSlice );
            playerOnTurn.obtainSlice(categoryOfTheSlice);
        }
    }


    public Boolean verifyVictory(){
        if(playerOnTurn.getSlicesObtained().size() == 6 && playerOnTurn.getActualPosition() ==0){
            return true;
        }
        else return false;
    }

}

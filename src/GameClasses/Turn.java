package GameClasses;

import GameClasses.Squares.BonusMalusSquare;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * CLASSE CHE CORRISPONDE A UN TURNO DI GIOCO, effettua la movimentazione delle pedine sul tabellone
 * @author Ansaldi Jacopo <jacopo.ansaldi01@universitadipavia.it>
 */

public class Turn {
    private final static int NSQUARES=36;
    private Board playBoard;    //tabellone di gioco
    private Player playerOnTurn;        //giocatore di turno
    private Die die;        //dado per determinare di quanto spostarsi
    private int dieresult;  //risultato del lancio del dado

    public Turn(Player playerOnTurn,Board playBoard){
        this.playerOnTurn=playerOnTurn;
        this.playBoard=playBoard;
        die=new Die();
        dieresult=0;
    }
    //metodo da chiamare nella classe trivialgame per cambiar turno
    public void setPlayerOnTurn(Player playerOnTurn) {
        this.playerOnTurn = playerOnTurn;
    }

    //metodo che effettua il lancio del dado e muove la pedina di conseguenza
    public void dieLaunch(){
        System.out.println("turno di " + playerOnTurn.getNickname());
        dieresult=die.Launch();
        System.out.println("risultato del lancio : " + dieresult);
    }

    //metodo che muove la pedina del risultato del dado
    public void movePlayer(){
        Scanner sc=new Scanner(System.in);
        System.out.println("in che direzione muoversi? cw=senso orario ccw=senso antiorario)");
        String direction="";
        while (!(direction.equalsIgnoreCase("ccw") || (direction.equalsIgnoreCase("cw")))){
            direction=sc.next();
            if(!(direction.equalsIgnoreCase("ccw") || (direction.equalsIgnoreCase("cw")))) {
                System.out.println("inserisci cw o ccw");
            }
        }
        System.out.println("posizione di partenza : " + playerOnTurn.getActualPosition());
        if(direction.equalsIgnoreCase("cw")){
            if(playerOnTurn.getActualPosition() + dieresult >= NSQUARES){
                Integer position=playerOnTurn.getActualPosition() + dieresult -NSQUARES;
                playerOnTurn.setActualPosition(position);
            }
            else playerOnTurn.setActualPosition(playerOnTurn.getActualPosition() + dieresult);
        }
        if(direction.equalsIgnoreCase("ccw")){
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

    public Boolean AnswerQuestion(){
        Boolean correct=false;
        int currentPosition=playerOnTurn.getActualPosition();
        correct=this.playBoard.getSquares().get(currentPosition).goOnIt();
        return correct;
    }

}

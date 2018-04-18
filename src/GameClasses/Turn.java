package GameClasses;

import java.util.ArrayList;
import java.util.Scanner;
/*
    CLASSE CHE CORRISPONDE A UN TURNO DI GIOCO
 */

public class Turn {
    private Player playerOnTurn;        //giocatore di turno
    private Die die;        //dado per determinare di quanto spostarsi

    public Turn(Player playerOnTurn){
        this.playerOnTurn=playerOnTurn;
        die=new Die();
    }
    //metodo da chiamare nella classe trivialgame per cambiar turno
    public void setPlayerOnTurn(Player playerOnTurn) {
        this.playerOnTurn = playerOnTurn;
    }

    //metodo che effettua il lancio del dado e muove la pedina di conseguenza
    public void dieLaunch(){
        System.out.println("turno di " + playerOnTurn.getNickname());
        Integer dieRoll=die.Launch();
        System.out.println("risultato del lancio : " + dieRoll);
        movePlayer(dieRoll);
    }
    //metodo che muove la pedina del risultato del dado
    private void movePlayer(Integer resultOfDieRoll){
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
            if(playerOnTurn.getActualPosition() + resultOfDieRoll >= 40){
                Integer position=playerOnTurn.getActualPosition() + resultOfDieRoll -39;
                playerOnTurn.setActualPosition(position);
            }
            else playerOnTurn.setActualPosition(playerOnTurn.getActualPosition() + resultOfDieRoll);
        }
        if(direction.equalsIgnoreCase("ccw")){
            if(playerOnTurn.getActualPosition() - resultOfDieRoll < 0){
                Integer excess= -1*(playerOnTurn.getActualPosition() - resultOfDieRoll);
                playerOnTurn.setActualPosition(40-excess);
            }
            else playerOnTurn.setActualPosition(playerOnTurn.getActualPosition() - resultOfDieRoll);
        }
        System.out.println("posizione attuale: " + playerOnTurn.getActualPosition() + "\n");
    }

}

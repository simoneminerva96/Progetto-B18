package GameClasses;

import GameClasses.Squares.BonusMalusSquare;
import java.awt.*;
import java.sql.SQLException;
import java.util.*;

import Interface.BonusMalusRandom;
import Interface.Direction;

/**
 * CLASSE A CUI CORRISPONDE UNA SINGOLA PARTITA DI GIOCO
 * @author Ansaldi Jacopo <jacopo.ansaldi01@universitadipavia.it>
 */
public class TrivialGame {
    private ArrayList<Player> players;      //giocatori partecipanti
    private ArrayList<Piece> possiblePieces;        //possibili pedine tra cui scegliere
    private Die die;        //dado
    //private Board playBoard;        //tabellone di gioco
    private BoardProva playBoard;    //tabellone di gioco di prova
    private Turn turn;      //turno attuale
    private TurnPhase turnPhase;
    private Integer index=0; //INDICE CHE SERVE PER TENER IL CONTO DI QUALE GIOCATORE è IL TURNO

    public TrivialGame(){
        players = new ArrayList<>();
        possiblePieces=  new ArrayList<>();
        die = new Die();
        turnPhase=null; //inizialmente è nullo
        try{
            playBoard = new BoardProva();
            turn = new Turn(null,playBoard);    //all'inizio non ho alcun giocatore di turno
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Integer getIndex() {
        return index;
    }

    /**
     * metodo che crea i giocatori che parteciperanno alla partita, riceve in ingresso la lista dei nickname
     */
    public void initializePlayers(ArrayList<String> nicknames){
        for(int i=0;i<nicknames.size();i++){
            players.add(new Player(nicknames.get(i)));
        }
    }

    //metodo che inizializza le possibili pedine, poi i colori possono essere cambiati volendo
    public void InitializePossiblePieces(){
        possiblePieces.add(new Piece(Color.BLUE));
        possiblePieces.add(new Piece(Color.RED));
        possiblePieces.add(new Piece(Color.YELLOW));
        possiblePieces.add(new Piece(Color.GREEN));
    }

    /**metodo che esegue il lancio iniziale del dado e che ordina di conseguenza i giocatori nell'ordine
     * in cui giocheranno
    NB: implementato facendo in modo che i 4 lanci diano risultati diversi tra loro, altrimenti si rischia
     che questa fase del gioco si prolunghi troppo in caso di continui pareggi
    */
    public void BeginningDieRoll(){
        ArrayList<Integer> launches = new ArrayList<>();
        launches.add(die.Launch());
        Boolean check=false;

        //ciclo che riempe l'array dei lanci facendo in modo che siano diversi
        for(int i=0;i<players.size() -1;i++){
            Integer actualLaunch = null;
            while (check==false)
            {
                actualLaunch = die.Launch();
                check = checkDifferentLaunches(launches, actualLaunch);
            }
            launches.add(actualLaunch);
            check=false;
        }
        // a ogni giocatore viene associato il risultato del suo lancio
        for(int i=0;i<players.size();i++) players.get(i).setInitialRollResult(launches.get(i));
        //STAMPA DEL RISULTATO DEL LANCIO
        System.out.println("risultati del lancio:");
        for(int i=0;i<launches.size();i++) System.out.println("giocatore " + players.get(i).getNickname() + " : " + players.get(i).getInitialRollResult());
        //ordinamento lanci precedenti
        ArrayList<Integer> orderedLaunches=new ArrayList<Integer>();
        orderedLaunches.addAll(orderLaunches(launches));
        //creazione lista ordinata dei giocatori
        ArrayList<Player> orderedPlayers=new ArrayList<Player>();
        for(int i=0;i<orderedLaunches.size();i++){
            for(int j=0;j<players.size();j++){
                if(players.get(j).getInitialRollResult() == orderedLaunches.get(i)) orderedPlayers.add(players.get(j));
            }
        }
        players.clear();
        players.addAll(orderedPlayers);
        //stampa giocatori ordinati
        System.out.println("ordered players:");
        for(int i=0;i<players.size();i++) System.out.println(players.get(i).getNickname());
    }

    //metodo che ordina un array di interi in ordine decrescente
    private ArrayList<Integer> orderLaunches(ArrayList<Integer> launches){
        int swipVariable;
        for(int i=0;i<launches.size();i++){
            for(int j=0;j<launches.size();j++){
                if(launches.get(i) > launches.get(j)){
                    swipVariable=launches.get(i);
                    launches.set(i,launches.get(j));
                    launches.set(j,swipVariable);
                }
            }
        }
        return launches;
    }

    //metodo che viene usato per controllare che i lanci dei giocatori siano diversi tra loro
    private boolean checkDifferentLaunches(ArrayList<Integer> previousLaunches, int currentLaunch){
        Boolean check=true;
        for(int i=0;i<previousLaunches.size();i++){
            if(currentLaunch==previousLaunches.get(i)) check=false;
        }
        return check;
    }

    /**
        metodo che implementa la scelta iniziale della pedina (per ora con scelta da terminale)
        NB finire con i casi:
        1)in cui un giocatore inserisci un colore corrispondente a una pedina gia scelta
        2)l'ultimo giocatore gli viene associata l'ultima pedina rimasta senza esplicita scelta
     */
    public void pieceChoose(){
        Scanner sc=new Scanner(System.in);
        for(int i=0;i<players.size();i++){
            System.out.println("\n" + players.get(i).getNickname() + " quale pedina scegli?");
            System.out.println("pedine disponibili: ");
            for(int j=0;j<possiblePieces.size();j++){
                System.out.print((j+1) +")" + possiblePieces.get(j).getAssociatedColor() + " ");
            }
            String choose;
            do {
                choose = sc.next();
                if (!(choose.equalsIgnoreCase("red") || choose.equalsIgnoreCase("yellow") || choose.equalsIgnoreCase("blue") || choose.equalsIgnoreCase("green"))) {
                    System.out.println("inserisci uno tra i colori disponibili");
                }
            }while (!(choose.equalsIgnoreCase("red") || choose.equalsIgnoreCase("yellow") || choose.equalsIgnoreCase("blue") || choose.equalsIgnoreCase("green")));
            for(int h=0;h<possiblePieces.size();h++){
                if(possiblePieces.get(h).getAssociatedColor().equalsIgnoreCase(choose)){
                    players.get(i).setChosenPiece(possiblePieces.get(h));
                    possiblePieces.remove(h);
                }
            }
        }
    }

    //metodi che eseguono le fasi di gioco del turno

    /**
     * Setta il primo giocatore che è di turno
     */
    public void initializePhase(){
        index = 0;
        turnPhase = TurnPhase.Initialize;
        turn.setPlayerOnTurn(players.get(index));
    }

    public int throwDie(){
        turnPhase=TurnPhase.ThrowDie;
        return turn.dieLaunch();
    }

    public void chooseDirection(Direction d){
        String direction="";
        if(d==Direction.FORWARD){
            direction="cw";
        }
        else direction="ccw";
        //direction cw=senso orario ccw=senso antiorario
        turnPhase=TurnPhase.chooseDirection;
        turn.setChosenDirection(direction);
    }

    public void movePlayer(){
        turnPhase=TurnPhase.MovePlayer;
        turn.movePlayer();
    }

    public boolean checkBonusMalus(){
        return turn.checkBonusMalus();
    }

    public BonusMalusRandom executeBonusMalus(){
        turnPhase=TurnPhase.executeBonusMalus;
        BonusMalusRandom type=turn.executeBonusMalus();
        return type;
    }

    public Question visualizeQuestion(){
        turnPhase=TurnPhase.visualizeQuestion;
        return turn.visualizeQuestion();
    }

    public boolean answerQuestion(int indexOfQuestion){
        turnPhase=TurnPhase.answer;
        boolean correct=turn.AnswerQuestion(indexOfQuestion);
        return correct;
    }

    //se la risposta data è sbagliata incrementa l'indice e aggiorna il giocatore di turno
    public void setPlayerOnTurn(){
        if(!turn.getCorrectAnswer()){
            index ++;   //L'INDICE PUNTA AL GIOCATORE SUCCESSIVO
            if(index==players.size()) index=0;
            turn.setPlayerOnTurn(players.get(index));
        }
    }
    //da chiamare quando il giocatore sceglie la risposta
    public Boolean obtainSlice(){
        turnPhase=TurnPhase.obtainSlice;
        return turn.obtainSlice();
    }

    public boolean verifyVictory(){
        turnPhase=TurnPhase.victory;
        return turn.verifyVictory();
    }
    /*
    //metodo che esegue un turno di gioco e ritorna falso se un giocatore ha vinto
    public Boolean play(){
        Boolean correct=false;
        turn.dieLaunch();// LANCIA IL DADO E visualizza il risultato
        turn.movePlayer(); //muove il giocatore del risultato ottenuto
        // se la casella è bonus/malus eseguo il bonus malus prima
        if(playBoard.getSquares().get(players.get(index).getActualPosition()) instanceof BonusMalusSquare){
            turn.executeBonusMalus();
        }
        correct = turn.AnswerQuestion(); //visualizza la domanda e il giocatore risponde
        if((correct)){
            System.out.println("risposta corretta!\n");
            turn.obtainSlice();  //il metodo aggiunge lo spicchio solo se la casella corrente è una casella di domanda finale
        }
        if(!correct){
            if(players.get(index).getActualPosition() != 6 && players.get(index).getActualPosition() != 0 && players.get(index).getActualPosition() != 18 || players.get(index).getActualPosition() != 30) {
                System.out.println("risposta errata!\n");
            }
            index++;    //L'INDICE PUNTA AL GIOCATORE SUCCESSIVO
            if(index==players.size() ) index=0;
            turn.setPlayerOnTurn(players.get(index));// setto come giocatore di turno il giocatore successivo
            //correct=true;
        }
        if(turn.verifyVictory()){
            System.out.println("CONGRATULAZIONI " + players.get(index).getNickname() + "! HAI VINTO!");
            return true;
        }
        else {
            return false;
        }
    }
    */

}

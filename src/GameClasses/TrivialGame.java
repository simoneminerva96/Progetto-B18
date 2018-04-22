package GameClasses;
import java.awt.*;
import java.util.*;

/*
        classe a cui corrisponde una singola partita effettiva
    */
public class TrivialGame {
    private ArrayList<Player> players;      //giccatori partecipanti
    private ArrayList<Piece> possiblePieces;        //possibili pedine tra cui scegliere
    private Die die;        //dado
    private Board playBoard;        //tabellone di gioco

    public TrivialGame(){
        players=new ArrayList<Player>();
        possiblePieces=new ArrayList<Piece>();
        die=new Die();
        playBoard=new Board();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }


    //metodo che crea i giocatori che parteciperanno alla partita, riceve in ingresso la lista dei nickname
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

    /*metodo che esegue il lancio iniziale del dado e che ordina di conseguenza i giocatori nell'ordine in cui giocheranno
    NB: implementato facendo in modo che i 4 lanci diano risultati diversi tra loro, altrimenti si rischia che questa fase
        del gioco si prolunghi troppo in caso di continui pareggi
    */
    public void BeginningDieRoll(){
        ArrayList<Integer> launches=new ArrayList<Integer>();
        launches.add(die.Launch());
        Boolean check=false;
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
        for(int i=0;i<players.size();i++) players.get(i).setInitialRollResult(launches.get(i)); // a ogni giocatore viene associato il risultato del suo lancio
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
    private Boolean checkDifferentLaunches(ArrayList<Integer> previousLaunches, int currentLaunch){
        Boolean check=true;
        for(int i=0;i<previousLaunches.size();i++){
            if(currentLaunch==previousLaunches.get(i)) check=false;
        }
        return check;
    }

    /*
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
    //metodo che esegue il flusso dei turni di gioco
    public void play(){
        Boolean correct=false;
        Integer index=0;
        Turn turn = new Turn(players.get(index));
        do {
            turn.dieLaunch();
            correct = playBoard.getSquares().get(players.get(index).getActualPosition()).goOnIt();  //esegue la domanda corrispondente alla posizione attuale del giocatore
            //da inserire metodo che controlla la possibile vittoria del giocatore
            if(correct==false){
                System.out.println("risposta errata!\n");
                index++;
                if(index==players.size() ) index=0;
                turn.setPlayerOnTurn(players.get(index));
                correct=true;
            }
            else{
                System.out.println("risposta corretta!\n");
            }
        }while (correct==true);

    }
}

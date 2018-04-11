package GameClasses;

import java.awt.*;
import java.util.ArrayList;

/*
        classe a cui corrisponde una singola partita effettiva
    */
public class TrivialGame {
    private ArrayList<Player> players;      //giccatori partecipanti
    private ArrayList<Piece> possiblePieces;        //possibili pedine tra cui scegliere
    private Die die;        //dado

    public TrivialGame(){
        players=new ArrayList<Player>();
        possiblePieces=new ArrayList<Piece>();
        die=new Die();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Piece> getPossiblePieces() {
        return possiblePieces;
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

    //metodo che esegue il lancio iniziale del dado e che ordina di conseguenza i giocatori nell'ordine in cui giocheranno
    public void BeginningDieRoll(){
        //da implementare
    }

}

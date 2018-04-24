package GameClasses;

import java.util.ArrayList;

/*
        classe che serve a eseguire i metodi della classe partita TrivialMatch seguendo il flusso di gioco corretto
     */
public class TrivialMatchFlow {
    private TrivialGame match;
    private ArrayList<String> gamingPlayers;

    public TrivialMatchFlow(){
        match=new TrivialGame();    //partita associata
        gamingPlayers=new ArrayList<String>();
        //INSERISCO GIOCATORI DI PROVA, POI ANDRANNO INSERITI I NICKNAME DEI GIOCATORI PARTECIPANTI PASSANDOLI A QUESTO COSTRUTTORE
        gamingPlayers.add("Jack");
        gamingPlayers.add("Rita");
        gamingPlayers.add("Ste");
        gamingPlayers.add("tia");
        //fine giocatori prova
        match.initializePlayers(gamingPlayers); //inizializzo i giocatori
        match.InitializePossiblePieces();  //inizializzo le possibili pedine per la scelta
        match.BeginningDieRoll();
        match.pieceChoose();
        match.play();
    }

    //psvm che poi andrà tolto perche questa classe esegue le operazioni nel costruttore quando viene istanziata
    //verrà quindi istanziata nel main controller
    public static void main(String[] args) {

        TrivialMatchFlow prova=new TrivialMatchFlow();
    }
}

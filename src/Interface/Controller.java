package Interface;

import GameClasses.TrivialGame;

import java.util.ArrayList;

public class Controller {
    private TrivialGame match;

    public Controller(){
        match=new TrivialGame();
        ArrayList<String> gamingPlayers=new ArrayList<String>();
        //INSERISCO GIOCATORI DI PROVA, POI ANDRANNO INSERITI I NICKNAME DEI GIOCATORI PARTECIPANTI PASSANDOLI A QUESTO COSTRUTTORE
        gamingPlayers.add("Jack");
        gamingPlayers.add("Rita");
        gamingPlayers.add("Ste");
        gamingPlayers.add("tia");
        //fine giocatori prova
        match.initializePlayers(gamingPlayers); //inizializzo i giocatori
        match.InitializePossiblePieces();  //inizializzo le possibili pedine per la scelta
        match.initializePhase();
    }

    public int getDiceValue(){
       return match.throwDie();
    }
}

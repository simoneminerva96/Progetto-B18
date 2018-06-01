package Interface;

import GameClasses.Question;
import GameClasses.TrivialGame;
import java.util.ArrayList;

public class Controller {
    private TrivialGame match;

    public Controller(){
        match=new TrivialGame();
    }

    public void initializePlayers(int nplayers){
        ArrayList<String> gamingPlayers=new ArrayList<>();
        //INSERISCO GIOCATORI DI PROVA, POI ANDRANNO INSERITI I NICKNAME DEI GIOCATORI
        // PARTECIPANTI PASSANDOLI A QUESTO COSTRUTTORE
        for(int i=0;i<nplayers;i++){
            gamingPlayers.add("prova" + (i +1));
        }

        match.initializePlayers(gamingPlayers); //inizializzo i giocatori
        match.InitializePossiblePieces();  //inizializzo le possibili pedine per la scelta
        //ci sarebbe da fare la scelta della pedina qui
        match.initializePhase(); //setto il primo giocatore di turno
    }

    public int getIndex(){
        return match.getIndex();
    }

    public int getDiceValue(){
       return match.throwDie();
    }
    public void setDirection(Direction direction){
        match.chooseDirection(direction);
        match.movePlayer();
    }

    public Question getQuestion(){
        return match.visualizeQuestion();
    }

    //il giocatore dalla grafica seleziona la risposta, il metodo ritorna se è corretta o sbagliata, incrementa l'indice del giocatore
    //di turno se è sbagliata
    public boolean answerQuestion(int index){
        return match.answerQuestion(index);
    }


}
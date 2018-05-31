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
        ArrayList<String> gamingPlayers=new ArrayList<String>();
        //INSERISCO GIOCATORI DI PROVA, POI ANDRANNO INSERITI I NICKNAME DEI GIOCATORI PARTECIPANTI PASSANDOLI A QUESTO COSTRUTTORE
        for(int i=0;i<nplayers;i++){
            gamingPlayers.add("prova");
        }
        //fine giocatori prova
        match.initializePlayers(gamingPlayers); //inizializzo i giocatori
        match.InitializePossiblePieces();  //inizializzo le possibili pedine per la scelta
        match.initializePhase(); //setto il primo giocatore di turno
    }

    public int getIndex(){
        return match.getIndex();
    }

    public int getDiceValue(){
       return match.throwDie();
    }
    //metodo per settare la direzione
    public void setDirection(Direction direction){
        match.chooseDirection(direction);
        match.movePlayer();
    }

    public Question getQuestion(){
        return match.visualizeQuestion();
    }

    public boolean answerQuestion(int index){

        return match.answerQuestion(index);
    }
}

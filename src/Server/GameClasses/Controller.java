package Server.GameClasses;

import java.io.Serializable;
import java.util.ArrayList;

public class Controller implements Serializable {
    private TrivialGame match;
    private Request request;

    public Controller(){
        match=new TrivialGame();
        request = new Request();
    }

    public void initializePlayers(int nplayers){
        ArrayList<String> gamingPlayers=new ArrayList<>();
        //INSERISCO GIOCATORI DI PROVA, POI ANDRANNO INSERITI I NICKNAME DEI GIOCATORI
        // PARTECIPANTI PASSANDOLI A QUESTO COSTRUTTORE
        for(int i=0;i<nplayers;i++){
            gamingPlayers.add("prova" + (i +1));
        }
        match.initializePlayers(gamingPlayers); //inizializzo i giocatori
    }

    public void beginningDieRoll(){
        match.BeginningDieRoll();
        match.initializePhase(); //setto il primo giocatore di turno
    }
    //metodo che ritorna i risultati dei lanci dei dadi iniziali
    public ArrayList<Integer> getResultsOfRoll(){
        ArrayList<Integer> results=new ArrayList<Integer>();
        for(int i=0;i<match.getPlayers().size();i++){
            results.add(match.getPlayers().get(i).getInitialRollResult());
        }
        return results;
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

    //metodo che ritorna true se il giocatore finisce nella casella iniziale
    public boolean checkInitialSquare(){
        return match.checkInitialSquare();
    }
    public Question getQuestion(){
        return match.visualizeQuestion();
    }

    //il giocatore dalla grafica seleziona la risposta, il metodo ritorna se è corretta o sbagliata, incrementa l'indice del giocatore
    //di turno se è sbagliata
    public boolean answerQuestion(int index){
        return match.answerQuestion(index);
    }
    //metodo che aggiorna l'indice corrispondente al giocator di turno se la rispsota data è sbagliata
    public void setPlayerOnTurn(){
        match.setPlayerOnTurn();
    }

    //metodo che controlla se la casella attuale è un bonus/malus/random
    public boolean checkBonusMalus() {
        return match.checkBonusMalus();
    }

    //metodo che esegue il bonus/malus e ritorna l'effetto eseguito(nel caso del random l'effetto viene estratto nel metodo moveplayer)
    public BonusMalusRandom checkType(){
        return match.executeBonusMalus();
    }

    public ArrayList<Slice> getSliceObtained(int index){
        return match.obtainedSlices(index);
    }

    public boolean verifyVictory(){
        return match.verifyVictory();
    }

    //metodo che restituisce i nicknames ordinati(dopo il lancio del dado iniziale)
    public ArrayList<String> getOrdinatedNicknames(){
        return match.getordinatednicknames();
    }

    public boolean request (Credenziali credenziali, TypeOfRequest typeOfRequest) {
        return request.request(credenziali, typeOfRequest);
    }

    public boolean isFinalQuestion () {
        return match.isFinalQuestion();
    }

    public Categories getCategoriesOfTheSliceObtained () {
        return match.getCategoriesOfTheSliceObtained();
    }
}

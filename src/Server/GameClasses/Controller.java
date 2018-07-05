package Server.GameClasses;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe Controller tra ServerInterface e la logica di gioco.
 * - match: oggetto di tipo partita TrivialGame
 * - request: oggetto di tipo Request, può svolgere sia login che registrazione
 * @author Jacopo Ansaldi
 */
class Controller implements Serializable {
    private TrivialGame match;
    private Request request;

    Controller(){
        match=new TrivialGame();
        request = new Request();
    }

    void initializePlayers(int nplayers){
        ArrayList<String> gamingPlayers=new ArrayList<>();
        //INSERISCO GIOCATORI DI PROVA, POI ANDRANNO INSERITI I NICKNAME DEI GIOCATORI
        // PARTECIPANTI PASSANDOLI A QUESTO COSTRUTTORE
        for(int i=0;i<nplayers;i++){
            gamingPlayers.add("prova" + (i +1));
        }
        match.initializePlayers(gamingPlayers); //inizializzo i giocatori
    }

    /** Si definisce l'ordine di gioco lanciando i dadi. Inoltre, viene settato il primo giocatore di turno. */
    void beginningDieRoll(){
        match.BeginningDieRoll();
        match.initializePhase(); //setto il primo giocatore di turno
    }
    /** @return lanci del dado iniziali */
    ArrayList<Integer> getResultsOfRoll(){
        ArrayList<Integer> results=new ArrayList<>();
        for(int i=0;i<match.getPlayers().size();i++){
            results.add(match.getPlayers().get(i).getInitialRollResult());
        }
        return results;
    }

    /** @return indice del giocatore di turno */
    int getIndex(){ return match.getIndex(); }

    /** @return valore estratto dal lancio del dado */
    int getDiceValue(){ return match.throwDie(); }

    void setDirection(Direction direction){
        match.chooseDirection(direction);
        match.movePlayer();
    }

    /** @return true se il giocatore finisce nella casella iniziale */
    boolean checkInitialSquare(){ return match.checkInitialSquare(); }

    /** @return oggetto Question che contiene la domanda e le 4 risposte della casella in cui si trova il player */
    Question getQuestion(){ return match.visualizeQuestion(); }

    /** @return boolean se la risposta è corretta o sbagliata e incrementa l'indice del giocatore di turno se la risposta
    è sbagliata */
    boolean answerQuestion(int index){ return match.answerQuestion(index); }

    /** Aggiorna l'indice corrispondente al giocator di turno se la rispsota data è sbagliata */
    void setPlayerOnTurn(){ match.setPlayerOnTurn(); }

    /** @return true se la casella attuale è un bonus/malus/random */
    boolean checkBonusMalus() { return match.checkBonusMalus(); }

    /** @return il tipo se Bonus, Malus e Random ed esegue il bonus o il malus o il random */
    BonusMalusRandom checkType(){ return match.executeBonusMalus(); }

    /** @return true o false se è stata vinta la partita o no */
    boolean verifyVictory(){ return match.verifyVictory(); }

    /** @return arrayList di nickname ordinati dopo il lancio del dado */
    ArrayList<String> getOrdinatedNicknames(){ return match.getordinatednicknames(); }

    /**
     * @param credenziali Username e password dell'utente
     * @param typeOfRequest tipo della richiesta: può essere LOGIN o REGISTRAZIONE
     * @return boolean se è andata a buon fine o no
     */
    boolean request (Credenziali credenziali, TypeOfRequest typeOfRequest) { return request.request(credenziali, typeOfRequest); }

    /** @return boolean se è una domanda finale oppure no */
    boolean isFinalQuestion () { return match.isFinalQuestion(); }

    /** @return categoria dello spicchio ottenuto */
    Categories getCategoriesOfTheSliceObtained () { return match.getCategoriesOfTheSliceObtained(); }
}

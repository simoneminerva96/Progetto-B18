package Server.GameClasses.ServerInterface;

import Server.GameClasses.GameClasses.*;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe Controller tra ServerInterface e la logica di gioco.
 * - match: oggetto di tipo partita TrivialGame
 * - request: oggetto di tipo Request, può svolgere sia login che registrazione
 * @author Jacopo Ansaldi
 */
public class Controller implements Serializable {
    private TrivialGame match;
    private Request request;

    public Controller(){
        match=new TrivialGame();
        request = new Request();
    }

    /**inizializza i giocatori nella logica di gioco*/
    public void initializePlayers(int nplayers,String user1){
        ArrayList<String> gamingPlayers=new ArrayList<>();
        //INSERISCO GIOCATORI DI PROVA, POI ANDRANNO INSERITI I NICKNAME DEI GIOCATORI
        // PARTECIPANTI PASSANDOLI A QUESTO COSTRUTTORE
        gamingPlayers.add(user1);
        for(int i=1;i<nplayers;i++){
            gamingPlayers.add("player" + (i +1));
        }
        match.initializePlayers(gamingPlayers); //inizializzo i giocatori
    }

    /** Si definisce l'ordine di gioco lanciando i dadi. Inoltre, viene settato il primo giocatore di turno. */
    public void beginningDieRoll(){
        match.BeginningDieRoll();
        match.initializePhase(); //setto il primo giocatore di turno
    }
    /** @return lanci del dado iniziali */
    public ArrayList<Integer> getResultsOfRoll(){
        ArrayList<Integer> results=new ArrayList<>();
        for(int i=0;i<match.getPlayers().size();i++){
            results.add(match.getPlayers().get(i).getInitialRollResult());
        }
        return results;
    }

    /** @return indice del giocatore di turno */
    public int getIndex(){ return match.getIndex(); }

    /** @return valore estratto dal lancio del dado */
    public int getDiceValue(){ return match.throwDie(); }

    /**setta la direzione selezionata dal giocatore*/
    public void setDirection(Direction direction){
        match.chooseDirection(direction);
        match.movePlayer();
    }

    /** @return true se il giocatore finisce nella casella iniziale */
    public boolean checkInitialSquare(){ return match.checkInitialSquare(); }

    /** @return oggetto Question che contiene la domanda e le 4 risposte della casella in cui si trova il player */
    public Question getQuestion(){ return match.visualizeQuestion(); }

    /** @return boolean se la risposta è corretta o sbagliata e incrementa l'indice del giocatore di turno se la risposta
    è sbagliata */
    public boolean answerQuestion(int index){ return match.answerQuestion(index); }

    /** Aggiorna l'indice corrispondente al giocator di turno se la rispsota data è sbagliata */
    public void setPlayerOnTurn(){ match.setPlayerOnTurn(); }

    /** @return true se la casella attuale è un bonus/malus/random */
    public boolean checkBonusMalus() { return match.checkBonusMalus(); }

    /** @return il tipo se Bonus, Malus e Random ed esegue il bonus o il malus o il random */
    public BonusMalusRandom checkType(){ return match.executeBonusMalus(); }

    /** @return true o false se è stata vinta la partita o no */
    public boolean verifyVictory(){ return match.verifyVictory(); }

    /** @return arrayList di nickname ordinati dopo il lancio del dado */
    public ArrayList<String> getOrdinatedNicknames(){ return match.getordinatednicknames(); }

    /**
     * @param credenziali Username e password dell'utente
     * @param typeOfRequest tipo della richiesta: può essere LOGIN o REGISTRAZIONE
     * @return boolean se è andata a buon fine o no
     */
    public boolean request (Credenziali credenziali, TypeOfRequest typeOfRequest) { return request.request(credenziali, typeOfRequest); }

    /** @return boolean se è una domanda finale oppure no */
    public boolean isFinalQuestion () { return match.isFinalQuestion(); }

    /** @return categoria dello spicchio ottenuto */
    public Categories getCategoriesOfTheSliceObtained () { return match.getCategoriesOfTheSliceObtained(); }
}

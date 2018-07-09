package Server.GameClasses.GameClasses;

import Server.GameClasses.GameClasses.*;

import java.util.*;

/**
 * CLASSE A CUI CORRISPONDE UNA SINGOLA PARTITA DI GIOCO
 * @author Ansaldi Jacopo <jacopo.ansaldi01@universitadipavia.it>
 */
public class TrivialGame {
    private ArrayList<Player> players;      //giocatori partecipanti
    private Die die;        //dado
    private Board playBoard;        //tabellone di gioco
    //private BoardProva playBoard;    //tabellone di gioco di prova
    private Turn turn;      //turno attuale
    private Integer index; //INDICE CHE SERVE PER TENER IL CONTO DI QUALE GIOCATORE è IL TURNO

    public TrivialGame(){
        players = new ArrayList<>();
        die = new Die();
        try {
            playBoard = new Board();
        } catch (Exception e) {
            e.printStackTrace();
        }
        turn = new Turn(null,playBoard);    //all'inizio non ho alcun giocatore di turno
    }

    public ArrayList<Player> getPlayers() { return players; }

    public Integer getIndex() { return index; }

    /** metodo che crea i giocatori che parteciperanno alla partita, riceve in ingresso la lista dei nickname*/
    public void initializePlayers(ArrayList<String> nicknames){
        for (String username : nicknames) {
            players.add(new Player(username));
        }
    }

    /**metodo che esegue il lancio iniziale del dado e ordina di conseguenza i giocatori nell'ordine in cui giocheranno.
    NB: implementato facendo in modo che i 4 lanci diano risultati diversi tra loro, altrimenti si rischia
     che questa fase del gioco si prolunghi troppo in caso di continui pareggi
    */
    public void BeginningDieRoll(){
        // a ogni giocatore viene associato il risultato del suo lancio
        ArrayList<Integer> launches = new ArrayList<>(fillArray());
        for(int i=0;i<players.size();i++) players.get(i).setInitialRollResult(launches.get(i));
        //ordinamento lanci precedenti
        ArrayList<Integer> orderedLaunches=new ArrayList<>(orderLaunches(launches));
        //creazione lista ordinata dei giocatori
        orderPlayers(orderedLaunches);
    }
    /**ritorna i nickname dei giocatori dopo l'ordinamento iniziale*/
    public ArrayList<String> getordinatednicknames(){
        ArrayList<String> nicknames=new ArrayList<>();
        for (Player p : players) {
            nicknames.add(p.getNickname());
        }
        return nicknames;
    }
    /** ordina l'array in base ai risultati ottenuti
     * @param orderedLaunches risultati dei lanci ottenuti
     */
    private void orderPlayers(ArrayList<Integer> orderedLaunches){
        ArrayList<Player> orderedPlayers=new ArrayList<>();
        for(int i=0;i<orderedLaunches.size();i++){
            for(int j=0;j<players.size();j++){
                if(players.get(j).getInitialRollResult() == orderedLaunches.get(i)) orderedPlayers.add(players.get(j));
            }
        }
        players.clear();
        players.addAll(orderedPlayers);
    }
    /**metodo che riempe l'array del lanci con risultati del dado */
    private ArrayList<Integer> fillArray(){
        ArrayList<Integer> launches = new ArrayList<>();
        launches.add(die.Launch());     //aggiungo il primo lancio
        Boolean check=false;

        //ciclo che riempe l'array dei lanci facendo in modo che siano diversi
        for(int i=0;i<players.size() -1;i++){
            Integer actualLaunch = null;
            while (!check)
            {
                actualLaunch = die.Launch();
                check = checkDifferentLaunches(launches, actualLaunch);
            }
            launches.add(actualLaunch);
            check=false;
        }
        return launches;
    }

    /**@param launches lanci effettuati
     * @return array di interi ordinato in ordine decrescente
     */
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

    /**metodo che viene usato per controllare che i lanci dei giocatori siano diversi tra loro */
    private boolean checkDifferentLaunches(ArrayList<Integer> previousLaunches, int currentLaunch){
        Boolean check=true;
        for (Integer order : previousLaunches){
            if (currentLaunch== order) {
                check = false;
            }
        }
        return check;
    }

    //metodi che eseguono le fasi di gioco del turno

    /**Setta il primo giocatore che è di turno */
    public void initializePhase(){
        index = 0;
        turn.setPlayerOnTurn(players.get(index));
    }

    /** effettua il lancio del dado */
    public int throwDie(){ return turn.dieLaunch(); }

    public void chooseDirection(Direction d){ turn.setChosenDirection(d); }

    public void movePlayer(){ turn.movePlayer(); }

    public boolean checkInitialSquare(){
        boolean check=turn.checkInitialSquare();
        if(check) turn.setCorrectAnswer(false);
        return check;
    }

    public boolean checkBonusMalus(){ return turn.checkBonusMalus(); }

    public BonusMalusRandom executeBonusMalus(){ return turn.executeBonusMalus(); }

    public Question visualizeQuestion(){ return turn.visualizeQuestion(); }

    public boolean answerQuestion(int indexOfQuestion){ return turn.AnswerQuestion(indexOfQuestion); }

    /**Se la risposta data è sbagliata incrementa l'indice e aggiorna il giocatore di turno*/
    public void setPlayerOnTurn(){
        if(!turn.getCorrectAnswer()){
            incrementIndex();
            turn.setPlayerOnTurn(players.get(index));
        }
    }
    /**incrementa l'indice corrispondente al giocatore di turno*/
    private void incrementIndex(){
        index ++;
        if(index==players.size()) index=0;
    }

    public boolean verifyVictory(){ return turn.verifyVictory(); }

    public boolean isFinalQuestion () { return turn.isFinalQuestion(); }

    public Categories getCategoriesOfTheSliceObtained () {
        return turn.getCategoriesOfTheSliceObtained();
    }
}

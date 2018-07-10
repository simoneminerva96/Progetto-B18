package Server.GameClasses.GameClasses;

import Server.GameClasses.GameClasses.Squares.*;

/**
 * Classe che corrisponde a un turno di gioco, effettua la movimentazione delle pedine sul tabellone
 * @author Ansaldi Jacopo <jacopo.ansaldi01@universitadipavia.it>
 */

public class Turn {
    private final static int NSQUARES=36;
    //private BoardProva playBoard;    //tabellone di gioco di prova, da utilizzare quando è possibile connettersi al db
    private Board playBoard;    //tabellone di gioco connesso al db
    private Player playerOnTurn;  //giocatore di turno
    private Die die;        //dado
    private int dieresult;  //risultato del lancio del dado
    private Direction chosenDirection;  //direzione scelta
    private boolean correctAnswer; //booleano che mi indica se la risposta data dall'utente è giusta o sbagliata

    private boolean isFinalQuestion;
    private Categories categoriesOfTheSliceObtained;

    public Turn(Player playerOnTurn,Board playBoard){
        this.playerOnTurn=playerOnTurn;
        this.playBoard=playBoard;
        die=new Die();
        dieresult=0;
        correctAnswer=true; //viene inizializzato a true perchè sulla casella iniziale è previsto di perder il turno, se fosse
        //inizializzato a false durante il turno iniziale il giocatore perderebbe subito il turno senza lanciar il dado
    }

    /**metodo da chiamare in @see TrivialGame per cambiar turno */
    public void setPlayerOnTurn(Player playerOnTurn) { this.playerOnTurn = playerOnTurn; }

    public void setCorrectAnswer(boolean correctAnswer) { this.correctAnswer = correctAnswer; }


    /**metodo che effettua il lancio del dado*/
    public int dieLaunch(){
        dieresult=die.Launch();
        return dieresult;
    }

    /**setta la direzione scelta dal giocatore */
    public void setChosenDirection(Direction direction){ this.chosenDirection=direction; }

    /**metodo che muove la pedina del risultato del dado nella direzione scelta */
    public void movePlayer(){
        if(chosenDirection.equals(Direction.FORWARD)){
            if(playerOnTurn.getActualPosition() + dieresult >= NSQUARES){
                Integer position=playerOnTurn.getActualPosition() + dieresult -NSQUARES;
                playerOnTurn.setActualPosition(position);
            }
            else playerOnTurn.setActualPosition(playerOnTurn.getActualPosition() + dieresult);
        }
        else if(chosenDirection.equals(Direction.BACK)){
            if(playerOnTurn.getActualPosition() - dieresult < 0){
                Integer excess= -1*(playerOnTurn.getActualPosition() - dieresult);
                playerOnTurn.setActualPosition(NSQUARES-excess);
            }
            else playerOnTurn.setActualPosition(playerOnTurn.getActualPosition() - dieresult);
        }
        //se finisco sulla casella random estraggo un effetto
        if(playerOnTurn.getActualPosition() == 18){
            extractEffectType();
        }
    }


    /**metodo che estrae l'effetto che avrà la casella random quando ci finisci sopra */
    private void extractEffectType(){
        RandomSquare currentSquare= (RandomSquare) getcurrentSquare();
        currentSquare.extractEffectType();
    }

    /**ritorna la casella attuale*/
    private Square getcurrentSquare(){
        int currentPosition=playerOnTurn.getActualPosition();
        return playBoard.getSquares().get(currentPosition);
    }

    /**ritorna true se la casella attuale è la casella iniziale*/
    public boolean checkInitialSquare(){ return getcurrentSquare() instanceof InitialSquare; }

    /**ritorna true se la casella attuale è una bonus malus o random*/
    public boolean checkBonusMalus(){ return getcurrentSquare() instanceof  BonusMalusRandomSquare; }

    /**ritorna l'effetto che viene eseguito dalla casella */
    public BonusMalusRandom executeBonusMalus(){
        Square currentSquare=getcurrentSquare();
        if(currentSquare instanceof BonusMalusRandomSquare){
            return ((BonusMalusRandomSquare) currentSquare).executeBonusMalus(this);
        }
        else return null;
    }

    public Question visualizeQuestion(){
        Square currentSquare=getcurrentSquare();
        return currentSquare.visualizeQuestion();
    }

    /**metodo che permette al giocatore di rispondere */
    public Boolean AnswerQuestion(int indexOfAnswer){
        Square currentSquare=getcurrentSquare();
        correctAnswer=currentSquare.goOnIt(indexOfAnswer);
        if(correctAnswer){
            this.obtainSlice();//METODO CHE AGGIUNGE AL GIOCATORE LO SPICCHIO DELLA CATEGORIA SE LA DOMANDA è UNA DOM. FINALE
        }
        return correctAnswer;
    }

    public boolean getCorrectAnswer(){ return this.correctAnswer; }

    /**AGGIUNGE LO SPICCHIO AL GIOCATORE SE LA DOMANDA è UNA DOMANDA FINALE*/
    public void obtainSlice(){
        Square actualSquare=getcurrentSquare();
        if(actualSquare instanceof FinalQuestionSquare){
            isFinalQuestion = true;
            Categories categoryOfTheSlice=((FinalQuestionSquare)actualSquare).getCategory();
            playerOnTurn.obtainSlice(categoryOfTheSlice);
            categoriesOfTheSliceObtained = categoryOfTheSlice;
        }
        else isFinalQuestion= false;
    }
    /**verifica la vittoria del giocatore di turno*/
    public Boolean verifyVictory(){ return playerOnTurn.getSlicesObtained().size() == 6 && playerOnTurn.getActualPosition() ==0; }

    /**ritorna true se la casella attuale è una casella corrispondente a una domanda finale */
    public boolean isFinalQuestion() { return isFinalQuestion; }
    /**ritorna la categoria dello spicchio ottenuto*/
    public Categories getCategoriesOfTheSliceObtained () { return categoriesOfTheSliceObtained; }
}

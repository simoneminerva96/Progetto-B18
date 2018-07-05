package Server.GameClasses;

import Server.GameClasses.Squares.*;

/**
 * CLASSE CHE CORRISPONDE A UN TURNO DI GIOCO, effettua la movimentazione delle pedine sul tabellone
 * @author Ansaldi Jacopo <jacopo.ansaldi01@universitadipavia.it>
 */

public class Turn {
    private final static int NSQUARES=36;
    //private BoardProva playBoard;    //tabellone di gioco di prova
    private Board playBoard;    //tabellone di gioco connesso al db
    private Player playerOnTurn;        //giocatore di turno
    private Die die;        //dado per determinare di quanto spostarsi
    private int dieresult;  //risultato del lancio del dado
    private Direction chosenDirection;
    private boolean correctAnswer= true; //booleano che mi indica se la risposta data dall'utente è giusta o sbagliata

    private boolean isFinalQuestion;
    private Categories categoriesOfTheSliceObtained;

    public Turn(Player playerOnTurn,Board playBoard){
        this.playerOnTurn=playerOnTurn;
        this.playBoard=playBoard;
        die=new Die();
        dieresult=0;
    }
    //metodo da chiamare nella classe trivialgame per cambiar turno
    public void setPlayerOnTurn(Player playerOnTurn) {
        this.playerOnTurn = playerOnTurn;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    //metodo che effettua il lancio del dado
    public int dieLaunch(){
        dieresult=die.Launch();
        return dieresult;
    }

    //setta la direzione scelta dal giocatore
    public void setChosenDirection(Direction direction){
        this.chosenDirection=direction;
    }

    //metodo che muove la pedina del risultato del dado nella direzione scelta
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
    //metodo che estrae l'effetto che avrà la casella random quando ci finisci sopra
    public void extractEffectType(){
        RandomSquare currentSquare= (RandomSquare) getcurrentSquare();
        currentSquare.extractEffectType();
    }
    private Square getcurrentSquare(){
        int currentPosition=playerOnTurn.getActualPosition();
        Square currentSquare=playBoard.getSquares().get(currentPosition);
        return currentSquare;
    }
    public boolean checkInitialSquare(){
        return getcurrentSquare() instanceof InitialSquare;
    }

    public boolean checkBonusMalus(){
        return getcurrentSquare() instanceof  BonusMalusRandomSquare;
    }

    //ritorna l'effetto che viene eseguito dalla casella
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

    //metodo che permette al giocatore di rispondere
    public Boolean AnswerQuestion(int indexOfAnswer){
        Square currentSquare=getcurrentSquare();
        correctAnswer=currentSquare.goOnIt(indexOfAnswer);
        if(correctAnswer){
            this.obtainSlice();//METODO CHE AGGIUNGE AL GIOCATORE LO SPICCHIO DELLA CATEGORIA SE LA DOMANDA è UNA DOM. FINALE
        }
        return correctAnswer;
    }

    public boolean getCorrectAnswer(){
        return this.correctAnswer;
    }

    //RITORNA TRUE SE IL GIOCATORE HA OTTENUTO LO SPICCHIO
    private void obtainSlice(){
        Square actualSquare=getcurrentSquare();
        if(actualSquare instanceof FinalQuestionSquare){
            isFinalQuestion = true;
            Categories categoryOfTheSlice=((FinalQuestionSquare)actualSquare).getCategory();
            playerOnTurn.obtainSlice(categoryOfTheSlice);
            categoriesOfTheSliceObtained = categoryOfTheSlice;
        }
        else isFinalQuestion = false;
    }

    public Boolean verifyVictory(){
        if(playerOnTurn.getSlicesObtained().size() == 6 && playerOnTurn.getActualPosition() ==0){
            return true;
        }
        else return false;
    }


    public boolean isFinalQuestion() {
        return isFinalQuestion;
    }

    public Categories getCategoriesOfTheSliceObtained () {
        return categoriesOfTheSliceObtained;
    }
}

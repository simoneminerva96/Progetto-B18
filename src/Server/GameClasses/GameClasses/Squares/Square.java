package Server.GameClasses.GameClasses.Squares;

import Server.GameClasses.GameClasses.Question;
import java.util.ArrayList;
import java.util.Random;
/**
 *  Classe che corrisponde a una singola casella di gioco
 *  @author Ansaldi Jacopo <jacopo.ansaldi01@universitadipavia.it>
 */
public class Square {
    private Integer index;      //indice che identifica ogni casella, da 0 a 40, partendo dallo start(0) conteggiati in senso orario
    private ArrayList<Question> possibleQuestions;      //POSSIBILI DOMANDE
    private Question extractedQuestion;

    public Square(Integer index){
        this.index = index;
        possibleQuestions = new ArrayList<>();
        this.extractedQuestion = null;
    }

    public Integer getIndex() {
        return index;
    }

    /**
     * metodo per inizializzare le possibili domande ( da prendere dal db quando inizializzo la partita)
     * @param possibleQuestions possibili domande
     *
     */
    public void setPossibleQuestions(ArrayList<Question> possibleQuestions){
        this.possibleQuestions.addAll(possibleQuestions);
    }

    /**
     * Metodo che restituisce la domanda estratta. Genero un numero casuale tra 0 e numero massimo di
     * domande disponibili. Il numero generato corrisponderà all'indice della domanda da effettuare.
     */
    public Question visualizeQuestion(){
        Random questionChooser=new Random();
        int max=possibleQuestions.size() -1 ;
        int range = ((max) + 1);
        int indexOfQuestion = questionChooser.nextInt(range);
        this.extractedQuestion = possibleQuestions.get(indexOfQuestion);
        return extractedQuestion;
    }

    /**
     * @param indexOfAnswer
     * @return booleano che indica se la risposta è corretta o no
     */
    public boolean goOnIt(int indexOfAnswer){
        return extractedQuestion.getAnswers().get(indexOfAnswer).getCorrect();
    }
}

package Server.GameClasses;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * classe che rappresenta una domanda
 * le domande finali a livello di classi sono sempre domande in quanto non hanno nessuna caratteristica
 * aggiuntiva se non la difficoltà
 * @author Ansaldi Jacopo <jacopo.ansaldi01@universitadipavia.it>
 */

public class Question implements Serializable {
    private String question;
    private ArrayList<Answer> answers;

    public Question(String question,ArrayList<Answer> answers) {
        this.question = question;
        this.answers = new ArrayList<>();
        this.answers.addAll(answers);
    }

    public String getQuestion() {
        return question;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }
}

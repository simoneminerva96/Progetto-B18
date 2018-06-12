package Server.GameClasses;

import java.util.ArrayList;

/**
 * classe a cui corrisponde una domanda
 * le domande finali a livello di classi sono sempre domande in quanto non hanno nessuna caratteristica
 * aggiuntiva se non la difficoltà
 * @author Ansaldi Jacopo <jacopo.ansaldi01@universitadipavia.it>
 */

public class Question {
    private String question;
    private Categories category;
    private ArrayList<Answer> answers;

    public Question(String question,Categories category,ArrayList<Answer> answers) {
        this.question = question;
        this.category = category;
        this.answers = new ArrayList<>();
        this.answers.addAll(answers);
    }

    public String getQuestion() {
        return question;
    }

    public Categories getCategory() {
        return category;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }
}

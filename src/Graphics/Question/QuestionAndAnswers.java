package Graphics.Question;

import Graphics.Question.Answers;

import java.util.ArrayList;

/**
 * @author Rita
 *
 * La classe QuestionAndAnswers contiene:
 * - question: stringa della domanda
 * - a, a1, a2, a3: oggetti di tipo risposta {@see Answers}
 * - answered: flag che indica se ho risposto
 */

//DA TOGLIERE
public class QuestionAndAnswers {
    private String question ="Qual è la capitale dell'Italia?";
    private ArrayList<Answers> a;
    private boolean answered = false;

    public QuestionAndAnswers() {
        a = new ArrayList<>();
        a.clear();
    }

    public void setAnswer (String answer, boolean esito) {
        a.add(new Answers(answer,esito));
    }

    public Answers getAnswer(int index) {
        return a.get(index);
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public String getQuestion() {
        return question;
    }


    public boolean isAnswered() {
        return answered;
    }
}

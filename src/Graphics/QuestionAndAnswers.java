package Graphics;

/**
 * @author Rita
 *
 * La classe QuestionAndAnswers contiene:
 * - question: stringa della domanda
 * - a, a1, a2, a3: oggetti di tipo risposta {@see Answers}
 * - answered: flag che indica se ho risposto
 */
public class QuestionAndAnswers {
    private String question ="Qual è la capitale dell'Italia?";
    private Answers a = new Answers("Roma", true);
    private Answers a1 = new Answers("Milano", false);
    private Answers a2 = new Answers("Firenze", false);
    private Answers a3 = new Answers("Napoli", false);
    private boolean answered = false;

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public String getQuestion() {
        return question;
    }

    public Answers getA() {
        return a;
    }

    public Answers getA1() {
        return a1;
    }

    public Answers getA2() {
        return a2;
    }

    public Answers getA3() {
        return a3;
    }

    public boolean isAnswered() {
        return answered;
    }
}

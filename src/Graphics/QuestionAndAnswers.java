package Graphics;

public class QuestionAndAnswers {
    String question ="Qual è la capitale dell'Italia?";
    Answers a = new Answers("Roma", true);
    Answers a1 = new Answers("Milano", false);
    Answers a2 = new Answers("Firenze", false);
    Answers a3 = new Answers("Napoli", false);
    boolean answered = false;

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }
}

package Graphics;

public class questionAndAnswers {
    static String question ="Qual è la capitale dell'Italia?";
    static Answers a = new Answers("Roma", true);
    static Answers a1 = new Answers("Milano", false);
    static Answers a2 = new Answers("Firenze", false);
    static Answers a3 = new Answers("Napoli", false);
    static boolean answered = false;

    public static boolean isAnswered() {
        return answered;
    }

    public static void setAnswered(boolean answered) {
        questionAndAnswers.answered = answered;
    }
}

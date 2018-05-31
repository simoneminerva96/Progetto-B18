package Graphics.Question;

/**
 * @author Rita
 * Classe Answer che rappresenta una risposta. E' caratterizzata da:
 * - answer: la risposta
 * - esito: true se è la risposta corretta, false se la risposta è sbagliata
 */
//DA TOGLIERE
public class Answers {
    private String answer;
    private boolean esito;

    public Answers(String answer, boolean esito) {
        this.answer = answer;
        this.esito = esito;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isEsito() {
        return esito;
    }

}

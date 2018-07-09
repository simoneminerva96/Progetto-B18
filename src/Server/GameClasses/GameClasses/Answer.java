package Server.GameClasses.GameClasses;

import java.io.Serializable;

/**
 * Classe che corrisponde a una possibile risposta a una domanda
 * @author Ansaldi Jacopo <jacopo.ansaldi01@universitadipavia.it>
 */
public class Answer implements Serializable{
    private String answer;  //stringa contenente la risposta
    private boolean correct;    //indica se la rispota è corretta

    public Answer(String answer,Boolean correct){
        this.answer=answer;
        this.correct=correct;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public String getAnswer() {
        return answer;
    }
}

package Server.GameClasses;

import java.io.Serializable;

/**
 * classe che corrisponde a una possibile risposta a una domanda
 * @author Ansaldi Jacopo <jacopo.ansaldi01@universitadipavia.it>
 */
public class Answer implements Serializable{
    private String answer;
    private boolean correct;

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

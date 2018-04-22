package GameClasses;

public class Answer {
    private String answer;
    private Boolean correct;

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

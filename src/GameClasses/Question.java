package GameClasses;

import java.util.ArrayList;

public class Question {
    private String question;
    private Categories category;
    private ArrayList<Answer> answers;

    public Question(String question,Categories category,ArrayList<Answer> answers) {
        this.question = question;
        this.category = category;
        this.answers = new ArrayList<Answer>();
        this.answers.addAll(answers);
    }


}

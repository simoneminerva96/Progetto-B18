package GameClasses.Squares;

import GameClasses.Answer;
import GameClasses.Question;
import GameClasses.Square;
import java.util.ArrayList;

public class InitialSquare extends Square {
    private Question question; //domanda di prova estratta quando vado su casella bonus malus (DA TOGLIERE)

    public InitialSquare(Integer index) {
        super(index);
    }
    //non viene fatta nessuna domanda sulla casella iniziale
    @Override
    public boolean goOnIt(int indexOfAnswer) {
        return question.getAnswers().get(indexOfAnswer).getCorrect();
    }

    @Override
    public Question visualizeQuestion() {
        ArrayList<Answer> a=new ArrayList<>();
        a.add(new Answer("vuota1",true));
        a.add(new Answer("vuota1",false));
        a.add(new Answer("vuota1",false));
        a.add(new Answer("vuota1",false));
        Question vuota=new Question("CASELLA INIZIALE!",null,a);
        question=vuota;
        return question;
    }
}

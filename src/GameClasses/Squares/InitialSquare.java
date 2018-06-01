package GameClasses.Squares;

import GameClasses.Answer;
import GameClasses.Question;
import GameClasses.Square;
import java.util.ArrayList;

public class InitialSquare extends Square {

    public InitialSquare(Integer index) {
        super(index);
    }
    //non viene fatta nessuna domanda sulla casella iniziale
    @Override
    public boolean goOnIt(int indexOfAnswer) {
        return false;
    }

    @Override
    public Question visualizeQuestion() {
        ArrayList<Answer> a=new ArrayList<>();
        a.add(new Answer("vuota1",true));
        a.add(new Answer("vuota1",false));
        a.add(new Answer("vuota1",false));
        a.add(new Answer("vuota1",false));
        Question vuota=new Question("CASELLA INIZIALE!",null,a);
        return vuota;
    }
}

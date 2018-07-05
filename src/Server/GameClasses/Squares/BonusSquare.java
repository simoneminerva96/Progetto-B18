package Server.GameClasses.Squares;

import Server.GameClasses.BonusMalusRandom;
import Server.GameClasses.Turn;

public class BonusSquare extends BonusMalusRandomSquare {

    public BonusSquare(Integer index) {
        super(index);
    }

    public boolean goOnIt (int indexOfAnswer) {
        return false;
    }

    public BonusMalusRandom executeBonusMalus (Turn t){
        t.setCorrectAnswer(true);
        return BonusMalusRandom.BONUS;
    }
}

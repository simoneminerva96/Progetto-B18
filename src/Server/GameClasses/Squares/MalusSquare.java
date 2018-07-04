package Server.GameClasses.Squares;

import Server.GameClasses.BonusMalusRandom;
import Server.GameClasses.Turn;

public class MalusSquare extends BonusMalusRandomSquare {

    public MalusSquare(Integer index) {
        super(index);
    }

    public boolean goOnIt (int indexOfAnswer) {
        return false;
    }

    public BonusMalusRandom executeBonusMalus (Turn t){
        t.setCorrectAnswer(false);
        return BonusMalusRandom.MALUS;
    }
}

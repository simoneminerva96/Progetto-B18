package Server.GameClasses.Squares;

import Server.GameClasses.Interface.BonusMalusRandom;
import Server.GameClasses.Turn;

public class BonusSquare extends BonusMalusRandomSquare {

    public BonusSquare(Integer index, BonusMalusRandom squareType) {
        super(index, squareType);
    }

    public boolean goOnIt (int indexOfAnswer) {
        return false;
    }

    public BonusMalusRandom executeBonusMalus (Turn t){
        return getSquareType();
    }
}

package Server.GameClasses.Squares;

import Server.GameClasses.BonusMalusRandom;
import Server.GameClasses.Turn;

public abstract class BonusMalusRandomSquare extends Square {

    public BonusMalusRandomSquare(Integer index) {
        super(index);
    }

    public abstract boolean goOnIt (int indexOfAnswer);

    public abstract BonusMalusRandom executeBonusMalus (Turn t);
}

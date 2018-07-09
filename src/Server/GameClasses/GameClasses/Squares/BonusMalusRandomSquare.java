package Server.GameClasses.GameClasses.Squares;

import Server.GameClasses.GameClasses.BonusMalusRandom;
import Server.GameClasses.GameClasses.Turn;

/**
 * classe astratta estesa da bonus malus e random
 */

public abstract class BonusMalusRandomSquare extends Square {

    public BonusMalusRandomSquare(Integer index) {
        super(index);
    }

    public abstract boolean goOnIt (int indexOfAnswer);

    public abstract BonusMalusRandom executeBonusMalus (Turn t);
}

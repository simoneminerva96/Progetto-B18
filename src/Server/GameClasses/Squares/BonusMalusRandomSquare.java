package Server.GameClasses.Squares;

import Server.GameClasses.BonusMalusRandom;
import Server.GameClasses.Turn;

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

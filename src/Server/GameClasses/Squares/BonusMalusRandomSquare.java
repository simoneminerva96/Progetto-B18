package Server.GameClasses.Squares;

import Server.GameClasses.Interface.BonusMalusRandom;
import Server.GameClasses.Square;
import Server.GameClasses.Turn;

public abstract class BonusMalusRandomSquare extends Square {

    public BonusMalusRandomSquare(Integer index) {
        super(index);
    }

    public boolean goOnIt (int indexOfAnswer) {
        return false;
    }

    public void extractEffectType() {}

    public BonusMalusRandom executeBonusMalus (Turn t){return null;}
}

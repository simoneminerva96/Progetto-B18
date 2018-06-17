package Server.GameClasses.Squares;

import Server.GameClasses.Interface.BonusMalusRandom;
import Server.GameClasses.Square;
import Server.GameClasses.Turn;

public class BonusMalusRandomSquare extends Square {

    private BonusMalusRandom squareType;

    public BonusMalusRandomSquare(Integer index, BonusMalusRandom squareType) {
        super(index);
        this.squareType = squareType;
    }

    public boolean goOnIt (int indexOfAnswer) {
        return false;
    }

    public BonusMalusRandom getSquareType() {
        return squareType;
    }

    public void setSquareType(BonusMalusRandom squareType) {
        this.squareType = squareType;
    }

}

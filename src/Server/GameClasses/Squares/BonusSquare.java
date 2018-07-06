package Server.GameClasses.Squares;

import Server.GameClasses.BonusMalusRandom;
import Server.GameClasses.Turn;
/**
 casella bonus
 */

public class BonusSquare extends BonusMalusRandomSquare {

    public BonusSquare(Integer index) {
        super(index);
    }

    public boolean goOnIt (int indexOfAnswer) {
        return false;
    }

    public BonusMalusRandom executeBonusMalus (Turn t){
        t.setCorrectAnswer(true);   //cosi il giocatore non cambia turno
        return BonusMalusRandom.BONUS;
    }
}

package Server.GameClasses.GameClasses.Squares;

import Server.GameClasses.GameClasses.BonusMalusRandom;
import Server.GameClasses.GameClasses.Turn;
/**
    casella malus
 */
public class MalusSquare extends BonusMalusRandomSquare {

    public MalusSquare(Integer index) {
        super(index);
    }

    public boolean goOnIt (int indexOfAnswer) {
        return false;
    }

    public BonusMalusRandom executeBonusMalus (Turn t){
        t.setCorrectAnswer(false);  //cosi il giocatore passa il turno
        return BonusMalusRandom.MALUS;
    }
}

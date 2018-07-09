package Server.GameClasses.GameClasses.Squares;

import Server.GameClasses.GameClasses.BonusMalusRandom;
import Server.GameClasses.GameClasses.Turn;
import java.util.Random;

/**
 * casella random
 */

public class RandomSquare extends BonusMalusRandomSquare {
    private BonusMalusRandom squareType;

    public RandomSquare(Integer index) {
        super(index);
        this.squareType = BonusMalusRandom.RANDOM;
    }

    public boolean goOnIt (int indexOfAnswer) {
        return false;
    }

    /**ogni volta che il giocatore passa su questa casella viene estratto un effetto*/
    public void extractEffectType() {
        Random generator = new Random();
        int min = 1;
        int max = 2;
        int range = ((max - min) + 1);
        int result = generator.nextInt(range) + min;
        switch (result){
            case 1:
                squareType = BonusMalusRandom.BONUS;
                break;
            case 2:
                squareType = BonusMalusRandom.MALUS;
                break;
        }
    }

    public BonusMalusRandom executeBonusMalus(Turn t){
        switch (squareType){
            case BONUS:
                t.setCorrectAnswer(true);
                break;
            case MALUS:
                t.setCorrectAnswer(false);
                break;
        }
        return squareType;
    }
}

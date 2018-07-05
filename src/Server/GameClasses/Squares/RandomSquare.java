package Server.GameClasses.Squares;

import Server.GameClasses.BonusMalusRandom;
import Server.GameClasses.Turn;
import java.util.Random;

public class RandomSquare extends BonusMalusRandomSquare {
    private BonusMalusRandom squareType;

    public RandomSquare(Integer index) {
        super(index);
        this.squareType = BonusMalusRandom.RANDOM;
    }

    public boolean goOnIt (int indexOfAnswer) {
        return false;
    }

    public void extractEffectType() {
        if(squareType.equals(BonusMalusRandom.RANDOM)){
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
    }

    public BonusMalusRandom executeBonusMalus(Turn t){
        switch (squareType){
            case BONUS:
                break;
            case MALUS:
                t.setCorrectAnswer(false);
                break;
        }
        return squareType;
    }
}

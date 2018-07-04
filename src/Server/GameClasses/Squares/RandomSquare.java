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
            int min = 1; // numero minimo
            int max = 2; // numero massimo
            int range = ((max - min) + 1);
            int result = generator.nextInt(range) + min; //estrae un numero da 1 a 2 per estrarre a caso il tipo di bonus/malus

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

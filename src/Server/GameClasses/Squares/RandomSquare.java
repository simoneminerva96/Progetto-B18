package Server.GameClasses.Squares;

import Server.GameClasses.Interface.BonusMalusRandom;
import Server.GameClasses.Turn;

import java.util.Random;

public class RandomSquare extends BonusMalusRandomSquare {

    public RandomSquare(Integer index, BonusMalusRandom squareType) {
        super(index, squareType);
    }

    public boolean goOnIt (int indexOfAnswer) {
        return false;
    }

    public void extractEffectType() {
        if(super.getSquareType().equals(BonusMalusRandom.Random)){
            Random generator = new Random();
            int min = 1; // numero minimo
            int max = 2; // numero massimo
            int range = ((max - min) + 1);
            int result = generator.nextInt(range) + min; //estrae un numero da 1 a 2 per estrarre a caso il tipo di bonus/malus

            switch (result){
                case 1:
                    super.setSquareType(BonusMalusRandom.Bonus);
                    break;
                case 2:
                    super.setSquareType(BonusMalusRandom.Malus);
                    break;
            }
        }
    }

    public BonusMalusRandom executeBonusMalus(Turn t){
        switch (getSquareType()){
            case Bonus:
                break;
            case Malus:
                t.setCorrectAnswer(false);
                break;
        }
        return getSquareType();
    }
}

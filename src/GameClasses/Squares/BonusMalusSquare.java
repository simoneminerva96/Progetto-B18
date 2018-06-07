package GameClasses.Squares;
import GameClasses.*;
import Interface.BonusMalusRandom;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*
    CLASSE CHE CORRISPONDE ALLA CASELLE BONUS MALUS
 */
public class BonusMalusSquare extends Square {
    private BonusMalusRandom squareType;
    private BonusMalusRandom effectType; //indica se verrà eseguito bonus o malus, nel caso del random cambia valore ogni volta
    private Question question; //domanda di prova estratta quando vado su casella bonus malus (DA TOGLIERE)

    public BonusMalusSquare(Integer index,BonusMalusRandom bonusMalusType){
        super(index);
        this.squareType=bonusMalusType;
        if(!(squareType.equals(BonusMalusRandom.Random))) effectType=squareType;
        else effectType=null;
    }

    @Override
    public boolean goOnIt(int indexofAnswer) {
        return false;
    }

   //estrae bonus o malus sulla casella random
    public void extractEffectType() {
        if (squareType.equals(BonusMalusRandom.Random)) {
            // se esce il "random" la casella avrà l'effetto di uno dei bonus/malus a caso
            Random generator = new Random();
            int min = 1; // numero minimo
            int max = 2; // numero massimo
            int range = ((max - min) + 1);
            int result = generator.nextInt(range) + min; //estrae un numero da 1 a 2 per estrarre a caso il tipo di bonus/malus
            switch (result) {
                case 1:
                    effectType = BonusMalusRandom.Bonus;
                    break;
                case 2:
                    effectType = BonusMalusRandom.Malus;
                    break;
            }
        }
    }

    //esegue il bonus/malus
    public BonusMalusRandom executeBonusMalus(Turn t){
        //switch che esegue il bonus/malus
        switch (effectType){
            case Bonus: {
                break;
            }
            case Malus: {
                t.setCorrectAnswer(false); //cosi incremento l'indice e passa il turno
                break;
            }
        }
        return effectType;
    }

}

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

    //NON USO PATTERN STRATEGY PERCHè NON AVRO NECESSITà DI AGGIUNGERE ALTRI BONUS MALUS

    public BonusMalusRandom executeBonusMalus(Turn t){
        // se esce il "random" la casella avrà l'effetto di uno dei bonus/malus a caso
        if(squareType.equals(BonusMalusRandom.Random))
        {
            Random generator=new Random();
            int min = 1; // numero minimo
            int max=2 ; // numero massimo
            int range = ((max-min) + 1);
            int result= generator.nextInt(range) + min; //estrae un numero da 1 a 2 per estrarre a caso il tipo di bonus/malus
            switch (result){
                case 1: {
                    effectType = BonusMalusRandom.Bonus;
                    break;
                }
                case 2: {
                    effectType = BonusMalusRandom.Malus;
                    break;
                }
            }
        }
        //switch che esegue il bonus/malus
        switch (effectType){
            case Bonus: {
                System.out.println("CASELLA BONUS! PUOI LANCIARE NUOVAMENTE IL DADO!");
                t.setCorrectAnswer(true);
                break;
            }
            case Malus: {
                System.out.println("CASELLA MALUS! PASSI IL TURNO");
                // non fa nulla perchè poi tornando al metodo play verrà chiamato il metodo goOnIt su questa casella
                //che ritorna falso in ogni caso
                t.setCorrectAnswer(false); //cosi incremento l'indice e passa il turno
                break;
            }
        }
        return effectType;
    }

    /*@Override
    public Question visualizeQuestion() {
        ArrayList<Answer> a=new ArrayList<>();
        a.add(new Answer("vuota1",true));
        a.add(new Answer("vuota2",false));
        a.add(new Answer("vuota3",false));
        a.add(new Answer("vuota4",false));
        Question vuota=new Question("CASELLA BONUS/MALUS!",null,a);
        this.question=vuota;
        return question;
    }*/
}

package GameClasses.Squares;
import GameClasses.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*
    CLASSE CHE CORRISPONDE ALLA CASELLE BONUS MALUS
 */
public class BonusMalusSquare extends Square {
    private String bonusMalusType;

    public BonusMalusSquare(Integer index,String bonusMalusType){
        super(index);
        this.bonusMalusType=bonusMalusType;
    }

    public String getBonusMalusType() {
        return bonusMalusType;
    }

    @Override
    public Boolean goOnIt(int indexofAnswer) {
        return false;
    }

    //NON USO PATTERN STRATEGY PERCHè NON AVRO NECESSITà DI AGGIUNGERE ALTRI BONUS MALUS

    public void executeBonusMalus(Turn t){
        String typeBonusMalus=bonusMalusType;
        // se esce il "random" la casella avrà l'effetto di uno dei bonus/malus a caso
        if(typeBonusMalus.equalsIgnoreCase("random"))
        {
            Random generator=new Random();
            int min = 1; // numero minimo
            int max=2 ; // numero massimo
            int range = ((max-min) + 1);
            int result= generator.nextInt(range) + min; //estrae un numero da 1 a 2 per estrarre a caso il tipo di bonus/malus
            switch (result){
                case 1:
                    typeBonusMalus="bonus";
                    break;
                case 2:
                    typeBonusMalus="malus";
                    break;
            }
        }
        //switch che esegue il bonus/malus
        switch (typeBonusMalus){
            case("bonus") :
                System.out.println("CASELLA BONUS! PUOI LANCIARE NUOVAMENTE IL DADO!");
                t.dieLaunch();
                t.movePlayer();
                break;
            case("malus"):
                System.out.println("CASELLA MALUS! PASSI IL TURNO");
                // non fa nulla perchè poi tornando al metodo play verrà chiamato il metodo goOnIt su questa casella
                //che ritorna falso in ogni caso
                break;
        }
    }

    @Override
    public Question visualizeQuestion() {
        ArrayList<Answer> a=new ArrayList<>();
        a.add(new Answer("vuota1",false));
        a.add(new Answer("vuota1",false));
        a.add(new Answer("vuota1",false));
        a.add(new Answer("vuota1",false));
        Question vuota=new Question("CASELLA BONUS/MALUS!",null,a);
        return vuota;
    }
}

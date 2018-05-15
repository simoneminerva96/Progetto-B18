package GameClasses.Squares;
import GameClasses.*;

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
    public Boolean goOnIt() {
        return false;
    }

    //NON USO PATTERN STRATEGY PERCHè NON AVRO NECESSITà DI AGGIUNGERE ALTRI BONUS MALUS

    public void executeBonusMalus(Player playerOnTurn){
        String typeBonusMalus=bonusMalusType;
        // se esce il "random" la casella avrà l'effetto di uno dei 4 bonus/malus a caso
        if(typeBonusMalus.equalsIgnoreCase("random"))
        {
            Random generator=new Random();
            int min = 1; // numero minimo
            int max=4 ; // numero massimo
            int range = ((max-min) + 1);
            int result= generator.nextInt(range) + min; //estrae un numero da 1 a 4 per estrarre a caso il tipo di bonus/malus
            switch (result){
                case 1:
                    typeBonusMalus="bonus1";
                    break;
                case 2:
                    typeBonusMalus="malus1";
                    break;
                case 3:
                    typeBonusMalus="bonus2";
                    break;
                case 4:
                    typeBonusMalus="malus2";
                    break;
            }
        }
        //switch che esegue il bonus/malus
        switch (typeBonusMalus){
            case ("bonus1"):
                Scanner sc=new Scanner(System.in);
                System.out.println("CASELLA BONUS! scegli in che posizione vuoi spostare la pedina");
                int shift=sc.nextInt();
                if(shift>= 39 || shift <=0){
                    System.out.println("inserisci un numero corrispondente a una casella");
                    shift=sc.nextInt();
                }
                playerOnTurn.setActualPosition(shift);
                break;
            case("bonus2") :
                System.out.println("CASELLA BONUS! PUOI LANCIARE NUOVAMENTE IL DADO!");
                Turn t=new Turn(playerOnTurn);
                t.dieLaunch();
                break;
            case("malus1"):
                System.out.println("CASELLA MALUS! PASSI IL TURNO");
                // non fa nulla perchè poi tornando al metodo play verrà chiamato il metodo goOnIt su questa casella
                //che ritorna falso in ogni caso
                break;
            case("malus2"):
            System.out.println("CASELLA MALUS! PERDI UNO SPICCHIO!");
            playerOnTurn.removeSlice();
            break;
        }
    }

}

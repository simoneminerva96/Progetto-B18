package GameClasses.Squares;
import GameClasses.*;

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
        if(bonusMalusType.equalsIgnoreCase("bonus1")){
            Scanner sc=new Scanner(System.in);
            System.out.println("CASELLA BONUS! scegli in che posizione vuoi spostare la pedina");
            int shift=sc.nextInt();
            if(shift>= 39 || shift <=0){
                System.out.println("inserisci un numero corrispondente a una casella");
                shift=sc.nextInt();
            }
            playerOnTurn.setActualPosition(shift);
        }
        else if(bonusMalusType.equalsIgnoreCase("bonus2")){
            System.out.println("CASELLA BONUS! PUOI LANCIARE NUOVAMENTE IL DADO!");
            Turn t=new Turn(playerOnTurn);
            t.dieLaunch();
        }
        else if(bonusMalusType.equalsIgnoreCase("malus1")){
            System.out.println("CASELLA MALUS! PASSI IL TURNO");
            // non fa nulla perchè poi tornando al metodo play verrà chiamato il metodo goOnIt su questa casella
            //che ritorna falso in ogni caso
        }
        else{
            System.out.println("CASELLA MALUS! PERDI UNO SPICCHIO");
            playerOnTurn.removeSlice();
        }
    }

}

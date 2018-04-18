package GameClasses;
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
}

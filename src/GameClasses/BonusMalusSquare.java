package GameClasses;
/*
    CLASSE CHE CORRISPONDE ALLA CASELLE BONUS MALUS
 */
public class BonusMalusSquare extends Square {
    private String bonusMalusType;

    public BonusMalusSquare(Integer index,Categories category,String bonusMalusType){
        super(index,category);
        this.bonusMalusType=bonusMalusType;
    }

    public String getBonusMalusType() {
        return bonusMalusType;
    }
}

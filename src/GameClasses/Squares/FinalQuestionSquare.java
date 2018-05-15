package GameClasses.Squares;

import GameClasses.*;

public class FinalQuestionSquare extends Square {
    private Categories category;
    public FinalQuestionSquare(Integer index,Categories category){
        super(index);
        this.category=category;
    }

    public Categories getCategory() {
        return category;
    }

    @Override
    public Boolean goOnIt() {
        System.out.println("DOMANDA FINALE!");
        return super.goOnIt();
    }
}

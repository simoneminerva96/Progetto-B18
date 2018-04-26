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
}

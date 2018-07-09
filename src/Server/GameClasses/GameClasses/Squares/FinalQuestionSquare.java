package Server.GameClasses.GameClasses.Squares;

import Server.GameClasses.GameClasses.Categories;

/**
    casella corrispondente a una domanda finale
 */

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

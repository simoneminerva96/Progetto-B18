package GameClasses.Squares;

import GameClasses.*;

public class FinalQuestionSquare extends Square {
    private Categories category;
    public FinalQuestionSquare(Integer index,Categories category){
        super(index);
        this.category=category;
    }
    // metodo da implementare con la domanda finale e la conseguente vittoria o meno dello spicchio di categoria
    @Override
    public Boolean goOnIt() {
        System.out.println("da implementare la domanda finale");
        return false;
    }
}

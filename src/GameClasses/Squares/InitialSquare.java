package GameClasses.Squares;

import GameClasses.Square;

public class InitialSquare extends Square {

    public InitialSquare(Integer index) {
        super(index);
    }
    //non viene fatta nessuna domanda sulla casella iniziale
    @Override
    public Boolean goOnIt(int indexOfAnswer) {
        return false;
    }
}

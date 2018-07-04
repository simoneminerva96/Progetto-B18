package Server.GameClasses.Squares;

public class InitialSquare extends Square {

    public InitialSquare(Integer index) {
        super(index);
    }
    //non viene fatta nessuna domanda sulla casella iniziale
    @Override
    public boolean goOnIt(int indexOfAnswer) {
        return false;
    }

}

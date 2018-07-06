package Server.GameClasses.Squares;

public class InitialSquare extends Square {

    public InitialSquare(Integer index) {
        super(index);
    }

    @Override
    public boolean goOnIt(int indexOfAnswer) {
        return false;
    }

}

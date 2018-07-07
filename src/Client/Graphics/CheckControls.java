package Client.Graphics;

/**@author Di Cecca Rita, Stefano Gamage
 * Classe contenente dei flag che segnalano a Trivia se alcune informazioni sono state già ricevute da ClientInterface
 * @see Trivia
 * @see ClientInterface */

public class CheckControls {
    private boolean checkreceivedBonusMalus,checkreceivedtype,checkreceivedInitialSquare;
    private boolean checkreceivedVictory,checkReceivedSlices;

    public CheckControls(){
        checkreceivedBonusMalus=false;
        checkreceivedInitialSquare=false;
        checkreceivedtype=false;
        checkreceivedVictory=false;
        checkReceivedSlices=false;
    }

    public boolean isCheckreceivedBonusMalus() { return checkreceivedBonusMalus; }

    public boolean isCheckreceivedtype() { return checkreceivedtype; }

    public boolean isCheckreceivedInitialSquare() { return checkreceivedInitialSquare; }

    public boolean isCheckreceivedVictory() { return checkreceivedVictory; }

    public void setCheckreceivedBonusMalus(boolean checkreceivedBonusMalus) {
        this.checkreceivedBonusMalus = checkreceivedBonusMalus;
    }

    public void setCheckreceivedtype(boolean checkreceivedtype) { this.checkreceivedtype = checkreceivedtype; }

    public void setCheckreceivedInitialSquare(boolean checkreceivedInitialSquare) {
        this.checkreceivedInitialSquare = checkreceivedInitialSquare;
    }

    public void setCheckreceivedVictory(boolean checkreceivedVictory) {
        this.checkreceivedVictory = checkreceivedVictory;
    }

    public void setCheckReceivedSlices(boolean checkReceivedSlices) { this.checkReceivedSlices = checkReceivedSlices; }

    public boolean isCheckReceivedSlices() { return checkReceivedSlices; }

    public void reset(){
        checkreceivedBonusMalus=false;
        checkreceivedInitialSquare=false;
        checkreceivedtype=false;
        checkreceivedVictory=false;
        checkReceivedSlices=false;
    }
}

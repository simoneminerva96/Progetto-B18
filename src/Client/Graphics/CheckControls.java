package Client.Graphics;
import Client.Graphics.ClientInteface.ClientInterface;
import Server.GameClasses.GameClasses.BonusMalusRandom;

/**@author Di Cecca Rita, Kothuwa Gamage Thenuka Stefano
 * Classe contentente dei flag che segnalano a Trivia se alcune informazioni sono state già ricevute da ClientInterface
 * @see Trivia
 * @see ClientInterface
 */

public class CheckControls {
    private boolean checkreceivedBonusMalus,checkreceivedtype,checkreceivedInitialSquare;
    private boolean checkreceivedVictory,checkReceivedSlices;
    private boolean checkBonusMalus,checkinitialSquare,checkplayerVictory;
    private boolean esitoDomanda=false;
    private BonusMalusRandom checktype;
    private ClientInterface clientInterface;
    private String c="Nessuna";

    public CheckControls(ClientInterface clientInterface){
        checkreceivedBonusMalus=false;
        checkreceivedInitialSquare=false;
        checkreceivedtype=false;
        checkreceivedVictory=false;
        checkReceivedSlices=false;
        this.clientInterface=clientInterface;
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

    public void getStatus(){
        if(!checkreceivedBonusMalus){
            checkBonusMalus=clientInterface.checkBonusMalus();
            checkreceivedBonusMalus=true;
        }
        if(checkBonusMalus){
            if(!checkreceivedtype){
                checktype=clientInterface.getType();
                checkreceivedtype=true;
            }
        }
        if(!checkBonusMalus){
            if(!checkreceivedInitialSquare){
                checkinitialSquare=clientInterface.getCheckInitialSquare();
                checkreceivedInitialSquare=true;
            }
        }
        if(checkinitialSquare){
            if(!checkreceivedVictory){
                checkplayerVictory=clientInterface.receiveOutcome();
                checkreceivedVictory=true;
            }
        }
    }

    public void checkReceivedSlices(){
        if(!checkinitialSquare){
            if(esitoDomanda){
                if(!checkReceivedSlices){
                    c = clientInterface.getCategoriesOfTheSliceObtained();
                    checkReceivedSlices=true;
                }
            }
        }

    }


    public boolean isCheckBonusMalus() {
        return checkBonusMalus;
    }

    public boolean isCheckinitialSquare() {
        return checkinitialSquare;
    }

    public boolean isCheckplayerVictory() {
        return checkplayerVictory;
    }

    public BonusMalusRandom getChecktype() {
        return checktype;
    }

    public void setEsitoDomanda(boolean b){
        esitoDomanda=b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }
}

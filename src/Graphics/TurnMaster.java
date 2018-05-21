package Graphics;

import java.util.ArrayList;

public class TurnMaster {
    private ArrayList<Player> playerList;
    private int index;

    public TurnMaster(){
        playerList = new ArrayList<>();
        playerList.clear();
        index = 0;
    }

    public void addPlayer(Player p) {
        playerList.add(p);
    }

    public void nextPlayer(int diceN, PlayerGUI pl, Direction d){
        pl.setClicked(true);
        playerList.get(index).update(diceN,d);
        pl.updateCoordinates();
    }

    public void incrementIndex(boolean esito, boolean primoGiro, PlayerGUI pl){
        if (primoGiro) {
            if (!esito) {
                index++;
            }
            else
                pl.setClicked(false);
        }
    }

    public int getIndex() {
        return index;
    }

    public void resetIndex(){
        index = 0;
    }
}

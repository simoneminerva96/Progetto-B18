package Graphics;

import java.util.ArrayList;

public class TurnMaster {
    ArrayList<Player> playerList;
    int index = 0;
    //Player[] list;

    public TurnMaster(){
        playerList = new ArrayList<>();
        playerList.clear();
        //list = new Player[n];
    }

    public void addPlayer(Player p) {
        playerList.add(p);
        /*list[index] = p;
        if (index == 3) {
            System.out.println("NON");
        }else{
            index++;
        }*/
    }

    public void nextPlayer(int n, int diceN, PlayerGUI pl, Direction d){
        pl.setClicked(true);
        playerList.get(n).update(diceN,d);
        pl.updateCoordinates();

        /*n++;
        if(n>=list.length){
            n=0;
        }*/
        //return n;
    }
}

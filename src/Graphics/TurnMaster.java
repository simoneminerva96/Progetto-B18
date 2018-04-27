package javagame;

public class TurnMaster {
    int index=0,turn=0;
    Player[]list;

    public TurnMaster(int n){
        list=new Player[n];
    }

    public void addPlayer(Player p)throws Exception{
        list[index]=p;
        index++;
    }

    public int nextPlayer(int n){
        n++;
        if(n>=list.length){
            n=0;
        }

        return n;
    }


}

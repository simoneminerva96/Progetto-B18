package Logic;

import java.util.ArrayList;

public class Match {
    private int numeroGiocatori;
    private ArrayList<LogicPlayer> lp;
    private boolean gameEnded;

    public Match(int numeroGiocatori) {
        this.numeroGiocatori = numeroGiocatori;
        lp = new ArrayList<>(numeroGiocatori);
        lp.clear();
        gameEnded = false;
    }

    public void addLogicPlayer(LogicPlayer p) {
        lp.add(p);
    }

}

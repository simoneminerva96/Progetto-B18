package Graphics;

import Graphics.Player.Player;
import Graphics.Player.PlayerGUI;
import Interface.Direction;
import java.util.ArrayList;

/**
 * @author Rita
 *
 * La classe TurnMaster gestisce i turni. Essendo nella grafica, gestisce esclusivamente i turni dei
 * giocatori e il relativo movimento della pedina attraverso un indice.
 *
 * - playerList: arrayList di giocatori
 * - index: indice del Player corrente
 */

public class TurnMaster {
    private ArrayList<Player> playerList;
    private int index;

    public TurnMaster(){
        playerList = new ArrayList<>();
        playerList.clear();
        index = 0;
    }

    /**
     * @param p giocatore da aggiungere alla lista dei giocatori.
     */
    public void addPlayer(Player p) {
        playerList.add(p);
    }

    /**
     * Aggiorno i frame e le coordinate del PlayerGUI relativo al giocatore di turno. Dato che avrò
     * cliccato su una delle due frecce di spostamento, aggiorno clicked.
     *
     * @param diceN dado estratto
     * @param pl GUI del giocatore associata al Player di turno
     * @param d direzione di spostamento
     */

    public void nextPlayer(int diceN, PlayerGUI pl, Direction d){
        pl.setClicked(true);
        playerList.get(index).update(diceN,d);
        pl.updateCoordinates();
    }

    /**
     * Incremento indice di turno se ho almeno fatto un'estrazione dal dado e se non ho risposto
     * correttamente alla domanda, quindi toccherà ad un altro giocatore. Altrimenti, se ho risposto
     * correttamente, per poter continuare a giocare reimposto clicked a false.
     *
     * @param esito esito della domanda, true era corretta e posso proseguire, false era sbagliata
     *              e mi fermo.
     * @param primoGiro flag se ho effettuato almeno un'estrazione
     * @param pl GUI del Player da aggiornare
     */

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

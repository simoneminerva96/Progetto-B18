package Client.Graphics.Player;

import Server.GameClasses.Direction;
import Client.Graphics.Map.Map;

/**
 * @author Stefano
 *
 * La classe Player contiene le informazioni relative al singolo giocatore. È caratterizzato da:
 * - name: nome del giocatore
 * - Coordinate minime, massime, finali, matrice
 * - direction: direzione di spostamento
 * - isOnLeft, isOnRight, isOnUp, isOnDown
 */

public class Player{
    private Coordinate minime, massime, finali, matrice;
    private int id;
    private String name;
    private Direction direction = Direction.FORWARD;

    public Player(String name,int id,Map map){
        this.id = id;
        matrice = new Coordinate(map.getdimO(), map.getdimV());
        switch(id){
            case 1:
                minime = new Coordinate(0,0);
                massime = new Coordinate(matrice.getX()-2, matrice.getY()-2);
                break;

            case 2:
                minime = new Coordinate(0,1);
                massime = new Coordinate(matrice.getX()-2, matrice.getY()-1);
                break;

            case 3:
                minime = new Coordinate(1,0);
                massime = new Coordinate(matrice.getX()-1, matrice.getY()-2);
                break;

            case 4:
                minime = new Coordinate(1,1);
                massime = new Coordinate(matrice.getX()-1, matrice.getY()-1);
                break;
        }
        finali = new Coordinate(massime.getX(), massime.getY());
        this.name = name;

    }

    /**
     * Aggiorna le coordinate del giocatore in base al numero ottenuto dal dado e alla direzione scelta.
     * @param die numero estratto
     * @param direction direzione scelta dall'utente
     */
    public void update(int die,Direction direction) {
        this.direction = direction;
        finali.calculate(minime,massime,die,direction);
    }

    public String getName() {
        return name;
    }

    public Coordinate getMinime() { return minime; }

    public Coordinate getMassime() { return massime; }

    public int getX() { return finali.getX(); }

    public int getY() { return finali.getY(); }

    public Direction getDirection(){return direction;}
}

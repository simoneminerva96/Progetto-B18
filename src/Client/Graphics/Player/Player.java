package Client.Graphics.Player;

import Server.GameClasses.GameClasses.Direction;
import Client.Graphics.Map.Map;

/**
 * @author Stefano
 * La classe Player contiene le informazioni relative al singolo giocatore. È caratterizzato da:
 * - name: nome del giocatore
 * - id: id del giocatore
 * - Coordinate minime, massime, finali, matrice
 * - direction: direzione di spostamento
 */

public class Player{
    private Coordinate minime, massime, finali, matrice;
    private String name;
    private final int MINMOVEMENT = 2;
    private Direction direction = Direction.FORWARD;

    public Player(String name,int id,Map map){
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
        if (direction == Direction.FORWARD) {
            finali = checkForward(minime, massime, die);
        } else
            finali = checkBack(minime, massime, die);
    }

    private Coordinate checkForward (Coordinate minime, Coordinate massime, int die) {
        int x = finali.getX();
        int y = finali.getY();

        for(int i = 0; i<die; i++) {
            if (y == minime.getY() && x < massime.getX()) {
                x += MINMOVEMENT;
            }
            else
            if (y < massime.getY() && x == massime.getX()) {
                y += MINMOVEMENT;
            } else
            if (y == massime.getY() && x > minime.getX()) {
                x -= MINMOVEMENT;
            } else
            if (x == minime.getX() && y > minime.getY()) {
                y -= MINMOVEMENT;
            }
        }
        return new Coordinate(x,y);
    }

    private Coordinate checkBack (Coordinate minime, Coordinate massime, int die) {
        int x = finali.getX();
        int y = finali.getY();

        for(int i = 0; i<die; i++) {
            if (x == massime.getX() && y > minime.getY()) {
                y -= MINMOVEMENT;
            }
            else
            if (y == minime.getY() && x > minime.getX()) {
                x -= MINMOVEMENT;
            } else
            if (x == minime.getX() && y < massime.getY()) {
                y += MINMOVEMENT;
            } else
            if (y == massime.getY() && x < massime.getX()) {
                x += MINMOVEMENT;
            }
        }
        return new Coordinate(x,y);
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

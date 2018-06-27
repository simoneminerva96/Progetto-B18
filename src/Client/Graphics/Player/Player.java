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
    private boolean isOnLeft=false,isOnRight=false,isOnUp=false,isOnDown=false;

    public Player(String name,int id,Map map){
        this.id = id;
        matrice = new Coordinate(map.getXmax(), map.getYmax());
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

    public int getX() { return finali.getX(); }

    public int getY() { return finali.getY(); }

    public Direction getDirection(){return direction;}

    /**
     * Aggiorna le coordinate del giocatore in base al numero ottenuto dal dado e alla direzione scelta.
     * @param die numero estratto
     * @param direction direzione scelta dall'utente
     */
    public void update(int die,Direction direction) {
        int y = finali.getY();
        int x = finali.getX();
        int miny = minime.getY();
        int maxy = massime.getY();
        int minx = minime.getX();
        int maxx = massime.getX();
        int tempx, tempy;
        this.direction = direction;

        switch(direction){
            case FORWARD:
                if (y == miny) {
                    isOnLeft = false;
                    tempx = x + (2 * die);
                    if (tempx > maxx) {
                        tempx = tempx - maxx;
                        x = maxx;
                        y += tempx;
                    } else
                        x = tempx;
                    finali.setCoordinates(x, y);
                    break;
                }
                if (x == maxx) {
                    tempy = y + (2 * die);
                    if (tempy > maxy) {
                        tempy = tempy - maxy;
                        x -= tempy;
                        y = maxy;
                    } else
                        y = tempy;
                    finali.setCoordinates(x, y);
                    break;
                }
                if (y == maxy) {
                    tempx = x - (2 * die);
                    if (tempx < minx) {
                        tempx = minx - tempx;
                        x = minx;
                        y -= tempx;
                    } else
                        x = tempx;
                    finali.setCoordinates(x, y);
                    break;
                }
                if (x == minx) {
                    tempy = y - (2 * die);
                    isOnLeft = true;
                    if (tempy < miny) {
                        tempy = miny - tempy;
                        x += tempy;
                        y = miny;
                    } else
                        y = tempy;
                    finali.setCoordinates(x, y);
                    break;
                }
            case BACK:
                if (y == miny) {
                    tempx = x - (2 * die);
                    isOnUp = true;
                    if (tempx < minx) {
                        tempx = minx - tempx;
                        x = minx;
                        y += tempx;
                    } else
                        x = tempx;
                    finali.setCoordinates(x, y);
                    break;
                }
                if (x == maxx) {
                    isOnRight = true;
                    tempy = y - (2 * die);
                    if (tempy < miny) {
                        tempy = miny - tempy;
                        x -= tempy;
                        y = miny;
                    } else
                        y = tempy;
                    finali.setCoordinates(x, y);
                    break;
                }
                if (y == maxy) {
                    tempx = x + (2 * die);
                    if (tempx > maxx) {
                        tempx = tempx - maxx;
                        x = maxx;
                        y -= tempx;
                    } else
                        x = tempx;
                    finali.setCoordinates(x, y);
                    break;
                }
                if (x == minx) {
                    isOnDown = true;
                    tempy = y + (2 * die);
                    if (tempy > maxy) {
                        tempy = tempy - maxy;
                        x += tempy;
                        y = maxy;
                    } else
                        y = tempy;
                    finali.setCoordinates(x, y);
                    break;
                }
        }
    }

    public String getName() {
        return name;
    }

    public boolean isOnLeft() {
        return isOnLeft;
    }

    public boolean isOnRight() {
        return isOnRight;
    }

    public boolean isOnUp() {
        return isOnUp;
    }

    public boolean isOnDown() {
        return isOnDown;
    }

    public void setOnLeft(boolean onLeft) {
        isOnLeft = onLeft;
    }

    public void setOnRight(boolean onRight) {
        isOnRight = onRight;
    }

    public void setOnUp(boolean onUp) {
        isOnUp = onUp;
    }

    public void setOnDown(boolean onDown) {
        isOnDown = onDown;
    }
}

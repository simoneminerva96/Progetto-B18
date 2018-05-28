package Graphics.Player;

import Graphics.Direction;
import Graphics.Map.Map;

/**
 * @author Stefano
 *
 * La classe Player contiene le informazioni relative al singolo giocatore. È caratterizzato da:
 * - name: nome del giocatore
 * - x,y: coordinate da raggiungere
 * - minx, miny, maxx, maxy: ogni player si troverà in una certa porzione della cella della matrice,
 *                           questi valori rappresentano la posizione in cui si deve trovare per non
 *                           sovrapporsi agli altri.
 * - mulsize: dimensione della cella della matrice
 * - direction: direzione di spostamento
 * - isOnLeft
 * - isOnRight
 * - isOnUp
 * - isOnDown
 * - mx: dimensione massima X della matrice
 * - my: dimensione massima Y della matrice
 */
public class Player{
    private int x,y,id,minx,miny,maxx,maxy;
    private String name;
    private int mulsize;
    private Direction direction = Direction.FORWARD;
    boolean isOnLeft=false,isOnRight=false,isOnUp=false,isOnDown=false;
    int mx,my;

    public Player(String name,int id,Map map){
        this.id = id;
        mulsize = map.getTilesize();
        mx = map.getXmax();
        my = map.getYmax();

        switch(id){
            case 1:
                minx = 0;
                miny = 0;
                maxx = map.getXmax()-2;
                maxy = map.getYmax()-2;
                break;

            case 2:
                minx = 0;
                miny = 1;
                maxx = map.getXmax()-2;
                maxy = map.getYmax()-1;
                break;

            case 3:
                minx = 1;
                miny = 0;
                maxx = map.getXmax()-1;
                maxy = map.getYmax()-2;
                break;

            case 4:
                minx = 1;
                miny = 1;
                maxx = map.getXmax()-1;
                maxy = map.getYmax()-1;
                break;
        }
        x = maxx;
        y = maxy;
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection(){return direction;}

    /**
     * Aggiorna le coordinate del giocatore in base al numero ottenuto dal dado e alla direzione scelta.
     * @param die numero estratto
     * @param direction direzione scelta dall'utente
     */
    public void update(int die,Direction direction) {
        int tempx, tempy; 
        this.direction=direction;
        if (y == miny) {
            switch (direction) {
                case FORWARD: { 
                    isOnLeft=false;
                    tempx = x + (2 * die);
                    if (tempx > maxx) {
                        tempx = tempx - maxx;
                        x = maxx;
                        y += tempx;
                    } else 
                        x = tempx;
                    break;
                }
                case BACK: {        
                    tempx = x - (2 * die);
                    isOnUp=true;
                    if (tempx < minx) {
                        tempx = minx - tempx;
                        x = minx;
                        y += tempx;
                    } else 
                        x = tempx;
                    break;
               }
            }
         } else if (x == maxx) {
            switch(this.direction){
                case FORWARD: {
                    tempy = y + (2 * die);
                    if (tempy > maxy) {
                        tempy = tempy - maxy;
                        x -= tempy;
                        y = maxy;
                    } else 
                        y = tempy;
                    break; 
                }
                case BACK: {
                    isOnRight=true;
                    tempy = y - (2 * die);
                    if (tempy < miny) {
                        tempy = miny - tempy;
                        x -= tempy;
                        y = miny;
                    } else
                        y = tempy;
                    break;
                }
            }
        } else if (y == maxy) {
            switch(this.direction){
                case FORWARD: {
                    tempx = x - (2 * die);
                    if (tempx < minx) {
                        tempx = minx - tempx;
                        x = minx;
                        y -= tempx;
                    } else
                        x = tempx;
                    break;
                }
                case BACK: {

                    tempx = x + (2 * die);
                    if (tempx > maxx) {
                        tempx = tempx - maxx;
                        x = maxx;
                        y -= tempx;
                    } else
                        x = tempx;
                    break;
                }
            }
        } else if (x == minx) {
            switch(this.direction){
                case FORWARD: {
                    tempy = y - (2 * die);
                    isOnLeft=true;
                    if (tempy < miny) {
                        tempy = miny - tempy;
                        x += tempy;
                        y = miny;
                    } else
                        y = tempy;
                    break;
                }
                case BACK: {
                    isOnDown=true;
                    tempy = y + (2 * die);
                    if (tempy > maxy) {
                        tempy = tempy-maxy;
                        x += tempy;
                        y = maxy;
                    } else
                        y = tempy;
                    break;
                }
            }
        }
        System.out.println("X: "+x+" Y:"+y);
    }

    public String getName() {
        return name;
    }
}

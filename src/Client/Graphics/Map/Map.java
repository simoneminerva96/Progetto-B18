package Client.Graphics.Map;

import Client.Graphics.Player.Coordinate;
import org.newdawn.slick.SlickException;

/**
 * @author Stefano
 *
 * La classe Map è una matrice di dimensione dimO, dimV in cui ogni cella è grande tilesize pixel,
 * associata alla mappa di gioco.
 * - matrix: matrice associata alla mappa
 * - dimO, dimV: dimensione matrix
 * - counter: contatore delle singole celle
 * - tilesize: dimensione di ogni cella in pixel
 */

public class Map {
    private Tile[][] matrix;
    private int dimO,dimV,counter=0;
    private Coordinate coordinate;
    private int tilesize;

    public Map(int dimO,int dimV,int size) throws SlickException {
        this.dimO = dimO;
        this.dimV = dimV;
        matrix = new Tile[dimO][dimV];
        tilesize = size;
        for(int j=0;j<dimO;j++){
            for(int z=0;z<dimV;z++){
                matrix[z][j] = new Tile(counter);
                counter++;
            }
        }
    }

    public int getdimO() {
        return dimO;
    }
    public int getdimV() {
        return dimV;
    }
}

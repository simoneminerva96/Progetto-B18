package Client.Graphics.Map;

import org.newdawn.slick.SlickException;

/**
 * @author Stefano
 *
 * La classe Map è una matrice di dimensione xmax, ymax in cui ogni cella è grande tilesize pixel,
 * associata alla mappa di gioco.
 * - matrix: matrice associata alla mappa
 * - xmax, ymax: dimensione matrix
 * - counter: contatore delle singole celle
 * - tilesize: dimensione di ogni cella in pixel
 */

public class Map {
    private Tile[][] matrix;
    private int xmax,ymax,counter=0;
    private int tilesize;

    public Map(int xmax,int ymax,int size) throws SlickException {
        this.xmax = xmax;
        this.ymax = ymax;
        matrix = new Tile[xmax][ymax];
        tilesize = size;
        for(int j=0;j<xmax;j++){
            for(int z=0;z<ymax;z++){
                matrix[z][j] = new Tile(counter);
                counter++;
            }
        }
    }

    public int getXmax() {
        return xmax;
    }
    public int getYmax() {
        return ymax;
    }
}

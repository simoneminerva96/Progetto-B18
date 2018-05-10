package Graphics;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Map {
    public Tile[][] matrix;
    public int xmax,ymax,counter=0;
    public int tilesize;

    public Map(int xmax,int ymax,int size) throws SlickException {
        this.xmax=xmax;
        this.ymax=ymax;
        matrix=new Tile[xmax][ymax];
        tilesize=size;
        for(int j=0;j<xmax;j++){
            for(int z=0;z<ymax;z++){
                matrix[z][j]=new Tile(counter);
                counter++;
            }
        }
        //  mapGUI=new TiledMap(path);
    }

    public int getXmax() {
        return xmax;
    }
    public int getYmax() {
        return ymax;
    }

}

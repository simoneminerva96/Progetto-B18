package javagame;

public class Tile {
    public Player tempPlayer=null;
    public int id;
    public Tile(int counter){
        id=counter;
    }

    public int getId() {
        return id;
    }
}

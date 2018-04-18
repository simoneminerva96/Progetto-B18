package javagame;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame{
    public static final String gameName = "Trivial Pursuit";
    public static final int menu = 0;
    public static final int play = 1;
    public static final int prova = 2;

    public Game(String gameName) {
        super(gameName);
        this.addState(new Menu(menu));
        this.addState(new Play(play));
        this.addState(new Prova(prova));
    }

    /*
    Ogni State è una "schermata" di gioco. Qui le inizializzo
     */
    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.getState(menu).init(gc,this); // Inizializzo Menu iniziale del gioco
        this.getState(play).init(gc, this); // Inizializzo lo screen del gioco
        this.getState(prova).init(gc,this);
        this.enterState(menu); // qual è il primo screen che devo mostrare allo user?
    }

    public static void main(String[] args) {
        AppGameContainer appgc; // the window for the game
        try {
            //a windows that contains a game named gameName
            appgc = new AppGameContainer(new Game(gameName));
            appgc.setDisplayMode(1024,700, false);
            appgc.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}

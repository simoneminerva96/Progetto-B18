package Graphics;
import Graphics.com.menu.MainMenu;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Main extends StateBasedGame{
    public static final String gameName = "Trivial Pursuit";
    public static final int menu = 3;
    public static final int trivia = 1;
    public static final int login = 4;


    public Main(String gameName) throws Exception {
        super(gameName);
        this.addState(new MainMenu(menu));
        this.addState(new Trivia(trivia));
        this.addState(new LoginScreen(login));

    }

    /*
    Ogni State è una "schermata" di gioco. Qui le inizializzo
     */
    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.getState(menu).init(gc,this); // Inizializzo Menu iniziale del gioco
        this.getState(trivia).init(gc,this);
        this.getState(login).init(gc,this);
        this.enterState(menu);

    }

    public static void main(String[] args) throws Exception {
        AppGameContainer appgc; // the window for the game
        try {
            //a windows that contains a game named gameName
            appgc = new AppGameContainer(new Main(gameName));
            appgc.setAlwaysRender(true);
            appgc.setDisplayMode(1300, 700, false);
            appgc.setShowFPS(false);
            appgc.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}

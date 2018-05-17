package Graphics;
import Graphics.OfflineMenu.OfflineSelection;
import Graphics.com.menu.MainMenu;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Main extends StateBasedGame{
    public static final String gameName = "Trivial Pursuit";
    public static final int trivia = 3;
    public static final int login = 2;
    public static final int mainmenu=1;
    public static final int offlineSel=5;



    public Main(String gameName) throws Exception {
        super(gameName);

    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        /*this.getState(trivia).init(gc,this);
        this.getState(login).init(gc,this);
        this.getState(mainmenu).init(gc,this);
        this.enterState(mainmenu);*/
        this.addState(new MainMenu(mainmenu));
        this.addState(new LoginScreen(login));
        this.addState(new Trivia(trivia));
        this.addState(new OfflineSelection(offlineSel));


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

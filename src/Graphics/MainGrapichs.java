package Graphics;

import Graphics.com.menu.MainMenu;
import Login.RegistrationInterface;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class MainGrapichs extends StateBasedGame{
    public static final String gameName = "Trivial Pursuit";
    public static final int trivia = 5;
    public static final int mainmenu = 2;
    public static final int registration = 1;
    public static final int escape = 7;

    public MainGrapichs(String gameName) throws Exception {
        super(gameName);
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.addState(new Trivia(trivia));
        this.addState(new RegistrationInterface(registration));

        this.addState(new MainMenu(mainmenu));


    }

    //metodo da chiamare per avviare la classe
    public void Run() throws Exception{
        AppGameContainer appgc; // the window for the game
        try {
            //a windows that contains a game named gameName
            appgc = new AppGameContainer(new MainGrapichs(gameName));
            appgc.setAlwaysRender(true);
            appgc.setDisplayMode(1300, 700, false);
            appgc.setShowFPS(false);
            appgc.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}

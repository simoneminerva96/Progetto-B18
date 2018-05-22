package Graphics;

import Graphics.com.menu.MainMenu;
import Login.LoginRegistrationInterface;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 * @author Rita, Stefano
 *
 * La classe MainGraphics rappresenta il main della grafica. Definisco gli ID degli stati principali
 * di gioco (trivia, mainmenu, registration) e il nome del gioco che verrà visualizzato sulla finestra.
 */
public class MainGrapichs extends StateBasedGame{
    public static final String gameName = "Trivial Pursuit";
    public static final int trivia = 5;
    public static final int mainmenu = 2;
    public static final int registration = 1;

    public MainGrapichs(String gameName) throws Exception {
        super(gameName);
    }

    /**
     * Aggiungo gli state al contenitore associato alla finestra di gioco. Il primo ad essere aggiunto
     * sarà il primo ad essere visualizzato.
     *
     * @param gc contenitore associato alla finestra
     * @throws SlickException
     */

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.addState(new LoginRegistrationInterface(registration));
        this.addState(new Trivia(trivia));

        this.addState(new MainMenu(mainmenu));
    }

    /**
     * Metodo che deve essere chiamato per avviare la grafica. Esso viene richiamato in {@see MainController}
     * - appgc: finestra di gioco di nome gameName
     * @throws Exception
     */
    public void Run() throws Exception{
        AppGameContainer appgc;
        try {
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

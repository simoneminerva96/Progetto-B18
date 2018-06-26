package Client.Graphics;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 * @author Rita, Stefano
 *
 * La classe MainGraphics rappresenta il main della grafica. Definisco gli ID degli stati principali
 * di gioco (trivia, mainmenu, registration) e il nome del gioco che verrà visualizzato sulla finestra.
 */
public class MainGraphics extends StateBasedGame{
    private ClientInterface clientInterface;
    public static final String gameName = "Trivial Pursuit";
    public static final int trivia = 5;
    public static final int mainmenu = 2;
    public static final int playernumbersel=3;
    public static final int characterSelection=4;
    public static final int logininterface=1;

    public MainGraphics(String gameName, ClientInterface clientInterface) {
        super(gameName);
        this.clientInterface = clientInterface;
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
        this.addState(new LoginRegistrationInterface(logininterface,clientInterface));
        this.addState(new MainMenu(mainmenu));
        this.addState(new PlayerNumberSelection(playernumbersel));
        this.addState(new CharacterSelection(characterSelection));
        this.addState(new Trivia(trivia));

    }

    /**
     * Metodo che deve essere chiamato per avviare la grafica. Esso viene richiamato in {@see MainController}
     * - appgc: finestra di gioco di nome gameName
     * @throws Exception
     */
    public void Run() throws Exception{
        AppGameContainer appgc;
        try {
            appgc = new AppGameContainer(new MainGraphics(gameName, clientInterface));
            appgc.setAlwaysRender(true);
            appgc.setDisplayMode(1680, 1000, false);
            appgc.setShowFPS(false);
            appgc.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}

package Client.Graphics;

import Client.Graphics.ClientInteface.ClientInterface;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/** @author Rita, Stefano
 * La classe MainGraphics rappresenta il main della grafica. */
public class MainGraphics extends StateBasedGame{
    private ClientInterface clientInterface;
    private CheckControls checkControls;
    private static final String gameName = "Trivial Pursuit";

    public MainGraphics(String gameName, ClientInterface clientInterface,CheckControls checkControls) {
        super(gameName);
        this.clientInterface = clientInterface;
        this.checkControls=checkControls;
    }

    /**Aggiungo gli state al contenitore associato alla finestra di gioco. Il primo ad essere aggiunto
     * sarà il primo ad essere visualizzato.
     * @param gc contenitore associato alla finestra*/
    @Override
    public void initStatesList(GameContainer gc)  {
        this.addState(new LoginRegistrationInterface(clientInterface));
        this.addState(new MainMenu());
        this.addState(new PlayerNumberSelection(clientInterface));
        this.addState(new GameOrderState(clientInterface));
        this.addState(new Trivia(clientInterface,checkControls));
    }

    /**Metodo che deve essere chiamato per avviare la grafica. Esso viene richiamato in {@see Clientinferface}
     * - appgc: finestra di gioco di nome gameName*/
     public void run() {
        AppGameContainer appgc;
        try {
            appgc = new AppGameContainer(new MainGraphics(gameName, clientInterface,checkControls));
            appgc.setAlwaysRender(true);
            appgc.setDisplayMode(1680, 1000, false);
            appgc.setShowFPS(false);
            appgc.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}

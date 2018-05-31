package Graphics;

import Graphics.Map.Map;
import Graphics.Player.*;
import Domanda;
import Interface.Controller;
import Interface.Direction;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import java.util.ArrayList;

/**
 * @author Rita, Stefano
 *
 * La classe Trivia rappresenta lo state del tabellone.
 * - backgroundMap: immagine della mappa di gioco, tabellone
 * - back,forward: immagine freccia che torna indietro e che va avanti
 * - background: sfondo della schermata
 * - rydia, ceodore, kain, luca: immagini delle pedine
 * - d: oggetto di tipo DieGUI utilizzato per visualizzare la faccia del dado ed estrarre il numero
 * - map: oggetto di tipo Map associato alla matrice e al tabellone
 * - pGUI: arrayList di GUI dei player
 * - piece1,piece2,piece3,piece4: pedine associate ai player
 * - launch: bottone per lanciare il dado
 * - launched: flag che indica se ho tirato il dado o no
 * - first: flag che indica se ho tirato il dado almeno una volta
 * - diceN: intero che contiene il numero estratto
 * - turn: oggetto TurnMaster che gestisce i turni dei player
 * - prova: oggetto Domanda, state da renderizzare
 * - esc: oggetto Escape, state da renderizzare
 * - f, fonx1: font utilizzato
 */

public class Trivia extends BasicGameState {
    private Image backgroundMap, back, forward, background;
    private Image rydia, ceodore, kain, luca;
    private DieGUI d;
    private Map map;
    private ArrayList<PlayerGUI> pGUI;
    private Pedina piece, piece1, piece2, piece3;
    private String mouse = "No input";
    private Image launch;
    private boolean launched = false;
    private boolean first = false;
    private int diceN = 0;
    // private TurnMaster turn;
    private Domanda domanda;
    private Escape esc;
    private TrueTypeFont fonx1;
    private TriviaFont f;

    private Controller interm;
    private int nPlayers;
    private ArrayList<Image> playerBack;

    public Trivia(int id) {
        domanda = new Domanda(6);
        esc = new Escape(7);
        //turn = new TurnMaster();
        pGUI = new ArrayList<>();
        pGUI.clear();
        f = new TriviaFont();
        playerBack = new ArrayList<>();

    }

    public int getID() {
        return 5;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

        map = new Map(20, 20, 35);
        backgroundMap = new Image("res/map/Tabella.png");

        piece = new Pedina("res/char/FFIV/Rydia/rydiaup.png", "res/char/FFIV/Rydia/Rydiadwn.png", "res/char/FFIV/Rydia/rydiasx.png", "res/char/FFIV/Rydia/rydiadx.png", 35, 35);
        piece1 = new Pedina("res/char/FFIV/Ceodore/ced_up.png", "res/char/FFIV/Ceodore/ced_dwn.png", "res/char/FFIV/Ceodore/ced_lft.png", "res/char/FFIV/Ceodore/ced_rht.png", 35, 35);
        piece2 = new Pedina("res/char/FFIV/Kain/kainup.png", "res/char/FFIV/Kain/kaindwn.png", "res/char/FFIV/Kain/kainsx.png", "res/char/FFIV/Kain/kaindx.png", 35, 35);
        piece3 = new Pedina("res/char/FFIV/Luca/lucaup.png", "res/char/FFIV/Luca/lucadwn.png", "res/char/FFIV/Luca/lucasx.png", "res/char/FFIV/Luca/lucadx.png", 35, 35);

        back = new Image("res/buttons/Frecce/freccia1.png");
        forward = new Image("res/buttons/Frecce/freccia2.png");
        d = new DieGUI();

        rydia = new Image("res/char/rydia.png");
        ceodore = new Image("res/char/ceodore.png");
        kain = new Image("res/char/kain.png");
        luca = new Image("res/char/luca.png");
        background = new Image("res/backgrounds/green_landscape2.png");
        launch = new Image("res/buttons/Button_Launch/Button_Login_02.png");

        domanda.init(gameContainer, stateBasedGame);
        esc.init(gameContainer, stateBasedGame);
        fonx1 = new TrueTypeFont(f.getFont().deriveFont(23f), false);
        interm = new Controller();
        domanda.setController(interm);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        int x = 750;
        int y = 30;

        graphics.drawImage(background, 0, 0);
        graphics.drawImage(backgroundMap, 0, 0);
        graphics.drawImage(back, 750, 575);
        graphics.drawImage(forward, 850, 575);

        for(int i=0; i<nPlayers; i++){
            if ((i==1) || (i==3)){
                x = 1050;
            }
            if (i==2) {
                x = 750;
                y = 130;
            }
            playerBack.get(i).draw(x, y);
            fonx1.drawString((x+80),y,pGUI.get(i).getName(), Color.white);
        }
        rydia.draw(750, 30);
        ceodore.draw(1050, 30);
        kain.draw(750, 130);
        luca.draw(1050, 130);
        fonx1.drawString(870, 219, "E' il turno di: " + pGUI.get(interm.getIndex()).getName());
        launch.draw(990, 580);

        for (PlayerGUI p : pGUI) {
            p.getPedina().draw(p.getxUpdate(), p.getyUpdate());
        }

        if (launched) {
            d.getCurrentDie().draw(1200, 575);
        }

        /*
        Se la pedina si è fermata e quindi il flag ready= true allora posso renderizzare la domanda.
        Se ho risposto e mi è uscita la stringa, allora resetto ready e launched.
         */

        if (pGUI.get(interm.getIndex()).isReady()) {
            try {
                domanda.render(gameContainer, stateBasedGame, graphics);
                if (domanda.isEnd()) {
                    pGUI.get(interm.getIndex()).setReady(false);
                    launched = false;
                }
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
        /*
        se il flag quit= true vuol dire che ho premuto Esc e renderizzo lo state esc.
         */
        if (esc.isQuit()) {
            try {
                esc.render(gameContainer, stateBasedGame, graphics);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) {
        float xpos = Mouse.getX();
        float ypos = Mouse.getY();

        mouse = "Mouse position x:" + xpos + " y: " + ypos;
        Input input = gameContainer.getInput();

        /*
        Se sono nelle coordinate del bottone Launch controllo se l'indice del turno è uguale a 3 per
        resettarlo e reimpostare i flag clicked e first. Estraggo il numero e rimetto launched a true.
        richiamo incrementIndex per incrementare l'indice solo nelle condizioni possibili
        {@see TurnMaster}. resetto answered ed esito a false perchè risponderò ad una nuova domanda.
         */
        if (xpos > 990 && xpos < 1130 && ypos > 55 && ypos < 120) {
            if (input.isMousePressed(0) && launched == false) {
                if (interm.getIndex() == nPlayers-1) {
                    first = false;
                    //turn.resetIndex();
                    for (PlayerGUI p : pGUI) {
                        p.setClicked(false);
                    }
                }
                launched = true;
                diceN = interm.getDiceValue();
                d.setCurrentDie(diceN);
                pGUI.get(interm.getIndex()).setClicked(false);
                //turn.incrementIndex(domanda.isEsito(), first, pGUI.get(interm.getIndex()));
                domanda.setAnswered(false);
                domanda.setEsito(false);
            }
        }

        /*
        se ho lanciato il dado, controllo le coordinate in cui ho cliccato (una delle due frecce).
        in uno o nell'altro caso, richiamo il metodo nextPlayer per aggiornare le coordinate della GUI.
        se sono al primo tiro del dado, first = true.
         */

        if (launched) {
            if (ypos > 46 && ypos < 109) {
                if (xpos > 870 && xpos < 930) {
                    if (input.isMousePressed(0)) {
                        interm.setDirection(Direction.BACK);
                        pGUI.get(interm.getIndex()).setClicked(true);
                        pGUI.get(interm.getIndex()).getP().update(diceN, Direction.BACK);
                        pGUI.get(interm.getIndex()).updateCoordinates();
                        //turn.nextPlayer(diceN, pGUI.get(interm.getIndex()), Direction.BACK);
                        if (interm.getIndex() == 0) {
                            first = true;
                        }
                    }
                }
                if (xpos > 770 && xpos < 830) {
                    if (input.isMousePressed(0)) {
                        interm.setDirection(Direction.FORWARD);
                        pGUI.get(interm.getIndex()).setClicked(true);
                        pGUI.get(interm.getIndex()).getP().update(diceN, Direction.FORWARD);
                        pGUI.get(interm.getIndex()).updateCoordinates();
                        //turn.nextPlayer(diceN, pGUI.get(interm.getIndex()), Direction.FORWARD);
                        if (interm.getIndex() == 0) {
                            first = true;
                        }
                    }
                }
            }
        }

        try {
            domanda.update(gameContainer, stateBasedGame, i);
            esc.update(gameContainer, stateBasedGame, i);
        } catch (SlickException e) {
            e.printStackTrace();
        }

        pGUI.get(interm.getIndex()).updateOnEachFrame(i);

        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            esc.setQuit(true);
        }
    }

    public void setPlayersNumber(int n) throws SlickException {
        nPlayers =n;
        interm.initializePlayers(nPlayers);
        for (int i = 0; i <nPlayers; i++) {
            Player p = new Player("prova", i + 1, map);
            if (i == 0) {
                pGUI.add(i, new PlayerGUI(p, piece));
                playerBack.add(new Image("res/backgrounds/PlayerBackgrounds/SfondoGiocatore.png"));
            }
            if (i == 1) {
                pGUI.add(i, new PlayerGUI(p, piece1));
                playerBack.add(new Image("res/backgrounds/PlayerBackgrounds/SfondoGiocatore1.png"));
            }
            if (i == 2) {
                pGUI.add(i, new PlayerGUI(p, piece2));
                playerBack.add(new Image("res/backgrounds/PlayerBackgrounds/SfondoGiocatore2.png"));
            }
            if (i == 3) {
                pGUI.add(i, new PlayerGUI(p, piece3));
                playerBack.add(new Image("res/backgrounds/PlayerBackgrounds/SfondoGiocatore3.png"));
            }
        }
        /*for (int i = 0; i < nPlayers; i++) {
            turn.addPlayer(pGUI.get(i).getP());
        }*/

        for (PlayerGUI p : pGUI) {
            p.getPedina().stop();
        }
    }
}


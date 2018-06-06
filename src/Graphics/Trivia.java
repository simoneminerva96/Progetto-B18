package Graphics;

import Graphics.Fonts.TriviaFont;
import Graphics.Map.Map;
import Graphics.Player.*;
import Interface.*;
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
 * - domanda: oggetto Domanda, state da renderizzare
 * - esc: oggetto Escape, state da renderizzare
 * - f, fonx1: font utilizzato
 * - interm: oggetto di tipo Controller per comunicare con la logica
 * - nPlayers: numero di giocatori effettivo per la partita
 * - playerBack: arrayList di sfondi
 */

public class Trivia extends BasicGameState {
    private Image backgroundMap, back, forward, background;
    private Image rydia, ceodore, kain, luca;
    private DieGUI d;
    private Map map;
    private ArrayList<PlayerGUI> pGUI;
    private Pedina piece, piece1, piece2, piece3;
    private Image launch;
    private boolean launched = false;
    private int diceN = 0;
    private Domanda domanda;
    private Escape esc;
    private TrueTypeFont fonx1;
    private TriviaFont f;
    private Controller interm;
    private int nPlayers;
    private ArrayList<Image> playerBack;
    private boolean check;
    private BonusMalusRandom b;

    public Trivia(int id) {
        domanda = new Domanda(6);
        esc = new Escape(7);
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

        map = new Map(20, 20, 50);
        backgroundMap = new Image("res/map/Tabella.png");

        piece = new Pedina("res/char/FFIV/Rydia/rydiaup.png", "res/char/FFIV/Rydia/Rydiadwn.png", "res/char/FFIV/Rydia/rydiasx.png", "res/char/FFIV/Rydia/rydiadx.png", 40, 40);
        piece1 = new Pedina("res/char/FFIV/Ceodore/ced_up.png", "res/char/FFIV/Ceodore/ced_dwn.png", "res/char/FFIV/Ceodore/ced_lft.png", "res/char/FFIV/Ceodore/ced_rht.png", 40, 40);
        piece2 = new Pedina("res/char/FFIV/Kain/kainup.png", "res/char/FFIV/Kain/kaindwn.png", "res/char/FFIV/Kain/kainsx.png", "res/char/FFIV/Kain/kaindx.png", 40, 40);
        piece3 = new Pedina("res/char/FFIV/Luca/lucaup.png", "res/char/FFIV/Luca/lucadwn.png", "res/char/FFIV/Luca/lucasx.png", "res/char/FFIV/Luca/lucadx.png", 40, 40);

        back = new Image("res/buttons/Frecce/back.png");
        forward = new Image("res/buttons/Frecce/forward.png");
        d = new DieGUI();

        rydia = new Image("res/char/rydia.png");
        ceodore = new Image("res/char/ceodore.png");
        kain = new Image("res/char/kain.png");
        luca = new Image("res/char/luca.png");
        background = new Image("res/backgrounds/green_landscape.png");
        launch = new Image("res/buttons/Button_Launch/Button_Launch.png");

        domanda.init(gameContainer, stateBasedGame);
        esc.init(gameContainer, stateBasedGame);
        fonx1 = new TrueTypeFont(f.getFont().deriveFont(23f), false);
        interm = new Controller();
        domanda.setController(interm);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        int x = 1100;
        int y = 30;

        graphics.drawImage(background, 0, 0);
        graphics.drawImage(backgroundMap, 0, 0);
        graphics.drawImage(back, 1160, 840);
        graphics.drawImage(forward, 1050, 840);

        for(int i=0; i<nPlayers; i++){
            if ((i==1) || (i==3)){
                x = 1400;
            }
            if (i==2) {
                x = 1100;
                y = 130;
            }
            playerBack.get(i).draw(x, y);
            fonx1.drawString((x+80),y,pGUI.get(i).getName(), Color.white);
        }
        //rydia.draw(750, 30);
        //ceodore.draw(1050, 30);
        //kain.draw(750, 130);
        //luca.draw(1050, 130);
        fonx1.drawString(1190, 230, "E' il turno di: " + pGUI.get(interm.getIndex()).getName());
        launch.draw(1320, 860);

        for (PlayerGUI p : pGUI) {
            p.getPedina().draw(p.getxUpdate(), p.getyUpdate());
        }

        if (launched) { d.getCurrentDie().draw(1550, 870); }

        /*
        Se la pedina si è fermata e quindi il flag ready= true allora posso renderizzare la domanda.
        Se ho risposto e mi è uscita la stringa, allora resetto ready e launched.
         */

        if (pGUI.get(interm.getIndex()).isReady() ) {
            check = interm.checkBonusMalus();
            if(check){
                switch(interm.checkType()) {
                    case Bonus: {
                        fonx1.drawString(200,500, "PUOI RILANCIARE IL DADO!", Color.white);
                        pGUI.get(interm.getIndex()).setReady(false);
                        launched = false;
                        break;
                    }
                    case Malus: {
                        fonx1.drawString(200, 500, "HAI PERSO IL TURNO!", Color.white);
                        pGUI.get(interm.getIndex()).setReady(false);
                        launched = false;
                        break;
                    }
                }
            }
            else {
                domanda.render(gameContainer, stateBasedGame, graphics);
                if (domanda.isEnd()) {
                    pGUI.get(interm.getIndex()).setReady(false);
                    launched = false;
                }
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

        //System.out.println("X: "+ xpos + "Y: "+ ypos);
        Input input = gameContainer.getInput();

        /*
        Se sono nelle coordinate del bottone Launch, ottengo il numero estratto, metto launched a true e aggiorno la
        faccia del dado. Resetto answered ed esito a false perchè risponderò ad una domanda.
         */
        if (xpos > 1322 && xpos < 1505 && ypos > 57 && ypos < 143) {
            if (input.isMousePressed(0) && !launched) {
                interm.setPlayerOnTurn();
                pGUI.get(interm.getIndex()).setClicked(false);
                launched = true;
                diceN = interm.getDiceValue();
                d.setCurrentDie(diceN);
                domanda.setAnswered(false);
                domanda.setEsito(false);
                domanda.setEnd(false);
            }
        }

        /*
        se ho lanciato il dado, controllo le coordinate in cui ho cliccato (una delle due frecce).
        in uno o nell'altro caso, informo il controller della direzione presa, aggiorno le coordinate delle gui e
        controllo se è la prima scelta di direzione con first.
         */
        if (launched) {
            if (ypos > 45 && ypos < 123) {
                if (xpos > 1081 && xpos < 1156) {
                    if (input.isMousePressed(0)) {
                        interm.setDirection(Direction.BACK);
                        pGUI.get(interm.getIndex()).setClicked(true);
                        pGUI.get(interm.getIndex()).getP().update(diceN, Direction.BACK);
                        pGUI.get(interm.getIndex()).updateCoordinates();
                        check = interm.checkBonusMalus();
                    }
                }
                if (xpos > 1188 && xpos < 1270) {
                    if (input.isMousePressed(0)) {
                        interm.setDirection(Direction.FORWARD);
                        pGUI.get(interm.getIndex()).setClicked(true);
                        pGUI.get(interm.getIndex()).getP().update(diceN, Direction.FORWARD);
                        pGUI.get(interm.getIndex()).updateCoordinates();
                        check = interm.checkBonusMalus();
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

        if (input.isKeyPressed(Input.KEY_ESCAPE)) { esc.setQuit(true); }
    }

    public void setPlayersNumber(int n) throws SlickException {
        nPlayers =n;
        interm.initializePlayers(nPlayers);
        for (int i = 0; i <nPlayers; i++) {
            Player p = new Player("prova" + (i+1), i + 1, map);
            playerBack.add(new Image("res/backgrounds/PlayerBackgrounds/SfondoGiocatore"+i+".png"));
            if (i == 0) {
                pGUI.add(i, new PlayerGUI(p, piece));
            }
            if (i == 1) {
                pGUI.add(i, new PlayerGUI(p, piece1));
            }
            if (i == 2) {
                pGUI.add(i, new PlayerGUI(p, piece2));
            }
            if (i == 3) {
                pGUI.add(i, new PlayerGUI(p, piece3));
            }
        }

        for (PlayerGUI p : pGUI) {
            p.getPedina().stop();
        }
    }
}


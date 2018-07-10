package Client.Graphics;

import Client.Graphics.ClientInteface.ClientInterface;
import Client.Graphics.Player.Player;
import Client.Graphics.com.sticky.StateButton;
import Client.Graphics.Fonts.TriviaFont;
import Client.Graphics.Map.Map;
import Client.Graphics.Player.*;
import Server.GameClasses.GameClasses.BonusMalusRandom;
import Server.GameClasses.GameClasses.Categories;
import Server.GameClasses.GameClasses.Direction;
import Server.GameClasses.GameClasses.Slice;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import java.util.ArrayList;

/**
 * @author Di Cecca Rita, Kothuwa Gamage Stefano
 *
 * La classe Trivia rappresenta lo state del tabellone.
 * - backgroundMap: immagine della mappa di gioco, tabellone
 * - back,forward: immagine freccia che torna indietro e che va avanti
 * - background: sfondo della schermata
 * - d: oggetto di tipo DieGUI utilizzato per visualizzare la faccia del dado
 * - map: oggetto di tipo Map associato alla matrice e al tabellone
 * - pGUI: arrayList di GUI dei player
 * - piece1,piece2,piece3,piece4: pedine associate ai player
 * - launch: bottone per lanciare il dado
 * - launched: flag che indica se ho tirato il dado o no
 * - diceN: intero che contiene il numero estratto
 * - domanda: state da renderizzare per visualizzare e rispondere alla domanda
 * - esc: state da renderizzare premendo il tasto ESC che visualizza Menu, Exit e Resume
 * - f, fonx1: font utilizzato
 * - NPLAYERS: numero di giocatori effettivo per la partita
 * - playerBack: arrayList di sfondi dei giocatori
 * - checkVictory: flag che indica se ho vinto la partita. in tal caso, visualizzo stringa e dopo 5s chiude il gioco
 * - diamanti: arrayList di image di diamanti
 * - indexPlayerOnTurn: indice del  giocatore di turno
 */

public class Trivia extends BasicGameState {
    private Image backgroundMap,background;
    private ArrayList<Image> diamanti;
    private ArrayList<Image> playerBack;
    private ArrayList<Image> playerIcons;

    private DieGUI d;
    private Map map;
    private ArrayList<PlayerGUI> pGUI;
    private Pedina piece, piece1, piece2, piece3;

    private boolean launched = false;
    private boolean checkVictory = false;

    private StateButton bkButton;
    private StateButton fwButton;
    private StateButton launchButton;

    private int diceN = 0;
    private ClientInterface clientInterface;
    private int NPLAYERS;
    private CheckControls checkControls;

    private Domanda domanda;
    private Escape esc;
    private TrueTypeFont fonx1;
    private TriviaFont f;
    private boolean checkBonusMalus,checkinitialSquare;
    private boolean checkreceivedInformationPLAYERS; //diventa true quando ho ricevuto l'informazione sul num di giocatori
    private int indexPlayerOnTurn;

    Trivia(ClientInterface clientInterface,CheckControls checkControls) {
        domanda = new Domanda(clientInterface);
        esc = new Escape();
        pGUI = new ArrayList<>();
        pGUI.clear();
        f = new TriviaFont();
        playerBack = new ArrayList<>();
        diamanti = new ArrayList<>();
        playerIcons=new ArrayList<>();
        this.clientInterface = clientInterface;
        this.checkControls=checkControls;
    }

    public int getID() { return 5; }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

        map = new Map(20, 20, 50);
        backgroundMap = new Image("res/map/Tabella.png");

        piece = new Pedina("res/char/FFIV/Rydia/rydiaup.png", "res/char/FFIV/Rydia/Rydiadwn.png", "res/char/FFIV/Rydia/rydiasx.png", "res/char/FFIV/Rydia/rydiadx.png", 40, 40);
        piece1 = new Pedina("res/char/FFIV/Ceodore/ced_up.png", "res/char/FFIV/Ceodore/ced_dwn.png", "res/char/FFIV/Ceodore/ced_lft.png", "res/char/FFIV/Ceodore/ced_rht.png", 40, 40);
        piece2 = new Pedina("res/char/FFIV/Kain/kainup.png", "res/char/FFIV/Kain/kaindwn.png", "res/char/FFIV/Kain/kainsx.png", "res/char/FFIV/Kain/kaindx.png", 40, 40);
        piece3 = new Pedina("res/char/FFIV/Luca/lucaup.png", "res/char/FFIV/Luca/lucadwn.png", "res/char/FFIV/Luca/lucasx.png", "res/char/FFIV/Luca/lucadx.png", 40, 40);

        bkButton=new StateButton(new Rectangle(1190, 860,100,100),new Image("res/buttons/Frecce/back.png"),new Image("res/buttons/Frecce/back.png"),new Image("res/buttons/Frecce/back.png"),null);
        fwButton=new StateButton(new Rectangle(1050, 860,100,100),new Image("res/buttons/Frecce/forward.png"),new Image("res/buttons/Frecce/forward.png"),new Image("res/buttons/Frecce/forward.png"),null);
        d = new DieGUI();

        playerIcons.add(new Image("res/char/rydial.png"));
        playerIcons.add(new Image("res/char/ceodor.png"));
        playerIcons.add(new Image("res/char/kainl.png"));
        playerIcons.add(new Image("res/char/lucal.png"));
        background = new Image("res/backgrounds/green_landscape.png");
        launchButton=new StateButton(new Rectangle(1320, 860,185,91),new Image("res/buttons/Button_Launch/Button_Launch_0.png"),new Image("res/buttons/Button_Launch/Button_Launch.png"),new Image("res/buttons/Button_Launch/Button_Launch_0.png"),null);
        initializeDiamonds();

        domanda.init(gameContainer, stateBasedGame);
        esc.init(gameContainer, stateBasedGame);
        fonx1 = new TrueTypeFont(f.getFont().deriveFont(23f), false);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        int x = 1100;
        int y = 30;

        graphics.drawImage(background, 0, 0);
        graphics.drawImage(backgroundMap, 0, 0);
        bkButton.render(gameContainer,graphics);
        fwButton.render(gameContainer,graphics);
        fonx1.drawString(1190, 280, "E' il turno di: " + pGUI.get(indexPlayerOnTurn).getName(), Color.black);
        launchButton.render(gameContainer,graphics);

        for(int i=0; i<NPLAYERS; i++){
            if ((i==1) || (i==3))
                x = 1400;
            if (i==2) {
                x = 1100;
                y = 150;
            }
            playerBack.get(i).draw(x, y);
            playerIcons.get(i).draw(x,y);
            fonx1.drawString((x+100),y,pGUI.get(i).getName(), Color.white);
            for (Slice slice : pGUI.get(i).getSlc()) {
                Categories c = slice.getCategory();
                drawDiamonds(graphics, c, x, y);
            }
        }

        for (PlayerGUI p : pGUI) { p.getPedina().draw(p.getxUpdate(), p.getyUpdate()); }

        if (launched) { d.getCurrentDie().draw(1550, 870); }

        /*
            METODI DI VERIFICA STATO POSIZIONE UTENTE
         Se la pedina si è fermata (ready=true) controllo se è una casella di bonus malus.Se non è una bonus malus
         controllo se è la casella iniziale, se non è allora posso renderizzare la domanda, altrimenti controllo
         se ho vinto o se passo il turno. Se ho risposto e mi è uscita la stringa, allora resetto ready e launched.
         */

        checkReady(gameContainer,stateBasedGame,graphics);

        if (esc.isQuit()) {
            try {
                esc.render(gameContainer, stateBasedGame, graphics);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException{
        Input input = gameContainer.getInput();

        if(!checkreceivedInformationPLAYERS) initializePlayers();

        if(input.isMousePressed(0)){
            /*ottengo il numero estratto, launched=true e aggiorno la faccia del dado. Resetto answered, esito a false
            perchè risponderò ad una domanda.*/
            if(launchButton.onClickBoolean(input.getMouseX(),input.getMouseY())){
                if(!launched && domanda.isClicked()){
                    clientInterface.sendOutcome(); //comunica al server che deve eseguire setplayerOnturn
                    indexPlayerOnTurn=clientInterface.getIndex();
                    pGUI.get(indexPlayerOnTurn).setClicked(false);
                    launched = true;
                    diceN = clientInterface.getDiceValue();
                    d.setCurrentDie(diceN);
                    domanda.reset();
                    domanda.setClicked(false);
                    resetBoolean();
                }
            }
        /*aggiorno il server sulla direzione presa e aggiorno la gui dei giocatori verificando se è stato cliccato il
        tasto per il lancio del dado*/
            if(launched){
                if(bkButton.onClickBoolean(input.getMouseX(),input.getMouseY()))
                    updateGui(Direction.BACK);
                else
                    if(fwButton.onClickBoolean(input.getMouseX(),input.getMouseY()))
                    updateGui(Direction.FORWARD);
            }
        }
        try {
            domanda.update(gameContainer, stateBasedGame, i);
            esc.update(gameContainer, stateBasedGame, i);
        } catch (SlickException e) {
            e.printStackTrace();
        }

        pGUI.get(indexPlayerOnTurn).updateOnEachFrame();
        if (input.isKeyPressed(Input.KEY_ESCAPE)) { esc.setQuit(true); }

        if (checkVictory) {
            pause();
            gameContainer.exit();
        }

        launchButton.onMouseEnter(launchButton,input.getMouseX(),input.getMouseY());
    }

    /** controllo se la pedina si è fermata e in tal caso posso ricevere una domanda. Il primo controllo è se una
     * casella di bonus o di malus.
     * @param gameContainer container della finestra di gioco
     * @param stateBasedGame oggetto che permette di passare tra state diversi
     * @param graphics oggetto che permette di disegnare sulla  finestra
     */
    private void checkReady(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics){
        if (pGUI.get(indexPlayerOnTurn).isReady() ) {
            checkControls.getStatus();
            //entra se è una bonus/malus o random
            checkBonusMalus=checkControls.isCheckBonusMalus();
            checkBonusMalusState(gameContainer,stateBasedGame,graphics);
            pGUI.get(indexPlayerOnTurn).setReady(false);
            launched = false;
        }
    }

    /** Metodo che esegue la bonus malus oppure controlla se sono sulla casella iniziale */
    private void checkBonusMalusState(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics){
        BonusMalusRandom checktype;
        //se il checkbonusmalus è vero esegui l'effetto del bonus malus
        if (checkBonusMalus) {
            //ricevo il tipo estratto
            checktype=checkControls.getChecktype();
            //a seconda del tipo estratto eseguo bonus o malus
            switch (checktype) {
                case BONUS:
                    fonx1.drawString(1190, 700, "PUOI RILANCIARE IL DADO!", Color.black);
                    break;
                case MALUS:
                    fonx1.drawString(1190, 700, "HAI PERSO IL TURNO!", Color.black);
                    break;
            }
            domanda.setClicked(true);
        }
        else {
            //ricevo check se la casella in cui sono è la casella iniziale
            checkinitialSquare=checkControls.isCheckinitialSquare();
            checkVictoryState(gameContainer,stateBasedGame,graphics);
        }
    }

    /** Se non sono nei casi precedenti, sono su una casella classica e quindi render dello state domanda
     * o verifica se l'utente ha vinto */
    private void checkVictoryState(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics){
        boolean checkplayerVictory;

        //se non sono sulla casella iniziale renderizzo la domanda
        if (!checkinitialSquare) {
            domanda.render(gameContainer, stateBasedGame, graphics);
            checkControls.setEsitoDomanda(domanda.isEsito());
            checkControls.checkReceivedSlices();
            String c=checkControls.getC();
            if(!c.equals("Nessuna")){
                pGUI.get(indexPlayerOnTurn).addSliceObtained(new Slice(Categories.valueOf(c)));
                checkControls.setC("Nessuna");
            }
        }
        // se sono nella casella iniziale controllo se ho abbastanza spicchi per la vittoria, altrimenti
        // passa il turno
        else {
           checkplayerVictory=checkControls.isCheckplayerVictory();
            if (checkplayerVictory) {
                fonx1.drawString(1190, 700, "HAI VINTO", Color.black);
                checkVictory = true;
            } else {
                fonx1.drawString(1190, 700, "CASELLA INIZIALE, PASSI IL TURNO!", Color.black);
                pGUI.get(indexPlayerOnTurn).setReady(false);
                domanda.setClicked(true);
                launched = false;
            }
        }
    }

    private void updateGui (Direction direction) {
        pGUI.get(indexPlayerOnTurn).setClicked(true);
        clientInterface.sendDirection(direction);
        pGUI.get(indexPlayerOnTurn).getP().update(diceN, direction);
        pGUI.get(indexPlayerOnTurn).updateCoordinates();
    }

    /** istanzio i giocatori nell'ordine di gioco corretto coerentemente con lo state precedente @see GameOrderState*/
    private void initializePlayers()throws SlickException{
        ArrayList<String> nicknames=new ArrayList<>(clientInterface.getNicknames());
        NPLAYERS=nicknames.size();
        for (int i = 0; i <NPLAYERS; i++) {
            Player p = new Player(nicknames.get(i), i + 1, map);
            playerBack.add(new Image("res/backgrounds/PlayerBackgrounds/SfondoGiocatore"+i+".png"));
            if (i == 0)
                pGUI.add(i, new PlayerGUI(p, piece));
            if (i == 1)
                pGUI.add(i, new PlayerGUI(p, piece1));
            if (i == 2)
                pGUI.add(i, new PlayerGUI(p, piece2));
            if (i == 3)
                pGUI.add(i, new PlayerGUI(p, piece3));
        }
        for (PlayerGUI p : pGUI) { p.getPedina().stop(); }
        checkreceivedInformationPLAYERS=true;
    }

    private static void pause(){
        long Time0 = System.currentTimeMillis();
        long Time1;
        long runTime = 0;
        while(runTime<5000){
            Time1 = System.currentTimeMillis();
            runTime = Time1 - Time0;
        }
    }

    private void drawDiamonds(Graphics graphics, Categories c, int x, int y){
        switch (c){
            case GEOGRAFIA:
                graphics.drawImage(diamanti.get(5),x+100,y+40 );
                break;
            case STORIA:
                graphics.drawImage(diamanti.get(3), x+125,y+40);
                break;
            case SCIENZE:
                graphics.drawImage(diamanti.get(4), x+150,y+40);
                break;
            case SPETTACOLO:
                graphics.drawImage(diamanti.get(2), x+175,y+40);
                break;
            case ARTELETTERATURA:
                graphics.drawImage(diamanti.get(1), x+200,y+40);
                break;
            case SPORT:
                graphics.drawImage(diamanti.get(0), x+225,y+40);
                break;
        }
    }

    private void initializeDiamonds (){
        for (int i=1; i<7; i++){
            try {
                diamanti.add(new Image("res/diamonds/d"+i+".png"));
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
    }

    private void resetBoolean(){
        domanda.setCheckreceivedQuestion();
        checkControls.reset();
    }
}




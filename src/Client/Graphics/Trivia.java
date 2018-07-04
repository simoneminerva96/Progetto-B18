package Client.Graphics;

import Client.Graphics.Player.Player;
import Server.GameClasses.*;
import Client.Graphics.Fonts.TriviaFont;
import Client.Graphics.Map.Map;
import Client.Graphics.Player.*;
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
 * - diceN: intero che contiene il numero estratto
 * - domanda: oggetto Domanda, state da renderizzare
 * - esc: oggetto Escape, state da renderizzare
 * - f, fonx1: font utilizzato
 * - controller: oggetto di tipo Controller per comunicare con la logica
 * - NPLAYERS: numero di giocatori effettivo per la partita
 * - playerBack: arrayList di sfondi
 * - checkVictory: se ho vinto la partita visualizzo stringa e dopo 5s chiude gioco
 * - diamanti: arrayList di image di diamanti
 */

public class Trivia extends BasicGameState {
    private Image backgroundMap, back, forward, background;
    private Image rydia, ceodore, kain, luca;
    private Image launch;
    private ArrayList<Image> diamanti;
    private ArrayList<Image> playerBack;
    private ArrayList<Image> playerIcons;

    private DieGUI d;
    private Map map;
    private ArrayList<PlayerGUI> pGUI;
    private Pedina piece, piece1, piece2, piece3;

    private boolean launched = false;
    private boolean checkVictory = false;
    private int diceN = 0;
    private ClientInterface clientInterface;
    private int NPLAYERS;

    private Domanda domanda;
    private Escape esc;
    private TrueTypeFont fonx1;
    private TriviaFont f;
    private boolean checkBonusMalus,checkreceivedBonusMalus,checkreceivedtype,checkinitialSquare,checkreceivedInitialSquare;
    private boolean checkplayerVictory,checkreceivedVictory,checkReceivedSlices;
    private BonusMalusRandom checktype;

    private boolean checkreceivedInformationPLAYERS; //diventa true quando ho ricevuto l'informazione sul num di giocatori
    private int indexPlayerOnTurn;

    public Trivia(ClientInterface clientInterface) {
        domanda = new Domanda();
        esc = new Escape();
        pGUI = new ArrayList<>();
        pGUI.clear();
        f = new TriviaFont();
        playerBack = new ArrayList<>();
        diamanti = new ArrayList<>();
        playerIcons=new ArrayList<>();
        this.clientInterface = clientInterface;
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

        rydia = new Image("res/char/rydial.png");
        ceodore = new Image("res/char/ceodor.png");
        kain = new Image("res/char/kainl.png");
        luca = new Image("res/char/lucal.png");

        playerIcons.add(rydia);
        playerIcons.add(ceodore);
        playerIcons.add(kain);
        playerIcons.add(luca);
        background = new Image("res/backgrounds/green_landscape.png");
        launch = new Image("res/buttons/Button_Launch/Button_Launch.png");
        initializeDiamonds();

        domanda.init(gameContainer, stateBasedGame);
        esc.init(gameContainer, stateBasedGame);
        fonx1 = new TrueTypeFont(f.getFont().deriveFont(23f), false);
        domanda.setClientInterface(clientInterface);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        int x = 1100;
        int y = 30;

        graphics.drawImage(background, 0, 0);
        graphics.drawImage(backgroundMap, 0, 0);
        graphics.drawImage(back, 1190, 860);
        graphics.drawImage(forward, 1050, 860);
        fonx1.drawString(1190, 280, "E' il turno di: " + pGUI.get(indexPlayerOnTurn).getName(), Color.black);
        launch.draw(1320, 860);

        for(int i=0; i<NPLAYERS; i++){
            if ((i==1) || (i==3)){
                x = 1400;
            }
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

      /*  rydia.draw(1100,30);
        ceodore.draw(1400, 30);
        kain.draw(1100, 150);
        luca.draw(1400, 150);*/

        for (PlayerGUI p : pGUI) { p.getPedina().draw(p.getxUpdate(), p.getyUpdate()); }

        if (launched) { d.getCurrentDie().draw(1550, 870); }

        /*
        Se la pedina si è fermata (ready=true) controllo se è una casella di bonus malus. Se non è una bonus malus
         controllo se è la casella iniziale, se non è allora posso renderizzare la domanda, altrimenti controllo
         se ho vinto o se passo il turno. Se ho risposto e mi è uscita la stringa, allora resetto ready e launched.
         */

        if (pGUI.get(indexPlayerOnTurn).isReady() ) {
            //entra se è una bonus/malus o random

            //ricevo check bonusmalus
            if(!checkreceivedBonusMalus){
                checkBonusMalus=clientInterface.checkBonusMalus();
                checkreceivedBonusMalus=true;
            }
            //se il checkbonusmalus è vero esegui l'effetto del bonus malus
            if (checkBonusMalus) {
                //ricevo il tipo estratto
                if(!checkreceivedtype){
                    checktype=clientInterface.getType();
                    checkreceivedtype=true;
                }
                //a seconda del tipo estratto eseguo bonus o malus
                switch (checktype) {
                    case BONUS: {
                        fonx1.drawString(1190, 700, "PUOI RILANCIARE IL DADO!", Color.black);
                        break;
                    }
                    case MALUS: {
                        fonx1.drawString(1190, 700, "HAI PERSO IL TURNO!", Color.black);
                        break;
                    }
                }
            }
            // se non sono nella casella iniziale visualizzo una domanda
            else {
                //ricevo check se la casella in cui sono è la casella iniziale
                if(!checkreceivedInitialSquare){
                    checkinitialSquare=clientInterface.getCheckInitialSquare();
                    checkreceivedInitialSquare=true;
                }
                //se non sono sulla casella iniziale renderizzo la domanda
                if (!checkinitialSquare) {
                    domanda.render(gameContainer, stateBasedGame, graphics);
                    if (domanda.isEsito()) {
                        if (!checkReceivedSlices) {
                            Categories c = clientInterface.getCategoriesOfTheSliceObtained();
                            System.out.println(c.name());
                            if (!c.equals(Categories.Nessuna)) {
                                Slice slice = new Slice(c);
                                pGUI.get(indexPlayerOnTurn).addSliceObtained(slice);
                            }
                            checkReceivedSlices = true;
                        }
                    }
                }
                // se sono nella casella iniziale controllo se ho abbastanza spicchi per la vittoria, altrimenti
                // passa il turno
                else {
                    if(!checkreceivedVictory){
                        checkplayerVictory=clientInterface.receiveOutcome();
                        checkreceivedVictory=true;
                    }
                    if (checkplayerVictory) {
                        fonx1.drawString(1190, 700, "HAI VINTO", Color.black);
                        checkVictory = true;
                    } else {
                        fonx1.drawString(1190, 700, "CASELLA INIZIALE, PASSI IL TURNO!", Color.black);
                        pGUI.get(indexPlayerOnTurn).setReady(false);
                        launched = false;
                    }
                }
            }
            pGUI.get(indexPlayerOnTurn).setReady(false);
            launched = false;
        }

        //se il flag quit = true vuol dire che ho premuto Esc e renderizzo lo state esc.
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
        float xpos = Mouse.getX();
        float ypos = Mouse.getY();
        Input input = gameContainer.getInput();

        if(!checkreceivedInformationPLAYERS) initializePlayers();

        /*
        Se sono nelle coordinate del bottone Launch, ottengo il numero estratto, metto launched a true e aggiorno la
        faccia del dado. Resetto answered, esito a false perchè risponderò ad una domanda.
         */
        if (xpos > 1322 && xpos < 1505 && ypos > 57 && ypos < 143) {
            if (input.isMousePressed(0) && !launched) {
                clientInterface.sendOutcome(true); //comunica al server che deve eseguire setplayerOnturn
                indexPlayerOnTurn=clientInterface.getIndex();
                pGUI.get(indexPlayerOnTurn).setClicked(false);
                launched = true;
                diceN = clientInterface.getDiceValue();
                d.setCurrentDie(diceN);
                domanda.reset();
                resetBoolean();
            }
        }

        /*
        se ho lanciato il dado, controllo le coordinate in cui ho cliccato (una delle due frecce).
        in uno o nell'altro caso, informo il controller della direzione presa, aggiorno le coordinate delle gui
         */
        if (launched) {
            if (input.isMousePressed(0)) {
                if (ypos > 45 && ypos < 145) {
                    if (xpos > 1045 && xpos < 1160) {
                        pGUI.get(indexPlayerOnTurn).setClicked(true);
                        clientInterface.sendDirection(Direction.FORWARD);
                        pGUI.get(indexPlayerOnTurn).getP().update(diceN, Direction.FORWARD);
                        pGUI.get(indexPlayerOnTurn).updateCoordinates();
                    }
                    if (xpos > 1180 && xpos < 1290) {
                        pGUI.get(indexPlayerOnTurn).setClicked(true);
                        clientInterface.sendDirection(Direction.BACK);
                        pGUI.get(indexPlayerOnTurn).getP().update(diceN, Direction.BACK);
                        pGUI.get(indexPlayerOnTurn).updateCoordinates();
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

        pGUI.get(indexPlayerOnTurn).updateOnEachFrame(i);
        if (input.isKeyPressed(Input.KEY_ESCAPE)) { esc.setQuit(true); }

        if (checkVictory) {
            pause();
            gameContainer.exit();
        }
    }

    //non occcorre piu che ricevo dallo state precedente il num di giocatori, lo prendo dal client direttamente
    //con questo metodo istanzio i giocatori nell'ordine di gioco corretto coerentemente con lo state precedente
    public void initializePlayers()throws SlickException{
        ArrayList<String> nicknames=new ArrayList<>(clientInterface.getNicknames());
        NPLAYERS=nicknames.size();
        for (int i = 0; i <NPLAYERS; i++) {
            Player p = new Player(nicknames.get(i), i + 1, map);
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
            case Geografia:
                graphics.drawImage(diamanti.get(5),x+100,y+40 );
                break;
            case Storia:
                graphics.drawImage(diamanti.get(3), x+125,y+40);
                break;
            case Scienze:
                graphics.drawImage(diamanti.get(4), x+150,y+40);
                break;
            case Spettacolo:
                graphics.drawImage(diamanti.get(2), x+175,y+40);
                break;
            case ArteLetteratura:
                graphics.drawImage(diamanti.get(1), x+200,y+40);
                break;
            case Sport:
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
        checkreceivedBonusMalus=false;
        checkreceivedtype=false;
        checkreceivedInitialSquare=false;
        checkreceivedVictory=false;
        domanda.setCheckreceivedQuestion(false);
        checkReceivedSlices = false;
    }
}




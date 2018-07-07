package Client.Graphics;

import Client.Graphics.ClientInteface.ClientInterface;
import Client.Graphics.Fonts.TriviaFont;
import Client.Graphics.com.sticky.StateButton;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import java.util.ArrayList;

/**
 * State in cui viene fatto il lancio del dado e viene mostrato l'ordine effettivo di gioco.
 * - nPlayers: numero di giocatori selezionati
 * - background: immagine di background
 * - rydia, ceodore, kain, luca : immagini pedine
 * - images: arrayList di immagini delle pedine
 * - usernames: arrayList degli username ordinati
 * - launch: bottone per lanciare il dado per l'ordine dei giocatori
 * - next: bottone per passare allo state Trivia
 * - isClicked: verifica se launch è stato cliccato
 * - f, fonx1: font
 * - dadi: array di dadi da visualizzare
 * - clientInterface: oggetto per comunicare con il server
 * - checkReceivedInformation: boolean che diventa true se ho ricevuto le informazioni da visualizzare dal server
 */
public class GameOrderState extends BasicGameState {
    private int nPlayers=1;
    private Image background;
    private Image rydia, ceodore, kain, luca;
    private ArrayList<Image>images;
    private ArrayList<String>usernames;
    private StateButton launch;
    private StateButton next;
    private boolean isClicked=false;
    private TrueTypeFont fonx1;
    private TriviaFont f;
    private ArrayList<DieGUI> dadi;
    private ClientInterface clientInterface;
    private boolean checkReceivedInformation;

    GameOrderState(ClientInterface clientInterface) {
        f=new TriviaFont();
        this.clientInterface = clientInterface;
    }

    @Override
    public int getID() { return 4; }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        background=new Image("res/backgrounds/green_landscape.png");
        fonx1 = new TrueTypeFont(f.getFont().deriveFont(24f),false);
        rydia = new Image("res/char/FFIV/rydial.png");
        ceodore = new Image("res/char/FFIV/ceodor.png");
        kain = new Image("res/char/FFIV/kainl.png");
        luca = new Image("res/char/FFIV/lucal.png");

        images=new ArrayList<>();
        images.add(rydia);
        images.add(ceodore);
        images.add(kain);
        images.add(luca);

        dadi=new ArrayList<>();
        next=new StateButton(new Rectangle(780,750,100,101),new Image("res/buttons/Button_MenuFrame/next0.png"),new Image("res/buttons/Button_MenuFrame/next1.png"),new Image("res/buttons/Button_MenuFrame/back0.png"),null);
        launch=new StateButton(new Rectangle(740,750,185,91),new Image("res/buttons/Button_Launch/Button_Launch_0.png"),new Image("res/buttons/Button_Launch/Button_Launch.png"),new Image("res/buttons/Button_Launch/Button_Launch.png"),null);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        graphics.drawImage(background,0,0);
        fonx1.drawString(720,25,"ORDINE DI GIOCO", Color.black);
        //se launch è stato cliccato, render del button per il passaggio allo state successivo
        if(checkReceivedInformation) {
            fonx1.drawString(1060,25,"RISULTATI" ,Color.black);
            next.render(gameContainer, graphics);
        }
        else{
            fonx1.drawString(200, 775, "PREMI LAUNCH PER LANCIARE IL DADO :",Color.black);
            launch.render(gameContainer,graphics);
        }
        int yTemp=80; //coordinata y per immagini e stringhe username
        int bias=180; //n pixel tra un immagine e l'altra

        //in base a nPlayers, cambia il numero di elementi visualizzati
        for(int j=0;j<nPlayers;j++){
            graphics.drawImage(images.get(j),700,yTemp+bias*j);
            //se launch è cliccato, visualizza gli username ordinati nell'arraylist
            if(checkReceivedInformation) {
                fonx1.drawString(780, yTemp + bias * j, usernames.get(j));
                fonx1.drawString(500, yTemp + bias*j +25, "GIOCATORE " + (j + 1) + ":", Color.black);
                graphics.drawImage(dadi.get(j).getCurrentDie(),1080,yTemp + bias * j+10);
            }
            else
                fonx1.drawString(780,yTemp+bias*j,"*********");
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input r=gameContainer.getInput();

        if(r.isMousePressed(0)){
            //quando schiaccio il tasto launch isclicked diventa true
            isClicked=launch.onClickBoolean(r.getMouseX(),r.getMouseY());
            //se ho gia ricevuto le info dal server, quando schiaccio su next vai nel prossimo state
            if(checkReceivedInformation) next.onClickState(r.getMouseX(),r.getMouseY(),stateBasedGame,5);
        }

        //se premo launch e non ho ancora ricevuto le info dal server entro nell'if per riceverle
        if(isClicked && !checkReceivedInformation){
            usernames=clientInterface.getNicknames();
            ArrayList<Integer> resultsOfRoll=clientInterface.getResultsOfRoll();
            for(int j=0;j<usernames.size();j++){
                dadi.add(new DieGUI());
                dadi.get(j).setCurrentDie(resultsOfRoll.get(j));
            }
            checkReceivedInformation=true;
        }

        launch.onMouseEnter(launch,r.getMouseX(),r.getMouseY());
        next.onMouseEnter(next,r.getMouseX(),r.getMouseY());
    }

    /**metodo che "setta" il valore di nPlayers. Viene chiamato nello state precedente. */
    void getPlayerNumber(int n){ this.nPlayers=n; }
}



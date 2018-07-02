package Client.Graphics;

import Client.Graphics.Fonts.TriviaFont;
import Client.Graphics.com.sticky.StateButton;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

public class GameOrderState extends BasicGameState {
    //INT
    private int nPlayers=1; //numero di giocatori selezionati

    //IMMAGINI
    private Image background; //immagine di background
    private Image rydia, ceodore, kain, luca; //immagini pedine

    private ArrayList<Image>images; //arraylist immagini pedine
    private ArrayList<String>usernames; //arraylist degli username ordinati

    //STATEBUTTON
    private StateButton launch; //esegue metodo per ordine giocatori
    private StateButton next; //passaggio allo state successivo

    private boolean isClicked=false; //verifica se launch è stato cliccato

    //FONT
    private TrueTypeFont fonx1;
    private TriviaFont f;

    //DADI
    private ArrayList<DieGUI> dadi;

    //CLIENTINTERFACE
    private ClientInterface clientInterface;

    private boolean checkReceivedInformation; //diventa true se ho ricevuto le informazioni da visualizzare dal server
    public GameOrderState(int i, ClientInterface clientInterface) {
        f=new TriviaFont();
        this.clientInterface = clientInterface;
    }

    @Override
    public int getID() {
        return 4;
    }

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
        /*
        //riceve i nickname dei giocatori dal server
        usernames=clientInterface.getNicknames();
        System.out.println("NUMERO DI NICKNAMES RICEVUTI " + usernames.size());
        //riceve i risultati estratti dei dadi dal server
        ArrayList<DieGUI> dadi=new ArrayList<>();
        for(int i=0;i<usernames.size();i++){
            dadi.add(new DieGUI());
            //dadi.get(i).setCurrentDie(clientInterface.getDiceValue());
            dadi.get(i).setCurrentDie(1);
        }
        */
        dadi=new ArrayList<>();
        next=new StateButton(new Rectangle(780,750,100,101),new Image("res/buttons/Button_MenuFrame/next0.png"),new Image("res/buttons/Button_MenuFrame/next1.png"),new Image("res/buttons/Button_MenuFrame/back0.png"),null);
        launch=new StateButton(new Rectangle(740,750,100,101),new Image("res/buttons/Button_Launch/Button_Launch.png"),new Image("res/buttons/Button_Launch/Button_Launch.png"),new Image("res/buttons/Button_Launch/Button_Launch.png"),null);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawImage(background,0,0);
        fonx1.drawString(720,25,"ORDINE DI GIOCO", Color.black);
        if(checkReceivedInformation) { //se launch è stato cliccato, render del button per il passaggio allo state successivo
            next.render(gameContainer, graphics);
        }
        else{
            launch.render(gameContainer,graphics);
        }
        int yTemp=80; //coordinata y per immagini e stringhe username
        int bias=180; //n pixel tra un immagine e l'altra

        //in base a nPlayers, cambia il numero di elementi visualizzati
        for(int j=0;j<nPlayers;j++){
            graphics.drawImage(images.get(j),700,yTemp+bias*j);
            if(checkReceivedInformation) {//se launch è cliccato, visualizza gli username ordinati nell'arraylist
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
            isClicked=launch.onClickBoolean(r.getMouseX(),r.getMouseY()); //se launch è cliccato, isclicked assume il valore boolean in return dal metodo(true)
            if(isClicked){
                //next.onClickState(r.getMouseX(),r.getMouseY(),stateBasedGame,5);
                //riceve i nickname dei giocatori dal server
                usernames=clientInterface.getNicknames();
                System.out.println("NUMERO DI NICKNAMES RICEVUTI " + usernames.size());
                //riceve i risultati estratti dei dadi dal server
                for(int j=0;j<usernames.size();j++){
                    dadi.add(new DieGUI());
                    //dadi.get(i).setCurrentDie(clientInterface.getDiceValue());
                    dadi.get(j).setCurrentDie(1);
                }
                checkReceivedInformation=true;
            }
            if(isClicked && checkReceivedInformation) {

            }
        }

        launch.onMouseEnter(launch,r.getMouseX(),r.getMouseY());
        next.onMouseEnter(next,r.getMouseX(),r.getMouseY());
    }

    //metodo che "setta" il valore di nPlayers. Viene chiamato nello state precedente.
    public void getPlayerNumber(int n){
        this.nPlayers=n;
    }
}



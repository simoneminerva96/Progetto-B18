package Client.Graphics;

import Client.Graphics.Fonts.MenuFrame;
import Client.Graphics.Fonts.TriviaFont;
import Client.Graphics.com.sticky.StateButton;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

public class CharacterSelection extends BasicGameState {

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

    public CharacterSelection(int i) throws SlickException {
        f=new TriviaFont();
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

        usernames=new ArrayList<>();
        usernames.add("PLAYER1");
        usernames.add("PLAYER2");
        usernames.add("PLAYER3");
        usernames.add("PLAYER4");

        next=new StateButton(new Rectangle(780,750,100,101),new Image("res/buttons/Button_MenuFrame/next0.png"),new Image("res/buttons/Button_MenuFrame/next1.png"),new Image("res/buttons/Button_MenuFrame/back0.png"),null);
        launch=new StateButton(new Rectangle(780,750,100,101),new Image("res/buttons/Button_Play/Button_Play_01.png"),new Image("res/buttons/Button_Play/Button_Play_02.png"),new Image("res/buttons/Button_Play/Button_Play_01.png"),null);

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawImage(background,0,0);

        if(isClicked) //se launch è stato cliccato, render del button per il passaggio allo state successivo
            next.render(gameContainer,graphics);
        else
            launch.render(gameContainer,graphics);

        int yTemp=80; //coordinata y per immagini e stringhe username
        int bias=180; //n pixel tra un immagine e l'altra

        //in base a nPlayers, cambia il numero di elementi visualizzati
        for(int j=0;j<nPlayers;j++){
            graphics.drawImage(images.get(j),700,yTemp+bias*j);
            if(isClicked )//se launch è cliccato, visualizza gli username ordinati nell'arraylist
                 fonx1.drawString(780,yTemp+bias*j,usernames.get(j));
            else
                fonx1.drawString(780,yTemp+bias*j,"*********");

        }



    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input r=gameContainer.getInput();
        if(r.isMousePressed(0)){
            if(isClicked){
                next.onClickState(r.getMouseX(),r.getMouseY(),stateBasedGame,5);

            }
            isClicked=launch.onClickBoolean(r.getMouseX(),r.getMouseY()); //se launch è cliccato, isclicked assume il valore boolean in return dal metodo(true)

        }

        launch.onMouseEnter(launch,r.getMouseX(),r.getMouseY());
        next.onMouseEnter(next,r.getMouseX(),r.getMouseY());
    }

    //metodo che "setta" il valore di nPlayers. Viene chiamato nello state precedente.
    public void getPlayerNumber(int n){
        this.nPlayers=n;
    }
}



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

    //DEBUG
    public String mouse= "No input";
    Boolean clicked=false;
    int xb=100,yb=100;


    int nPlayers=1;
    int t=0;

    private boolean isClicked=false;

    private Image background;
    TrueTypeFont font;
    private String gameName;

    private Image rydia, ceodore, kain, luca;

    private ArrayList<Image>images;
    private ArrayList<String>usernames;

    private TrueTypeFont fonx1;

    private StateButton launch;
    private StateButton next;

    UnicodeFont fonx;
    private TriviaFont f;

    int playerN=1;



    //STATES
    MenuFrame mf=new MenuFrame(10);

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
        gameName="Trivial Pursuit";
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
      //  next.render(gameContainer,graphics);
        if(isClicked)
            next.render(gameContainer,graphics);
        else
            launch.render(gameContainer,graphics);

        int yTemp=80;
        int bias=180;
        for(int j=0;j<nPlayers;j++){
            graphics.drawImage(images.get(j),700,yTemp+bias*j);
            if(isClicked )
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
            isClicked=launch.onClickBoolean(r.getMouseX(),r.getMouseY());

        }

        launch.onMouseEnter(launch,r.getMouseX(),r.getMouseY());
        next.onMouseEnter(next,r.getMouseX(),r.getMouseY());

     /*   while(true){
            if(t==usernames.size()-1){
                t=0;
            }
            t++;
        }*/



    }

    public void getPlayerNumber(int n){
        this.nPlayers=n;
    }
}



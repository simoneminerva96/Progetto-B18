package Graphics.OfflineMenu;

import Graphics.com.sticky.StateButton;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import Graphics.*;

public class CharacterSelection extends BasicGameState {

    //DEBUG
    public String mouse= "No input";
    Boolean clicked=false;
    int xb=100,yb=100;

    private Image background;
    TrueTypeFont font;
    private String gameName;

    private StateButton play;

    UnicodeFont fonx;

    int playerN=1;

    //STATES
    MenuFrame mf=new MenuFrame(10);

    public CharacterSelection(int i) throws SlickException {
    }

    @Override
    public int getID() {
        return 4;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        background=new Image("res/backgrounds/green_landscape.png");
        gameName="Trivial Pursuit";
        mf.init(gameContainer,stateBasedGame);
        mf.setBackState(4);


    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawImage(background,0,0);
        mf.render(gameContainer,stateBasedGame,graphics);

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input r=gameContainer.getInput();
        mf.setMouseCoordinates(r.getMouseX(),r.getMouseY());
        if(r.isMousePressed(0)) {
            mf.setMouseClicked(true);
            System.out.println(playerN);
            mf.update(gameContainer,stateBasedGame,i);
        }
        mf.setMouseClicked(false);
        mf.update(gameContainer,stateBasedGame,i);

    }

    public void getPlayerNumber(int n){
        this.playerN=n;
    }
}



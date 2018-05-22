package Graphics.OfflineMenu;

import Graphics.com.sticky.SimpleButton;
import Graphics.com.sticky.StateButton;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;



public class CharacterSelection extends BasicGameState {


    public String mouse= "No input";

    Boolean clicked=false;


    SimpleButton b;
    int xb=100,yb=100;


    private SpriteSheet currentSprite;
    private Animation currentImage;


    private Image background;

    TrueTypeFont font;    private String gameName;


    private StateButton play;

    private TextField textField;
    UnicodeFont fonx;

    int playerN=1;

    //STATES

    //BOOLEAN 4 STATES

    boolean loginEnter=false,loginUpdateEnter=false,loginRenderEnter=false;

    public CharacterSelection(int i) throws SlickException {
    }

    @Override
    public int getID() {
        return 6;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        background=new Image("res/backgrounds/green_landscape_ridim.png");
        gameName="Trivial Pursuit";


        play=new StateButton(new Rectangle(550,325,190,49),new Image("res/buttons/fantasy/Button_Login_01.png"),new Image("res/buttons/fantasy/Button_Login_02.png"),new Image("res/buttons/fantasy/Button_Login_01.png"),null);

        fonx = getNewFont("Arial" , 16);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawImage(background,0,0);
        font.drawString(400,25,"TRIVIAL PURSUIT RELOADED", Color.white);

       // play.render(gameContainer,graphics);
        // font.drawString(600,330,"PLAY", Color.white);
        //   textField.render(gameContainer,graphics);
        //   graphics.setFont(fonx);




    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input r=gameContainer.getInput();

        if(r.isMousePressed(0)) {

        }


    }

    public void enter(GameContainer gameContainer,StateBasedGame stateBasedGame)throws SlickException{
        textField=new TextField(gameContainer,fonx,550,600,200,35);
    }

    public UnicodeFont getNewFont(String fontName , int fontSize)
    {
        fonx = new UnicodeFont(new java.awt.Font(fontName , java.awt.Font.PLAIN , fontSize));
        fonx.addGlyphs("@");
        fonx.getEffects().add(new ColorEffect(java.awt.Color.white));
        return (fonx);
    }

    public void getPlayerNumber(int n){
        this.playerN=n;
    }
}



package Graphics;



import Graphics.com.sticky.NumberButton;
import Graphics.com.sticky.SimpleButton;
import Graphics.com.sticky.StateButton;
import Graphics.com.sticky.TestButton;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.gui.ComponentListener;

import java.awt.*;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;

import Graphics.*;

public class MenuFrame extends BasicGameState {

    //DEBUGGING
    public String mouse= "No input";
    int xb=100,yb=100;
    Boolean clicked=false;



    private Image background, menuback;
    private TrueTypeFont fonx,fonx1;
    private UnicodeFont font;

    private String gameName;

    //BUTTONS
    private StateButton home,back,next,sound;
   // private NumberButton one,two,three,four;

    private int numbertosend=1;


    public MenuFrame(int i) throws SlickException {
    }

    @Override
    public int getID() {
        return 10;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

        background =new Image("res/backgrounds/green_landscape_ridim.png");
        menuback =new Image("res/backgrounds/Windows_02.png");
        home=new StateButton(new Rectangle(548,502,100,101),new Image("res/buttons/Button_MenuFrame/home0.png"),new Image("res/buttons/Button_MenuFrame/home1.png"),new Image("res/buttons/Button_MenuFrame/home0.png"),null);
        back=new StateButton(new Rectangle(448,502,100,101),new Image("res/buttons/Button_MenuFrame/back0.png"),new Image("res/buttons/Button_MenuFrame/back1.png"),new Image("res/buttons/Button_MenuFrame/back0.png"),null);
        next=new StateButton(new Rectangle(748,502,100,101),new Image("res/buttons/Button_MenuFrame/next0.png"),new Image("res/buttons/Button_MenuFrame/next1.png"),new Image("res/buttons/Button_MenuFrame/back0.png"),null);
        sound=new StateButton(new Rectangle(648,502,100,101),new Image("res/buttons/Button_MenuFrame/sound0.png"),new Image("res/buttons/Button_MenuFrame/sound1.png"),new Image("res/buttons/Button_MenuFrame/sound0.png"),null);

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {

        graphics.drawImage(menuback,300,50);
        home.render(gameContainer,graphics);
        back.render(gameContainer,graphics);
        next.render(gameContainer,graphics);
        sound.render(gameContainer,graphics);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input r=gameContainer.getInput();

        if(r.isMousePressed(0)) {


        }else if(r.isMousePressed(1)){

        }

        home.onMouseEnter(home,r.getMouseX(),r.getMouseY());
        back.onMouseEnter(back,r.getMouseX(),r.getMouseY());
        next.onMouseEnter(next,r.getMouseX(),r.getMouseY());
        sound.onMouseEnter(sound,r.getMouseX(),r.getMouseY());


    }



    public UnicodeFont getNewFont(String fontName , int fontSize)
    {
        font = new UnicodeFont(new Font(fontName , Font.PLAIN , fontSize));
        font.addGlyphs("@");
        font.getEffects().add(new ColorEffect(java.awt.Color.white));
        return (font);
    }
}



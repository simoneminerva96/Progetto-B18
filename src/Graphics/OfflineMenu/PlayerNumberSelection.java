package Graphics.OfflineMenu;



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

public class PlayerNumberSelection extends BasicGameState {

    //DEBUGGING
    public String mouse= "No input";
    int xb=100,yb=100;
    Boolean clicked=false;

    public static int numbertosend=1;



    private Image background, registrationback,menuback;
    private TrueTypeFont fonx,fonx1;
    private UnicodeFont font;

    private String gameName;
   // private int numbertosend=1;
    private boolean nextStateSendNumber=false;

    //BUTTONS
    private NumberButton one,two,three,four;
    private StateButton home,back,next,sound;




    //STATE
    MenuFrame mf=new MenuFrame(10);
    CharacterSelection cr=new CharacterSelection(4);


    public PlayerNumberSelection(int i) throws SlickException {
    }

    @Override
    public int getID() {
        return 3;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

        background =new Image("res/backgrounds/green_landscape_ridim.png");
        registrationback =new Image("res/backgrounds/Windows_02.png");

        one=new NumberButton(new Rectangle(525,180,100,101),new Image("res/buttons/Button_Numbers/Button1_0.png"),new Image("res/buttons/Button_Numbers/Button1_1.png"),null,1);
        two=new NumberButton(new Rectangle(675,180,100,101),new Image("res/buttons/Button_Numbers/Button2_0.png"),new Image("res/buttons/Button_Numbers/Button2_1.png"),null,2);
        three=new NumberButton(new Rectangle(525,305,100,101),new Image("res/buttons/Button_Numbers/Button3_0.png"),new Image("res/buttons/Button_Numbers/Button3_1.png"),null,3);
        four=new NumberButton(new Rectangle(675,305,100,101),new Image("res/buttons/Button_Numbers/Button4_0.png"),new Image("res/buttons/Button_Numbers/Button4_1.png"),null,4);
        mf.init(gameContainer,stateBasedGame);
        mf.setIsNumberToSend(true);
        mf.setBackState(2);
        }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawImage(background,0,0);
        mf.render(gameContainer,stateBasedGame,graphics);
        one.render(gameContainer,graphics);
        two.render(gameContainer,graphics);
        three.render(gameContainer,graphics);
        four.render(gameContainer,graphics);






    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input r=gameContainer.getInput();

        mf.setMouseCoordinates(r.getMouseX(),r.getMouseY());

        if(r.isMousePressed(0)) {
            System.out.println("ENTRO");

            mf.setMouseClicked(true);
            numbertosend=one.onClickGetNumber(r.getMouseX(),r.getMouseY(),numbertosend);
            numbertosend=two.onClickGetNumber(r.getMouseX(),r.getMouseY(),numbertosend);
            numbertosend=three.onClickGetNumber(r.getMouseX(),r.getMouseY(),numbertosend);
            numbertosend=four.onClickGetNumber(r.getMouseX(),r.getMouseY(),numbertosend);
            mf.setNumbertosend(numbertosend);
            mf.update(gameContainer,stateBasedGame,i);

        }else if(r.isMousePressed(1)){
            System.out.println(numbertosend);
        }

        mf.setMouseClicked(false);

        one.onMouseEnter(one,r.getMouseX(),r.getMouseY());
        two.onMouseEnter(two,r.getMouseX(),r.getMouseY());
        three.onMouseEnter(three,r.getMouseX(),r.getMouseY());
        four.onMouseEnter(four,r.getMouseX(),r.getMouseY());
        mf.update(gameContainer,stateBasedGame,i);





    }



    public UnicodeFont getNewFont(String fontName , int fontSize)
    {
        font = new UnicodeFont(new Font(fontName , Font.PLAIN , fontSize));
        font.addGlyphs("@");
        font.getEffects().add(new ColorEffect(java.awt.Color.white));
        return (font);
    }

}


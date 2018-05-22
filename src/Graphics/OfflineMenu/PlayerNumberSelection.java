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



    private Image background, registrationback;
    private TrueTypeFont fonx,fonx1;
    private UnicodeFont font;

    private String gameName;

    //BUTTONS
    private StateButton next;
    private NumberButton one,two,three,four;

    private int numbertosend=1;

    //STATE
    MenuFrame mf=new MenuFrame(10);


    public PlayerNumberSelection(int i) throws SlickException {
    }

    @Override
    public int getID() {
        return 3;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        font = getNewFont("Arial" , 18);
        background =new Image("res/backgrounds/green_landscape_ridim.png");
        registrationback =new Image("res/backgrounds/Windows_02.png");
        try {
            InputStream inputStream	= ResourceLoader.getResourceAsStream("res/fonts/Silkscreen/slkscr.ttf");
            java.awt.Font awtFont= java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,inputStream);
            awtFont=awtFont.deriveFont(32f);
            fonx=new TrueTypeFont(awtFont,false);
            fonx1=new TrueTypeFont(awtFont.deriveFont(23f),false);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        one=new NumberButton(new Rectangle(525,180,100,101),new Image("res/buttons/Button_Numbers/Button1_0.png"),new Image("res/buttons/Button_Numbers/Button1_1.png"),null,1);
        two=new NumberButton(new Rectangle(675,180,100,101),new Image("res/buttons/Button_Numbers/Button2_0.png"),new Image("res/buttons/Button_Numbers/Button2_1.png"),null,2);
        three=new NumberButton(new Rectangle(525,305,100,101),new Image("res/buttons/Button_Numbers/Button3_0.png"),new Image("res/buttons/Button_Numbers/Button3_1.png"),null,3);
        four=new NumberButton(new Rectangle(675,305,100,101),new Image("res/buttons/Button_Numbers/Button4_0.png"),new Image("res/buttons/Button_Numbers/Button4_1.png"),null,4);
        mf.init(gameContainer,stateBasedGame);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawImage(background,0,0);
        mf.render(gameContainer,stateBasedGame,graphics);
        font.drawString(400,25,"TRIVIAL PURSUIT RELOADED", Color.white);
        one.render(gameContainer,graphics);
        two.render(gameContainer,graphics);
        three.render(gameContainer,graphics);
        four.render(gameContainer,graphics);






    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input r=gameContainer.getInput();

        if(r.isMousePressed(0)) {
            numbertosend=one.onClickGetNumber(r.getMouseX(),r.getMouseY(),numbertosend);
            numbertosend=two.onClickGetNumber(r.getMouseX(),r.getMouseY(),numbertosend);
            numbertosend=three.onClickGetNumber(r.getMouseX(),r.getMouseY(),numbertosend);
            numbertosend=four.onClickGetNumber(r.getMouseX(),r.getMouseY(),numbertosend);

        }else if(r.isMousePressed(1)){
            System.out.println(numbertosend);
        }

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


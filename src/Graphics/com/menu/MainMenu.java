package Graphics.com.menu;

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

public class MainMenu extends BasicGameState {


    public String mouse= "No input";

    Boolean clicked=false;


    SimpleButton b;
    int xb=100,yb=100;


    private SpriteSheet currentSprite;
    private Animation currentImage;


    private Image background;

    TrueTypeFont font;    private String gameName;


    private StateButton play,offline;

    private TextField textField;
    UnicodeFont fonx;

    public MainMenu(int i) throws SlickException {
    }

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
            background=new Image("res/backgrounds/green_landscape_ridim.png");
            gameName="Trivial Pursuit";
            try {
            InputStream inputStream	= ResourceLoader.getResourceAsStream("res/fonts/Silkscreen/slkscr.ttf");
            java.awt.Font awtFont= java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,inputStream);
            awtFont=awtFont.deriveFont(32f);
            font=new TrueTypeFont(awtFont,false);
            } catch (FontFormatException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            play=new StateButton(new Rectangle(550,325,190,49),new Image("res/buttons/fantasy/Button_Login_01.png"),new Image("res/buttons/fantasy/Button_Login_02.png"),new Image("res/buttons/fantasy/Button_Login_01.png"),null);
            offline=new StateButton(new Rectangle(550,450,190,49),new Image("res/buttons/fantasy/Button_Login_01.png"),new Image("res/buttons/fantasy/Button_Login_02.png"),new Image("res/buttons/fantasy/Button_Login_01.png"),null);
            fonx = getNewFont("Arial" , 16);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
            graphics.drawImage(background,0,0);
            font.drawString(400,25,"TRIVIAL PURSUIT RELOADED", Color.white);

            play.render(gameContainer,graphics);
            offline.render(gameContainer,graphics);
           // font.drawString(600,330,"PLAY", Color.white);
         //   textField.render(gameContainer,graphics);
         //   graphics.setFont(fonx);




    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input r=gameContainer.getInput();

        if(r.isMousePressed(0)) {
            play.onClickState(r.getMouseX(),r.getMouseY(),stateBasedGame,2);
            offline.onClickState(r.getMouseX(),r.getMouseY(),stateBasedGame,3);
        }

        play.onMouseEnter(play,r.getMouseX(),r.getMouseY());
        offline.onMouseEnter(offline,r.getMouseX(),r.getMouseY());


    }

    public void enter(GameContainer gameContainer,StateBasedGame stateBasedGame)throws SlickException{
        textField=new TextField(gameContainer,fonx,550,600,200,35);
    }

    public UnicodeFont getNewFont(String fontName , int fontSize)
    {
        fonx = new UnicodeFont(new Font(fontName , Font.PLAIN , fontSize));
        fonx.addGlyphs("@");
        fonx.getEffects().add(new ColorEffect(java.awt.Color.white));
        return (fonx);
    }
}

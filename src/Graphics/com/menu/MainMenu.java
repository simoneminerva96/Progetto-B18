package Graphics.com.menu;

import Graphics.com.sticky.StateButton;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.util.ResourceLoader;

import java.awt.*;
import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;

public class MainMenu extends BasicGameState {

    private Image background;
    TrueTypeFont font;
    private StateButton play, options;
    UnicodeFont fonx;

    public MainMenu(int i) throws SlickException {
    }

    @Override
    public int getID() {
        return 2;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
            background =new Image("res/backgrounds/green_landscape_ridim.png");
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

            play=new StateButton(new Rectangle(550,325,190,49),new Image("res/buttons/Button_Play/Button_Play_01.png"),new Image("res/buttons/Button_Play/Button_Play_02.png"),new Image("res/buttons/Button_Play/Button_Play_01.png"),null);
            options=new StateButton(new Rectangle(550,450,190,49),new Image("res/buttons/Button_Options/Button_Options_01.png"),new Image("res/buttons/Button_Options/Button_Options_02.png"),new Image("res/buttons/Button_Options/Button_Options_01.png"),null);
            fonx = getNewFont("Arial" , 16);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
            graphics.drawImage(background,0,0);
            font.drawString(400,25,"TRIVIAL PURSUIT RELOADED", Color.white);

            play.render(gameContainer,graphics);
            options.render(gameContainer,graphics);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input r=gameContainer.getInput();

        if(r.isMousePressed(0)) {
            play.onClickState(r.getMouseX(),r.getMouseY(),stateBasedGame,3);
            options.onClickState(r.getMouseX(),r.getMouseY(),stateBasedGame,4);
        }

        play.onMouseEnter(play,r.getMouseX(),r.getMouseY());
        options.onMouseEnter(options,r.getMouseX(),r.getMouseY());


    }

    public void enter(GameContainer gameContainer,StateBasedGame stateBasedGame)throws SlickException{
    }

    public UnicodeFont getNewFont(String fontName , int fontSize)
    {
        fonx = new UnicodeFont(new Font(fontName , Font.PLAIN , fontSize));
        fonx.addGlyphs("@");
        fonx.getEffects().add(new ColorEffect(java.awt.Color.white));
        return (fonx);
    }
}

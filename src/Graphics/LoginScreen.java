package Graphics;

import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;

import Graphics.com.sticky.FormButton;
import Graphics.com.sticky.SimpleButton;
import Graphics.com.sticky.StateButton;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

public class LoginScreen extends BasicGameState {
    TextField usrname,psw;
    UnicodeFont font;

    TrueTypeFont fonx,fonx1;
    Image background;
    Image loginback;

    StateButton back;
    FormButton enter;


    public LoginScreen(int state)
    {

    }

    public void init(GameContainer gc , StateBasedGame sbg) throws SlickException {
        font = getNewFont("Arial" , 18);
        background=new Image("res/backgrounds/green_landscape_ridim.png");
        loginback=new Image("res/backgrounds/Windows_09p.png");
        back=new StateButton(new Rectangle(421,460,90,91),new Image("res/buttons/fantasy/back_03.png"),new Image("res/buttons/fantasy/back_02.png"),new Image("res/buttons/fantasy/back_01.png"),null);
        enter=new FormButton(new Rectangle(675,460,185,91),new Image("res/buttons/fantasy/Button_Login_01.png"),new Image("res/buttons/fantasy/Button_Login_02.png"),new Image("res/buttons/fantasy/Button_Login_01.png"),null);
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

    }

    public void render(GameContainer gc , StateBasedGame sbg , Graphics g) throws SlickException {
        g.drawImage(background,0,0);
        g.drawImage(loginback,421,135);
        usrname.render(gc,g);
        psw.render(gc,g);
        back.render(gc,g);
        enter.render(gc,g);

        fonx1.drawString(510,205,"INSERISCI NICKNAME", org.newdawn.slick.Color.white);
        fonx1.drawString(510,300,"INSERISCI PASSWORD", org.newdawn.slick.Color.white);


    }

    public void update(GameContainer gc , StateBasedGame sbg , int delta) throws SlickException {
        font.loadGlyphs();
        Input r=gc.getInput();

        if(r.isMousePressed(0)) {
            back.onClick(back, r.getMouseX(), r.getMouseY());
            enter.onClickForm(enter,r.getMouseX(),r.getMouseY(),usrname.getText(),psw.getText());

        }
        back.onMouseEnter(back,r.getMouseX(),r.getMouseY());
        enter.onMouseEnter(enter,r.getMouseX(),r.getMouseY());

    }

    public int getID()
    {
        return 4;
    }

    public void enter(GameContainer gc , StateBasedGame sbg) throws SlickException {
        usrname = new TextField(gc , font , 540 , 245 , 200 , 35);
        usrname.setBackgroundColor(org.newdawn.slick.Color.lightGray);
        psw = new TextField(gc , font , 540 , 340 , 200 , 35);
        psw.setBackgroundColor(org.newdawn.slick.Color.lightGray);
    }

    public UnicodeFont getNewFont(String fontName , int fontSize) {
        System.out.println("GETNEWFONT");
        font = new UnicodeFont(new Font(fontName , Font.PLAIN , fontSize));
        font.addGlyphs("@");
        font.getEffects().add(new ColorEffect(java.awt.Color.white));
        return (font);

    }
}

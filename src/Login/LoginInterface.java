package Login;
/*
    interfaccia finale di login
 */
import java.awt.*;
import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;
import Graphics.com.sticky.FormButton;
import Graphics.*;
import Graphics.com.sticky.StateButton;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;
import javax.swing.*;

public class LoginInterface extends BasicGameState {
    TextField usrname;
    TextFieldTest psw;
    UnicodeFont font;

    TrueTypeFont fonx, fonx1;
    Image background;
    Image loginback;

    StateButton back;
    FormButton enter;
    TextFieldTest txfs;

    JTextField hi = new JTextField();
    Music music;
    private Login login;

    public LoginInterface(int state) {
        login = new Login();
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        music = new Music("res/music/Wallpaper.wav");
        music.loop();
        font = getNewFont("Arial", 18);
        background = new Image("res/backgrounds/green_landscape_ridim.png");
        loginback = new Image("res/backgrounds/Windows_09p.png");
        back = new StateButton(new Rectangle(421, 460, 90, 91), new Image("res/buttons/fantasy/back_03.png"), new Image("res/buttons/fantasy/back_02.png"), new Image("res/buttons/fantasy/back_01.png"), null);
        enter = new FormButton(new Rectangle(675, 460, 185, 91), new Image("res/buttons/fantasy/Button_Login_01.png"), new Image("res/buttons/fantasy/Button_Login_02.png"), new Image("res/buttons/fantasy/Button_Login_01.png"), null);
        try {
            InputStream inputStream = ResourceLoader.getResourceAsStream("res/fonts/Silkscreen/slkscr.ttf");
            java.awt.Font awtFont = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, inputStream);
            awtFont = awtFont.deriveFont(32f);
            fonx = new TrueTypeFont(awtFont, false);
            fonx1 = new TrueTypeFont(awtFont.deriveFont(23f), false);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        usrname = new TextField(gc, fonx1, 540, 245, 200, 35);
        psw = new TextFieldTest(gc, fonx1, 540, 340, 200, 35);
        psw.setBackgroundColor(org.newdawn.slick.Color.lightGray);

        psw.setMaskEnabled(true);


    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawImage(background, 0, 0);
        g.drawImage(loginback, 421, 135);
        usrname.render(gc, g);
        //txfs.render(gc,g);
        psw.render(gc, g);
        back.render(gc, g);
        enter.render(gc, g);


        fonx1.drawString(510, 205, "INSERISCI NICKNAME", org.newdawn.slick.Color.white);
        fonx1.drawString(510, 300, "INSERISCI PASSWORD", org.newdawn.slick.Color.white);


    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        font.loadGlyphs();
        Input r = gc.getInput();

        if (r.isMousePressed(0)) {
            back.onClickState(back, r.getMouseX(), r.getMouseY(), gc, sbg, 1);


        } else if (r.isMousePressed(1)) {
            enter.onClickForm(enter, r.getMouseX(), r.getMouseY(), usrname.getText(), psw.getText(),login);
        }
        back.onMouseEnter(back, r.getMouseX(), r.getMouseY());
        enter.onMouseEnter(enter, r.getMouseX(), r.getMouseY());

    }

    public int getID() {
        return 2;
    }

    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        /*usrname = new TextField(gc , font , 540 , 245 , 200 , 35);
        usrname.setBackgroundColor(org.newdawn.slick.Color.lightGray);*/
        /*txfs=new TextFieldTest(gc , font , 540 , 245 , 200 , 35);
        psw = new TextField(gc , font , 540 , 340 , 200 , 35);
        psw.setBackgroundColor(org.newdawn.slick.Color.lightGray);*/
    }

    public UnicodeFont getNewFont(String fontName, int fontSize) {
        System.out.println("GETNEWFONT");
        font = new UnicodeFont(new Font(fontName, Font.PLAIN, fontSize));
        font.addGlyphs("@");
        font.getEffects().add(new ColorEffect(java.awt.Color.white));
        return (font);

    }

}


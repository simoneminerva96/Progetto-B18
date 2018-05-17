package Login;

import Graphics.TextFieldTest;
import Graphics.com.sticky.FormButton;
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
import java.awt.*;
import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;

/*
    interfaccia di registrazione finale
 */
public class RegistrationInterface extends BasicGameState {

    TextField usrname;
    TextFieldTest psw;
    UnicodeFont font;
    TrueTypeFont fonx,fonx1;
    Image background, registrationback;
    StateButton back;
    FormButton enter;

    Registration reg;

    public RegistrationInterface(int n){
        this.reg = new Registration();
    }

    @Override
    public int getID() { return 3; }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        font = getNewFont("Arial" , 18);
        background =new Image("res/backgrounds/green_landscape_ridim.png");
        registrationback =new Image("res/backgrounds/Windows_09p.png");
        back = new StateButton(new Rectangle(421,460,90,91),new Image("res/buttons/fantasy/back_03.png"),new Image("res/buttons/fantasy/back_02.png"),new Image("res/buttons/fantasy/back_01.png"),null);
        enter = new FormButton(new Rectangle(675,460,185,91),new Image("res/buttons/fantasy/Button_Login_01.png"),new Image("res/buttons/fantasy/Button_Login_02.png"),new Image("res/buttons/fantasy/Button_Login_01.png"),null);
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

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawImage(background,0,0);
        graphics.drawImage(registrationback,421,135);
        usrname.render(gameContainer,graphics);
        psw.render(gameContainer,graphics);
        back.render(gameContainer,graphics);
        enter.render(gameContainer,graphics);

        fonx1.drawString(510,205,"INSERISCI NICKNAME", org.newdawn.slick.Color.white);
        fonx1.drawString(510,300,"INSERISCI PASSWORD", org.newdawn.slick.Color.white);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

        Input r = gameContainer.getInput();

        if(r.isMousePressed(0)) {
            back.onClickState(back, r.getMouseX(), r.getMouseY(),gameContainer,stateBasedGame,1);

        }else if(r.isMousePressed(0)){
            enter.onClickFormRegistration(r.getMouseX(),r.getMouseY(),usrname.getText(),psw.getText(), reg);
        }
        back.onMouseEnter(back,r.getMouseX(),r.getMouseY());
        enter.onMouseEnter(enter,r.getMouseX(),r.getMouseY());
    }

    public UnicodeFont getNewFont(String fontName , int fontSize) {
        System.out.println("GETNEWFONT");
        font = new UnicodeFont(new Font(fontName , Font.PLAIN , fontSize));
        font.addGlyphs("@");
        font.getEffects().add(new ColorEffect(java.awt.Color.white));
        return (font);

    }

    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        usrname=new TextField(gc , fonx1 , 540 , 245 , 200 , 35);
        psw = new TextFieldTest(gc , fonx1 , 540 , 340 , 200 , 35);
        psw.setBackgroundColor(org.newdawn.slick.Color.lightGray);
        psw.setMaskEnabled(true);
    }

}

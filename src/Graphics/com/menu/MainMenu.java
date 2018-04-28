package Graphics.com.menu;

import Graphics.com.sticky.SimpleButton;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;

public class MainMenu extends BasicGameState {


    public String mouse= "No input";

    Boolean clicked=false;


    SimpleButton b;
    int xb=100,yb=100;


    private SpriteSheet currentSprite;
    private Animation currentImage;


    private Image background;

    TrueTypeFont font;
    private String gameName;

    private SimpleButton play;

    public MainMenu(int i) throws SlickException {
    }

    @Override
    public int getID() {
        return 3;
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

        play=new SimpleButton(new Rectangle(550,325,190,49),new Image("res/buttons/yellow_button03.png"),new Image("res/buttons/blue_button03.png"),new Image("res/buttons/yellow_button03.png"),null);

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
            graphics.drawImage(background,0,0);
            font.drawString(400,200,"TRIVIAL PURSUIT RELOADED", Color.white);

            play.render(gameContainer,graphics);
            font.drawString(600,330,"PLAY", Color.white);




    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input r=gameContainer.getInput();

        if(r.isMousePressed(0)) {
            play.onClick(play, r.getMouseX(), r.getMouseY());
        }

        play.onMouseEnter(play,r.getMouseX(),r.getMouseY());
    /*    if(clicked==true){
            b.onClick(b, Mouse.getX(), 720 - Mouse.getY());

        }else {


        }
        if (Mouse.getX() >= xb && Mouse.getX() <= xb + 190 && 720 - Mouse.getY() >= yb && 720 - Mouse.getY() <= yb + 49) {
            b.onMouseEnter(b);
        } else {
            b.onMouseExit(b);
            clicked = false;

        }


        if(r.isMousePressed(0)){
            if(Mouse.getX()>=xb&&Mouse.getX()<=xb+190&&720-Mouse.getY()>=yb&&720-Mouse.getY()<=yb+49) {
                clicked=true;
            }
        }

        mouse="Mouse position x:"+Mouse.getX()+ " y: "+Mouse.getY();
        */
    }
}

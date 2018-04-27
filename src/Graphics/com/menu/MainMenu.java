package javagame.com.menu;

import javagame.com.sticky.aem.sticky.button.SimpleButton;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.event.MouseListener;

public class MainMenu extends BasicGameState {

    public String mouse= "No input";

    Boolean clicked=false;


    SimpleButton b;
    int xb=100,yb=100;

    private SpriteSheet MoveLeft; // initate a SprtieSheet
    private Animation MoveLeftAni; // initate a Animation
    private Animation currentImage;

    public MainMenu(int i){
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

        b=new SimpleButton(new Rectangle(xb,yb,190,49),new Image("res/menu/buttons/blue_button00.png"),new Image("res/menu/buttons/blue_button01.png"),new Image("res/menu/buttons/yellow_button01.png"),null);
        MoveLeft = new SpriteSheet("res/map/particleg.png",60,62);
        MoveLeftAni=new Animation(MoveLeft,90);
        currentImage=MoveLeftAni;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
            b.render(gameContainer,graphics);
            graphics.drawString(mouse,100,100);
            currentImage.draw(200,200);

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input r=gameContainer.getInput();

        if(clicked==true){
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

    }
}

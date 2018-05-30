package Graphics.OfflineMenu;

import Graphics.com.sticky.NumberButton;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Graphics.*;

public class PlayerNumberSelection extends BasicGameState {

    public String mouse= "No input";
    public static int numbertosend=1;
    private Image background;

    //BUTTONS
    private NumberButton one,two,three,four;

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

        background =new Image("res/backgrounds/green_landscape_ridim.png");

        one=new NumberButton(new Rectangle(525,180,100,101),new Image("res/buttons/Button_Numbers/Button1_0.png"),new Image("res/buttons/Button_Numbers/Button1_1.png"),null,1);
        two=new NumberButton(new Rectangle(675,180,100,101),new Image("res/buttons/Button_Numbers/Button2_0.png"),new Image("res/buttons/Button_Numbers/Button2_1.png"),null,2);
        three=new NumberButton(new Rectangle(525,305,100,101),new Image("res/buttons/Button_Numbers/Button3_0.png"),new Image("res/buttons/Button_Numbers/Button3_1.png"),null,3);
        four=new NumberButton(new Rectangle(675,305,100,101),new Image("res/buttons/Button_Numbers/Button4_0.png"),new Image("res/buttons/Button_Numbers/Button4_1.png"),null,4);
        mf.init(gameContainer,stateBasedGame);
        mf.setIsNumberToSend(true);
        mf.setBackState(1);
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
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
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
            mf.update(gameContainer,stateBasedGame,delta);


        }else if(r.isMousePressed(1)){
            System.out.println(numbertosend);
        }

        mf.setMouseClicked(false);

        one.onMouseEnter(one,r.getMouseX(),r.getMouseY());
        two.onMouseEnter(two,r.getMouseX(),r.getMouseY());
        three.onMouseEnter(three,r.getMouseX(),r.getMouseY());
        four.onMouseEnter(four,r.getMouseX(),r.getMouseY());
        mf.update(gameContainer,stateBasedGame,delta);

    }

}


package Client.Graphics;

import Client.Graphics.Fonts.MenuFrame;
import Client.Graphics.com.sticky.NumberButton;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class PlayerNumberSelection extends BasicGameState {

    public String mouse= "No input";
    public static int numbertosend=1;
    private Image background;
    private NumberButton one,two,three,four;
    private boolean[] array;
    private MenuFrame mf=new MenuFrame(10);
    private ClientInterface clientInterface;
    private boolean checkSend; //serve a controllare che il numero di giocatori venga inviato una sola volta

    public PlayerNumberSelection(int i, ClientInterface clientInterface) throws SlickException {
        this.clientInterface = clientInterface;
    }

    @Override
    public int getID() {
        return 3;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        background =new Image("res/backgrounds/green_landscape.png");

        one=new NumberButton(new Rectangle(700,300,100,101),new Image("res/buttons/Button_Numbers/Button1_0.png"),new Image("res/buttons/Button_Numbers/Button1_1.png"),null,1);
        two=new NumberButton(new Rectangle(900,300,100,101),new Image("res/buttons/Button_Numbers/Button2_0.png"),new Image("res/buttons/Button_Numbers/Button2_1.png"),null,2);
        three=new NumberButton(new Rectangle(700,420,100,101),new Image("res/buttons/Button_Numbers/Button3_0.png"),new Image("res/buttons/Button_Numbers/Button3_1.png"),null,3);
        four=new NumberButton(new Rectangle(900,420,100,101),new Image("res/buttons/Button_Numbers/Button4_0.png"),new Image("res/buttons/Button_Numbers/Button4_1.png"),null,4);
        mf.init(gameContainer,stateBasedGame);
        mf.setIsNumberToSend(true);
        mf.setBackState(1);
        array=new boolean[5];
            for(int j=0;j<array.length;j++){
                array[j]=false;
            }
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
            mf.setMouseClicked(true);
            numbertosend=one.onClickGetNumber(r.getMouseX(),r.getMouseY(),numbertosend,array);
            numbertosend=two.onClickGetNumber(r.getMouseX(),r.getMouseY(),numbertosend,array);
            numbertosend=three.onClickGetNumber(r.getMouseX(),r.getMouseY(),numbertosend,array);
            numbertosend=four.onClickGetNumber(r.getMouseX(),r.getMouseY(),numbertosend,array);
            mf.setNumbertosend(numbertosend);
            if(!checkSend){
                clientInterface.sendNumberOfPlayers(numbertosend);
                checkSend=true;
            }
            mf.update(gameContainer,stateBasedGame,delta);
        }

        mf.setMouseClicked(false);

        one.onMouseEnterAndClick(one,r.getMouseX(),r.getMouseY(),array);
        two.onMouseEnterAndClick(two,r.getMouseX(),r.getMouseY(),array);
        three.onMouseEnterAndClick(three,r.getMouseX(),r.getMouseY(),array);
        four.onMouseEnterAndClick(four,r.getMouseX(),r.getMouseY(),array);
        mf.update(gameContainer,stateBasedGame,delta);

    }

}


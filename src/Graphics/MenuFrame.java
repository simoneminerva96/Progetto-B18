package Graphics;

import Graphics.com.sticky.StateButton;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MenuFrame extends BasicGameState {

    public String mouse= "No input";
    private Image menuback;
    //BUTTONS
    private StateButton home,back,next,sound;
    private int backState,nextState;
    private int numbertosend=1;
    private float mx,my;
    private boolean mouseClicked=false,isNumberToSend=false;


    public MenuFrame(int i) throws SlickException {
    }

    @Override
    public int getID() {
        return 10;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

        menuback =new Image("res/backgrounds/Windows_02.png");
        home=new StateButton(new Rectangle(740,625,100,101),new Image("res/buttons/Button_MenuFrame/home0.png"),new Image("res/buttons/Button_MenuFrame/home1.png"),new Image("res/buttons/Button_MenuFrame/home0.png"),null);
        back=new StateButton(new Rectangle(640,625,100,101),new Image("res/buttons/Button_MenuFrame/back0.png"),new Image("res/buttons/Button_MenuFrame/back1.png"),new Image("res/buttons/Button_MenuFrame/back0.png"),null);
        next=new StateButton(new Rectangle(940,625,100,101),new Image("res/buttons/Button_MenuFrame/next0.png"),new Image("res/buttons/Button_MenuFrame/next1.png"),new Image("res/buttons/Button_MenuFrame/back0.png"),null);
        sound=new StateButton(new Rectangle(840,625,100,101),new Image("res/buttons/Button_MenuFrame/sound0.png"),new Image("res/buttons/Button_MenuFrame/sound1.png"),new Image("res/buttons/Button_MenuFrame/sound0.png"),null);

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {

        graphics.drawImage(menuback,490,170);
        home.render(gameContainer,graphics);
        back.render(gameContainer,graphics);
        next.render(gameContainer,graphics);
        sound.render(gameContainer,graphics);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        if(mouseClicked){
            if(isNumberToSend)
                next.onClickStateSendNumber(mx,my,gameContainer,stateBasedGame,numbertosend);
            else
                next.onClickState(mx,my,stateBasedGame,nextState);

            back.onClickState(mx,my,stateBasedGame,backState);
            home.onClickState(mx, my, stateBasedGame, 1);
            sound.onClickStateSound(mx, my, gameContainer, gameContainer.isMusicOn());
        }
        home.onMouseEnter(home,mx,my);
        back.onMouseEnter(back,mx,my);
        next.onMouseEnter(next,mx,my);
        sound.onMouseEnter(sound,mx,my);
    }

    public void setNumbertosend(int n){
        numbertosend=n;
    }

    public void setMouseCoordinates(float mx,float my){
        this.mx=mx;
        this.my=my;
    }

    public void setMouseClicked(boolean b){
        mouseClicked=b;
    }

    public void setIsNumberToSend(boolean b){isNumberToSend=b;}

    public void setBackState(int i){backState=i;}
}



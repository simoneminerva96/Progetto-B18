package Client.Graphics;

import Client.Graphics.com.sticky.NumberButton;
import Client.Graphics.com.sticky.StateButton;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class PlayerNumberSelection extends BasicGameState {

    public static int numbertosend=1;
    private Image background;
    private Image menuback;
    private StateButton home,back,next,sound;
    private NumberButton one,two,three,four;
    private boolean[] array;
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
        menuback =new Image("res/backgrounds/Windows_02.png");
        home=new StateButton(new Rectangle(740,625,100,101),new Image("res/buttons/Button_MenuFrame/home0.png"),new Image("res/buttons/Button_MenuFrame/home1.png"),new Image("res/buttons/Button_MenuFrame/home0.png"),null);
        back=new StateButton(new Rectangle(640,625,100,101),new Image("res/buttons/Button_MenuFrame/back0.png"),new Image("res/buttons/Button_MenuFrame/back1.png"),new Image("res/buttons/Button_MenuFrame/back0.png"),null);
        next=new StateButton(new Rectangle(940,625,100,101),new Image("res/buttons/Button_MenuFrame/next0.png"),new Image("res/buttons/Button_MenuFrame/next1.png"),new Image("res/buttons/Button_MenuFrame/back0.png"),null);
        sound=new StateButton(new Rectangle(840,625,100,101),new Image("res/buttons/Button_MenuFrame/sound0.png"),new Image("res/buttons/Button_MenuFrame/sound1.png"),new Image("res/buttons/Button_MenuFrame/sound0.png"),null);

        array=new boolean[5];
            for(int j=0;j<array.length;j++){
                array[j]=false;
            }
        }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawImage(background,0,0);
        graphics.drawImage(menuback,490,170);
        home.render(gameContainer,graphics);
        back.render(gameContainer,graphics);
        next.render(gameContainer,graphics);
        sound.render(gameContainer,graphics);
        one.render(gameContainer,graphics);
        two.render(gameContainer,graphics);
        three.render(gameContainer,graphics);
        four.render(gameContainer,graphics);

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
        Input r=gameContainer.getInput();

        if(r.isMousePressed(0)) {
            numbertosend=one.onClickGetNumber(r.getMouseX(),r.getMouseY(),numbertosend,array);
            numbertosend=two.onClickGetNumber(r.getMouseX(),r.getMouseY(),numbertosend,array);
            numbertosend=three.onClickGetNumber(r.getMouseX(),r.getMouseY(),numbertosend,array);
            numbertosend=four.onClickGetNumber(r.getMouseX(),r.getMouseY(),numbertosend,array);
            if(!checkSend){
                clientInterface.sendindex(numbertosend);
                checkSend=true;
            }
            next.onClickStateSendNumber(r.getMouseX(),r.getMouseY(),stateBasedGame,numbertosend);
            sound.onClickStateSound(r.getMouseX(), r.getMouseY(), gameContainer, gameContainer.isMusicOn());
           // back.onClickState(r.getMouseX(),r.getMouseY(),stateBasedGame,2);
           // home.onClickState(r.getMouseX(), r.getMouseY(), stateBasedGame, 1);

        }

        one.onMouseEnterAndClick(one,r.getMouseX(),r.getMouseY(),array);
        two.onMouseEnterAndClick(two,r.getMouseX(),r.getMouseY(),array);
        three.onMouseEnterAndClick(three,r.getMouseX(),r.getMouseY(),array);
        four.onMouseEnterAndClick(four,r.getMouseX(),r.getMouseY(),array);
        home.onMouseEnter(home,r.getMouseX(),r.getMouseY());
        back.onMouseEnter(back,r.getMouseX(),r.getMouseY());
        next.onMouseEnter(next,r.getMouseX(),r.getMouseY());
        sound.onMouseEnter(sound,r.getMouseX(),r.getMouseY());

    }

}


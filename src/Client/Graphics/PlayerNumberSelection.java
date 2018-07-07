package Client.Graphics;

import Client.Graphics.ClientInteface.ClientInterface;
import Client.Graphics.com.sticky.NumberButton;
import Client.Graphics.com.sticky.StateButton;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * State in cui si sceglie il numero di giocatori per la partita.
 * - background: immagine di sfondo
 * - menuback: sfondo della finestra dei bottoni
 * - home, back, next, sound : bottoni per tornare alla home, indietro, andare avanti e fare stop o play per la musica
 * - one, two, three, four: bottoni per scegliere uno, due, tre o quattro giocatori
 * - array: array di boolean per identificare quale è stato premuto
 * - clientInterface: oggetto clientInterface per comunicare con il server
 * - checkSend: controlla che il numero di giocatori venga inviato una volta sola
 */
public class PlayerNumberSelection extends BasicGameState {
    private static int numbertosend=1;
    private Image background;
    private Image menuback;
    private StateButton home,back,next,sound;
    private NumberButton one,two,three,four;
    private boolean[] array;
    private ClientInterface clientInterface;
    private boolean checkSend;

    PlayerNumberSelection(ClientInterface clientInterface) { this.clientInterface = clientInterface; }

    @Override
    public int getID() { return 3; }

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
            if (next.onClickBoolean(r.getMouseX(), r.getMouseY()) && !checkSend) {
                GameOrderState cr = (GameOrderState) stateBasedGame.getState(4);
                cr.getPlayerNumber(numbertosend);
                clientInterface.sendindex(numbertosend);
                checkSend = true;
                stateBasedGame.enterState(4);
            }
            sound.onClickStateSound(r.getMouseX(), r.getMouseY(), gameContainer, gameContainer.isMusicOn());
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


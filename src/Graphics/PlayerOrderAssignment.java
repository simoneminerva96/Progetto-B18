package Graphics;

import Graphics.Player.Player;
import Graphics.Player.PlayerGUI;
import Graphics.com.sticky.StateButton;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

public class PlayerOrderAssignment extends BasicGameState {

    private Image background;
    private ArrayList<Image>characters;
    private ArrayList<Image>playerBack;
    private int NPLAYERS;
    private StateButton launchorder;
    private StateButton next;


    public PlayerOrderAssignment(int n){
        characters=new ArrayList<>();
        playerBack=new ArrayList<>();
    }
    public int getID() {
        return 4;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        background = new Image("res/backgrounds/green_landscape.png");
        launchorder=new StateButton(new Rectangle(725,700,205,101),new Image("res/buttons/Button_Order/order0.png"),new Image("res/buttons/Button_Order/order1.png"),new Image("res/buttons/Button_Order/order0.png"),null);
        next=new StateButton(new Rectangle(930,700,100,101),new Image("res/buttons/Button_MenuFrame/next0.png"),new Image("res/buttons/Button_MenuFrame/next1.png"),new Image("res/buttons/Button_MenuFrame/back0.png"),null);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
            graphics.drawImage(background,0,0);
            launchorder.render(gameContainer,graphics);
            next.render(gameContainer,graphics);
            int ydisplacement=0;
            for(int i=0;i<NPLAYERS;i++){
                int ytemp=125;
                ydisplacement+=ytemp;
                playerBack.get(i).draw(725,ydisplacement);
                characters.get(i).draw(725,ydisplacement);
                //ydisplacement-=ytemp;

        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input k=gameContainer.getInput();
            launchorder.onMouseEnter(launchorder,k.getMouseX(),k.getMouseY());
            next.onMouseEnter(next,k.getMouseX(),k.getMouseY());
            if(k.isMousePressed(0)){
                next.onClickState(k.getMouseX(),k.getMouseY(),stateBasedGame,5);
            }
    }

    public void setPlayerNumber(int n){
        NPLAYERS=n;
        for (int i = 0; i <NPLAYERS; i++) {
            try {
                playerBack.add(new Image("res/backgrounds/PlayerBackgrounds/SfondoGiocatore"+i+".png"));
                characters.add(new Image("res/char/charback"+i+".png"));
            } catch (SlickException e) {
                e.printStackTrace();
            }

        }
    }
}

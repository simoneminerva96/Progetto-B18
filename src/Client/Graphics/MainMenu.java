package Client.Graphics;

import Client.Graphics.Fonts.TriviaFont;
import Client.Graphics.com.sticky.StateButton;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenu extends BasicGameState {

    private Image background;
    private TrueTypeFont fonx1;
    private StateButton play;
    private TriviaFont f;

    public MainMenu()  {
        f = new TriviaFont();
    }

    @Override
    public int getID() {
        return 2;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
            background =new Image("res/backgrounds/green_landscape.png");
            fonx1 = new TrueTypeFont(f.getFont().deriveFont(23f),false);
            play=new StateButton(new Rectangle(750,400,190,49),new Image("res/buttons/Button_Play/Button_Play_01.png"),new Image("res/buttons/Button_Play/Button_Play_02.png"),new Image("res/buttons/Button_Play/Button_Play_01.png"),null);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics)  {
            graphics.drawImage(background,0,0);
            fonx1.drawString(700,150,"TRIVIAL PURSUIT RELOADED", Color.white);
            play.render(gameContainer,graphics);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input r=gameContainer.getInput();

        if(r.isMousePressed(0)) {
            play.onClickState(r.getMouseX(),r.getMouseY(),stateBasedGame,3);
        }
        play.onMouseEnter(play,r.getMouseX(),r.getMouseY());

    }
}

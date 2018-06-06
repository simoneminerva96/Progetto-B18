package Graphics;

import Graphics.com.sticky.StateButton;
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
    private StateButton play, options;
    private TriviaFont f;

    public MainMenu(int i) throws SlickException {
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
            play=new StateButton(new Rectangle(550,325,190,49),new Image("res/buttons/Button_Play/Button_Play_01.png"),new Image("res/buttons/Button_Play/Button_Play_02.png"),new Image("res/buttons/Button_Play/Button_Play_01.png"),null);
            options=new StateButton(new Rectangle(550,450,190,49),new Image("res/buttons/Button_Options/Button_Options_01.png"),new Image("res/buttons/Button_Options/Button_Options_02.png"),new Image("res/buttons/Button_Options/Button_Options_01.png"),null);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
            graphics.drawImage(background,0,0);
            fonx1.drawString(400,25,"TRIVIAL PURSUIT RELOADED", Color.white);

            play.render(gameContainer,graphics);
            options.render(gameContainer,graphics);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input r=gameContainer.getInput();

        if(r.isMousePressed(0)) {
            play.onClickState(r.getMouseX(),r.getMouseY(),stateBasedGame,5);
            options.onClickState(r.getMouseX(),r.getMouseY(),stateBasedGame,4);
        }

        play.onMouseEnter(play,r.getMouseX(),r.getMouseY());
        options.onMouseEnter(options,r.getMouseX(),r.getMouseY());


    }

    public void enter(GameContainer gameContainer,StateBasedGame stateBasedGame)throws SlickException{
    }
}

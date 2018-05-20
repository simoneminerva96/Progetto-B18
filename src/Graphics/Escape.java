package Graphics;

import Graphics.com.sticky.FormButton;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Escape extends BasicGameState {
    private FormButton exit, resume, menu;
    private boolean quit = false;

    public Escape (int n) {

    }
    @Override
    public int getID() {
        return 7;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        exit = new FormButton(new Rectangle(560,400, 185,91), new Image("res/buttons/Button_Exit/Button_Exit_01.png"), new Image("res/buttons/Button_Exit/Button_Exit_02.png"), new Image("res/buttons/Button_Exit/Button_Exit_01.png"), null);
        resume = new FormButton(new Rectangle(560,200, 185,91), new Image("res/buttons/Button_Resume/Button_Resume_01.png"), new Image("res/buttons/Button_Resume/Button_Resume_02.png"), new Image("res/buttons/Button_Resume/Button_Resume_01.png"), null);
        menu = new FormButton(new Rectangle(560,300, 185,91), new Image("res/buttons/Button_Menu/Button_Menu_01.png"), new Image("res/buttons/Button_Menu/Button_Menu_02.png"), new Image("res/buttons/Button_Menu/Button_Menu_01.png"), null);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        exit.render(gameContainer,graphics);
        resume.render(gameContainer, graphics);
        menu.render(gameContainer, graphics);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input in = gameContainer.getInput();

        if (in.isMousePressed(0)){
            exit.onCLickFormExit(in.getMouseX(), in.getMouseY());
            menu.onClickFormMenu(in.getMouseX(), in.getMouseY(), stateBasedGame, 2);
            resume.onClickFormResume(in.getMouseX(), in.getMouseY(), this);
        }
        exit.onMouseEnter(exit, in.getMouseX(), in.getMouseY());
        resume.onMouseEnter(resume, in.getMouseX(), in.getMouseY());
        menu.onMouseEnter(menu, in.getMouseX(), in.getMouseY());
    }

    public boolean isQuit() {
        return quit;
    }

    public void setQuit(boolean quit) {
        this.quit = quit;
    }

    public FormButton getResume() {
        return resume;
    }
}

package Client.Graphics;

import Client.Graphics.com.sticky.FormButton;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Rita
 *
 * La classe Escape rappresenta lo state che andrà renderizzato quando sullo state del tabellone
 * {@see Trivia} premo Esc.
 *
 * - exit: bottone che permette di uscire
 * - resume: bottone che permette di riprendere a giocare
 * - menu: bottone che permette di tornare al menu principale
 */
public class Escape extends BasicGameState {
    private FormButton exit, resume, menu;
    private boolean quit = false;

    public Escape () { }

    @Override
    public int getID() { return 7; }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        exit = new FormButton(new Rectangle(820,500, 185,91), new Image("res/buttons/Button_Exit/Button_Exit_01.png"), new Image("res/buttons/Button_Exit/Button_Exit_02.png"), new Image("res/buttons/Button_Exit/Button_Exit_01.png"), null);
        resume = new FormButton(new Rectangle(820,400, 185,91), new Image("res/buttons/Button_Resume/Button_Resume_01.png"), new Image("res/buttons/Button_Resume/Button_Resume_02.png"), new Image("res/buttons/Button_Resume/Button_Resume_01.png"), null);
        menu = new FormButton(new Rectangle(820,600, 185,91), new Image("res/buttons/Button_Menu/Button_Menu_01.png"), new Image("res/buttons/Button_Menu/Button_Menu_02.png"), new Image("res/buttons/Button_Menu/Button_Menu_01.png"), null);
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

}

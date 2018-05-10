package Graphics;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Main extends StateBasedGame{
    public static final String gameName = "Trivial Pursuit";
    public static final int trivia = 2;
    public static final int login = 1;


    public Main(String gameName) throws Exception {
        super(gameName);
        this.addState(new Trivia(trivia));
        this.addState(new LoginScreen(login));
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.getState(trivia).init(gc,this);
        this.getState(login).init(gc,this);
        this.enterState(trivia);
    }

    public static void main(String[] args) throws Exception {
        AppGameContainer appgc; // the window for the game
        try {
            //a windows that contains a game named gameName
            appgc = new AppGameContainer(new Main(gameName));
            appgc.setAlwaysRender(true);
            appgc.setDisplayMode(1300, 700, false);
            appgc.setShowFPS(false);
            appgc.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}

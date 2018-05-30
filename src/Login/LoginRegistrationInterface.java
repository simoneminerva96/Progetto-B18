package Login;

import Graphics.TextFieldTest;
import Graphics.com.sticky.FormButton;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import Graphics.*;

/**
 * @author Rita, Stefano
 *
 *La classe LoginRegistrationInterface rappresenta l'interfaccia in cui avviene sia login che registrazione.
 * - usrname: textfield in cui inserire usrname
 * - psw: textfield in cui inserisco la password
 * - f, fonx1: font utilizzato
 * - regButton: bottone per la registrazione
 * - logButton: bottone per il login
 * - reg: oggetto di tipo Registration
 * - login: oggetto di tipo Login
 * - check: flag che indica se la registrazione è andata a buon fine oppure no
 */

public class LoginRegistrationInterface extends BasicGameState {

    private TextField usrname;
    private TextFieldTest psw;
    private TrueTypeFont fonx1;
    private Image background, registrationback;
    private FormButton regButton, logButton;
    private Registration reg;
    private Login login;
    private boolean check;
    private TriviaFont f;

    //MenuFrame mf=new MenuFrame(10);

    public LoginRegistrationInterface(int n) throws SlickException {
        this.reg = new Registration();
        this.login = new Login();
        f = new TriviaFont();
    }

    @Override
    public int getID() { return 1; }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        background =new Image("res/backgrounds/green_landscape_ridim.png");
        registrationback =new Image("res/backgrounds/Windows_09p.png");
        regButton = new FormButton(new Rectangle(448,502,205,101),new Image("res/buttons/Button_Login/signup0.png"),new Image("res/buttons/Button_Login/signup1.png"),new Image("res/buttons/Button_Login/signup0.png"),null);
        logButton = new FormButton(new Rectangle(648,502,205,101),new Image("res/buttons/Button_Login/signin0.png"),new Image("res/buttons/Button_Login/signin1.png"),new Image("res/buttons/Button_Login/signin0.png"),null);
        fonx1 = new TrueTypeFont(f.getFont().deriveFont(23f),false);
        usrname =new TextField(gameContainer , fonx1 , 550 , 245 , 200 , 35);
        psw = new TextFieldTest(gameContainer , fonx1 , 550 , 340 , 200 , 35);
        psw.setBackgroundColor(org.newdawn.slick.Color.lightGray);
        psw.setMaskEnabled(true);
        //mf.init(gameContainer,stateBasedGame);
        //mf.setBackState(2);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawImage(background,0,0);
        graphics.drawImage(registrationback,421,135);
        //mf.render(gameContainer,stateBasedGame,graphics);
        usrname.render(gameContainer,graphics);
        psw.render(gameContainer,graphics);
        regButton.render(gameContainer,graphics);
        logButton.render(gameContainer,graphics);
        fonx1.drawString(520,205,"INSERISCI NICKNAME", org.newdawn.slick.Color.white);
        fonx1.drawString(520,300,"INSERISCI PASSWORD", org.newdawn.slick.Color.white);

        if (regButton.isClicked()) {
            if (check) {
                fonx1.drawString(490,20, "Registrazione effettuata con successo", org.newdawn.slick.Color.blue);
            }
            else fonx1.drawString(490,20, "Registrazione fallita", org.newdawn.slick.Color.blue);
            }
    }

    /*
    OnClickFormRegistration preleva ciò che viene inserito e lo salva nel db attraverso il metodo di
    Registration.
    OnClickFormLogin preleva ciò che viene inserito ed effettua il login attraverso il metodo di Login.
     */
    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input in = gameContainer.getInput();

        //mf.setMouseCoordinates(in.getMouseX(),in.getMouseY());
        
        if (in.isMousePressed(0)){
            //mf.setMouseClicked(true);
            regButton.setClicked(false);
            check = regButton.onClickFormRegistration(in.getMouseX(),in.getMouseY(),usrname.getText(),psw.getText(), reg);
            logButton.onClickFormLogin(in.getMouseX(),in.getMouseY(),usrname.getText(),psw.getText(), login, stateBasedGame, 2);
            //mf.update(gameContainer,stateBasedGame,i);
        }
        regButton.onMouseEnter(regButton,in.getMouseX(),in.getMouseY());
        logButton.onMouseEnter(logButton, in.getMouseX(), in.getMouseY());

        //mf.setMouseClicked(false);
        //mf.update(gameContainer,stateBasedGame,i);
    }

    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {

    }

}

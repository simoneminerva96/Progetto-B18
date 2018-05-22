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
import Graphics.TriviaFont;

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

    public LoginRegistrationInterface(int n){
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
        regButton = new FormButton(new Rectangle(375,460,185,91),new Image("res/buttons/Button_SignUp/Button_SignUp_01.png"),new Image("res/buttons/Button_SignUp/Button_SignUp_02.png"),new Image("res/buttons/Button_SignUp/Button_SignUp_01.png"),null);
        logButton = new FormButton(new Rectangle(675,460,185,91),new Image("res/buttons/Button_SignIn/Button_SignIn_01.png"),new Image("res/buttons/Button_SignIn/Button_SignIn_02.png"),new Image("res/buttons/Button_SignIn/Button_SignIn_01.png"),null);
        fonx1 = new TrueTypeFont(f.getFont().deriveFont(23f),false);
        usrname =new TextField(gameContainer , fonx1 , 540 , 245 , 200 , 35);
        psw = new TextFieldTest(gameContainer , fonx1 , 540 , 340 , 200 , 35);
        psw.setBackgroundColor(org.newdawn.slick.Color.lightGray);
        psw.setMaskEnabled(true);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawImage(background,0,0);
        graphics.drawImage(registrationback,421,135);
        usrname.render(gameContainer,graphics);
        psw.render(gameContainer,graphics);
        regButton.render(gameContainer,graphics);
        logButton.render(gameContainer,graphics);
        fonx1.drawString(510,205,"INSERISCI NICKNAME", org.newdawn.slick.Color.white);
        fonx1.drawString(510,300,"INSERISCI PASSWORD", org.newdawn.slick.Color.white);

        if (regButton.isClicked()) {
            if (check) {
                fonx1.drawString(490,100, "Registrazione effettuata con successo", org.newdawn.slick.Color.blue);
            }
            else
                fonx1.drawString(490,100, "Registrazione fallita", org.newdawn.slick.Color.blue);
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
        
        if (in.isMousePressed(0)){
            check = regButton.onClickFormRegistration(in.getMouseX(),in.getMouseY(),usrname.getText(),psw.getText(), reg);
            logButton.onClickFormLogin(in.getMouseX(),in.getMouseY(),usrname.getText(),psw.getText(), login, stateBasedGame, 2);
        }
        regButton.onMouseEnter(regButton,in.getMouseX(),in.getMouseY());
        logButton.onMouseEnter(logButton, in.getMouseX(), in.getMouseY());
    }

    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {

    }

}

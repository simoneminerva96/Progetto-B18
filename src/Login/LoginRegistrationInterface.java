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
 * - checkR: flag che indica se la registrazione è andata a buon fine oppure no
 * - checkL: flag che indica se la registrazione è andata a buon fine oppure no
 * - music: oggetto di tipo Music per la musica di gioco
 */

public class LoginRegistrationInterface extends BasicGameState {

    private TextField usrname;
    private TextFieldTest psw;
    private TrueTypeFont fonx1;
    private Image background, registrationback;
    private FormButton regButton, logButton;
    private Registration reg;
    private Login login;
    private boolean checkR, checkL;
    private TriviaFont f;
    private Music music;

    public LoginRegistrationInterface(int n) {
        this.reg = new Registration();
        this.login = new Login();
        f = new TriviaFont();
    }

    @Override
    public int getID() { return 1; }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        background =new Image("res/backgrounds/green_landscape.png");
        registrationback =new Image("res/backgrounds/Windows_02.png");
        regButton = new FormButton(new Rectangle(650,630,205,101),new Image("res/buttons/Button_Login/Button_SignUp_01.png"),new Image("res/buttons/Button_Login/Button_SignUp_02.png"),new Image("res/buttons/Button_Login/Button_SignUp_01.png"),null);
        logButton = new FormButton(new Rectangle(850,630,205,101),new Image("res/buttons/Button_Login/Button_Login_01.png"),new Image("res/buttons/Button_Login/Button_Login_02.png"),new Image("res/buttons/Button_Login/Button_Login_01.png"),null);
        fonx1 = new TrueTypeFont(f.getFont().deriveFont(23f),false);
        usrname =new TextField(gameContainer , fonx1 , 700 , 380 , 250 , 40);
        psw = new TextFieldTest(gameContainer , fonx1 , 700 , 480 , 250 , 40);
        psw.setBackgroundColor(org.newdawn.slick.Color.lightGray);
        psw.setMaskEnabled(true);
//        music = new Music("res/music/Wallpaper.wav");
//        music.play();
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        background.draw(0,0);
        registrationback.draw(490,170);
        usrname.render(gameContainer,graphics);
        psw.render(gameContainer,graphics);
        regButton.render(gameContainer,graphics);
        logButton.render(gameContainer,graphics);
        fonx1.drawString(700,350,"INSERISCI NICKNAME", org.newdawn.slick.Color.white);
        fonx1.drawString(700,450,"INSERISCI PASSWORD", org.newdawn.slick.Color.white);

        if (regButton.isClicked()) {
            if (checkR) {
                fonx1.drawString(490,20, "Registrazione effettuata con successo", org.newdawn.slick.Color.blue);
            }
            else fonx1.drawString(490,20, "Registrazione fallita", org.newdawn.slick.Color.blue);
        }

        if(logButton.isClicked()){
            if (!checkL) {
                fonx1.drawString(490,20, "Username o password errati", org.newdawn.slick.Color.blue);
            }
            else {
                stateBasedGame.enterState(3);
            }
        }
    }

    /*
    OnClickFormRegistration preleva ciò che viene inserito e lo salva nel db attraverso il metodo di Registration.
    OnClickFormLogin preleva ciò che viene inserito ed effettua il login attraverso il metodo di Login.
     */
    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) {
        Input in = gameContainer.getInput();

        if (in.isMousePressed(0)){
            regButton.setClicked(false);
            logButton.setClicked(false);
            checkR = regButton.onClickFormRegistration(in.getMouseX(),in.getMouseY(),usrname.getText(),psw.getText(), reg);
            checkL = logButton.onClickFormLogin(in.getMouseX(),in.getMouseY(),usrname.getText(),psw.getText(), login);
        }
        regButton.onMouseEnter(regButton,in.getMouseX(),in.getMouseY());
        logButton.onMouseEnter(logButton, in.getMouseX(), in.getMouseY());
    }

}

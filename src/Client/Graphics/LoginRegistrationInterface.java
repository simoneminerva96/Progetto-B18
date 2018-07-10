package Client.Graphics;

import Client.Graphics.ClientInteface.ClientInterface;
import Client.Graphics.Fonts.TextFieldTest;
import Client.Graphics.Fonts.TriviaFont;
import Client.Graphics.com.sticky.FormButton;
import Server.GameClasses.GameClasses.TypeOfRequest;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Rita, Stefano
 *
 *La classe LoginRegistrationInterface rappresenta l'interfaccia in cui avviene sia login che registrazione.
 * - usrname, psw: textfield in cui inserire usrname
 * - f, fonx1: font utilizzato
 * - regButton: bottone per la registrazione
 * - logButton: bottone per il login
 * - checkR: flag che indica se la registrazione è andata a buon fine oppure no
 * - checkL: flag che indica se la registrazione è andata a buon fine oppure no
 * - music: oggetto di tipo Music per la musica di gioco
 * - clientInterface: oggetto clientInterface per comunicare con il server
 */

public class LoginRegistrationInterface extends BasicGameState {
    private TextField usrname;
    private TextFieldTest psw;
    private TrueTypeFont fonx1;
    private Image background, registrationback;
    private FormButton regButton, logButton;
    private boolean checkR, checkL;
    private TriviaFont f;
    private Music music;
    private ClientInterface clientInterface;

    LoginRegistrationInterface(ClientInterface clientInterface) {
        f = new TriviaFont();
        this.clientInterface = clientInterface;
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
        music = new Music("res/music/Kevin MacLeod _ Enter the Party.wav");
        music.play();
        music.loop();
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
            if (checkR)
                fonx1.drawString(590,100, "Registrazione effettuata con successo", org.newdawn.slick.Color.blue);
            else fonx1.drawString(590,100, "Registrazione fallita", org.newdawn.slick.Color.blue);
        }

        if(logButton.isClicked()){
            if (!checkL)
                fonx1.drawString(590,20, "Username o password errati", org.newdawn.slick.Color.blue);
            else
                stateBasedGame.enterState(2);
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) {
        Input in = gameContainer.getInput();

        if (in.isMousePressed(0)){
            regButton.setClicked(false);
            logButton.setClicked(false);
            if(regButton.onClickForm(in.getMouseX(),in.getMouseY())){
                clientInterface.sendCredential(usrname.getText(),psw.getText(), TypeOfRequest.REGISTRAZIONE);
                checkR = clientInterface.receiveOutcome();
            }
            if(logButton.onClickForm(in.getMouseX(),in.getMouseY())){
                clientInterface.sendCredential(usrname.getText(),psw.getText(), TypeOfRequest.LOGIN);
                checkL = clientInterface.receiveOutcome();
            }
        }
        regButton.onMouseEnter(regButton,in.getMouseX(),in.getMouseY());
        logButton.onMouseEnter(logButton, in.getMouseX(), in.getMouseY());

    }

}

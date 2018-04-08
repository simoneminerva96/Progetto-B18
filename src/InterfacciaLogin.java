import javafx.scene.control.PasswordField;

import javax.swing.*;
import javax.swing.text.PasswordView;
import java.awt.*;

/*
    INTERFACCIA GRAFICA DI LOGIN USANDO JAVAX.SWING
    BOZZA TEMPORANEA
    (DA FINIRE CON GLI ACTION LISTENER IN CASO)
 */
import static java.awt.BorderLayout.*;

public class InterfacciaLogin extends JFrame{
    public static final int LUNGHEZZA=400;    //lunghezza finestra
    public static final int ALTEZZA=500;      //altezza finestra
    public static final Color color=Color.white;        //colore sfondo finestra
    public JPanel superiorPAnel,centralPanel,inferiorPanel;     //3 pannelli di cui si compone la finestra

    public InterfacciaLogin() {
        setSize(LUNGHEZZA, ALTEZZA);
        setBackground(color);
        setLayout(new BorderLayout());
        setTitle("WELCOME TO TRIVIAL PURSUIT");    //titolo in alto
        Container c=getContentPane();
        superiorPAnel=new SuperiorPanel();
        superiorPAnel.setBackground(color);
        centralPanel=new CentralPanel();
        centralPanel.setBackground(color);
        inferiorPanel=new InferioriorPanel();
        inferiorPanel.setBackground(color);
        c.add(superiorPAnel, NORTH);
        c.add(centralPanel, CENTER);
        c.add(inferiorPanel,BorderLayout.SOUTH);
    }

    class SuperiorPanel extends JPanel{      //pannello superiore
        private JLabel login;               //scritta "LOGIN"
        public SuperiorPanel(){
            login=new JLabel("LOGIN");
            add(login);
        }
    }
    class CentralPanel extends JPanel{      //pannello centrale con inserimenti nickname e password
        private JPanel username,pass;
        private TextField insertNickname;
        private JPasswordField insertPassword;
        private JLabel nickname,password;
        private JButton loginButton;

        public CentralPanel(){
            setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
            username=new JPanel();
            username.setBackground(color);
            username.setLayout(new BorderLayout());
            nickname=new JLabel("your nickname");
            insertNickname=new TextField();
            username.add(nickname, NORTH);
            username.add(insertNickname, CENTER);
            pass=new JPanel();
            pass.setLayout(new BorderLayout());
            pass.setBackground(color);
            password=new JLabel("your password");
            insertPassword=new JPasswordField();
            pass.add(password, NORTH);
            pass.add(insertPassword, CENTER);
            loginButton=new JButton("LOGIN");
            loginButton.setBackground(Color.LIGHT_GRAY);
            add(username, NORTH);
            add(pass, CENTER);
            add(loginButton, SOUTH);
        }
    }

    class InferioriorPanel extends JPanel{      //pannello inferiore con link a interfaccia registrazione
        private JLabel goToRegistration;
        private JButton registration;

        public InferioriorPanel(){
            setLayout(new BorderLayout());
            goToRegistration=new JLabel("not registered yet?");
            registration=new JButton("REGISTER");
            registration.setBackground(Color.LIGHT_GRAY);
            add(goToRegistration,BorderLayout.WEST);
            add(registration,BorderLayout.EAST);
        }
    }
}

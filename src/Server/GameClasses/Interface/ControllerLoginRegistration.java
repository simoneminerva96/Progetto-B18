package Server.GameClasses.Interface;

import Server.GameClasses.Credenziali;
import Server.GameClasses.Login;
import Server.GameClasses.Registration;
import Server.GameClasses.ServerInterface;

public class ControllerLoginRegistration {
    private Registration reg;
    private Login login;

    public ControllerLoginRegistration(){
        reg = new Registration();
        login = new Login();
    }

    public boolean login(Credenziali credenziali){
        return login.Login(credenziali);
    }

    public boolean registration(Credenziali credenziali){
        return reg.Registration(credenziali);
    }
}

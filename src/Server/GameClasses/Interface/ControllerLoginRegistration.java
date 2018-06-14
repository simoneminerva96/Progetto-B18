package Server.GameClasses.Interface;

import Server.GameClasses.Login;
import Server.GameClasses.Registration;
import Server.GameClasses.ServerInterface;

public class ControllerLoginRegistration {
    private Registration reg;
    private Login login;
    private ServerInterface serverInterface;

    public ControllerLoginRegistration(){
        reg = new Registration();
        login = new Login();
        //serverInterface = new ServerInterface();
    }

    public boolean login(String username, String psw){
        return login.Login(username, psw);
    }

    public boolean registration(String username, String psw){
        return reg.Registration(username,psw);
    }
}

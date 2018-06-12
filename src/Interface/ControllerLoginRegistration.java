package Interface;

import Login.*;

public class ControllerLoginRegistration {
    private Registration reg;
    private Login login;

    public ControllerLoginRegistration(){
        reg = new Registration();
        login = new Login();
    }

    public boolean login(String username, String psw){
        return login.Login(username, psw);
    }

    public boolean registration(String username, String psw){
        return reg.Registration(username,psw);
    }
}

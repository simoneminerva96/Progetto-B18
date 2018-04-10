/*
    controller class
 */

public class Controller {
    private Login_registrazione login_registrazione;

    public void initializeLogin(){
        login_registrazione=new Login_registrazione();
    }

    public static void main(String[] args) {
        Controller main= new Controller();
        main.initializeLogin();
    }
}

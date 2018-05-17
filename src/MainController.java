/*
    controller class
 */

import GameClasses.TrivialGame;
import Graphics.MainGrapichs;
import Login.LoginInterface;
import Login.RegistrationInterface;

public class MainController {

    public static void main(String[] args) throws Exception {
        MainGrapichs mainGrapichs=new MainGrapichs("trivial");
        mainGrapichs.Run();

    }
}

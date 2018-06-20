package Server.GameClasses.Interface;

import Server.GameClasses.*;

public class ControllerLoginRegistration {
    private Request request;

    public ControllerLoginRegistration(){
        request = new Request();
    }

    public boolean request (Credenziali credenziali, TypeOfRequest typeOfRequest) {
        return request.request(credenziali, typeOfRequest);
    }
}

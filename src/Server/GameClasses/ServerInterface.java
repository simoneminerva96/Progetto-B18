package Server.GameClasses;

import Server.GameClasses.Interface.ControllerLoginRegistration;
import java.io.*;
import java.net.Socket;

public class ServerInterface extends Thread implements Serializable {
    private ControllerLoginRegistration controllerLoginRegistration;
    private Socket socketClient ;
    private ObjectInputStream in = null;
    private ObjectOutputStream out = null;
    private TypeOfRequest typeOfRequest;
    private boolean check;

    public ServerInterface(Socket socketClient){
        this.socketClient = socketClient;
        controllerLoginRegistration = new ControllerLoginRegistration();
    }

    @Override
    public void run() {
        try {
            in = new ObjectInputStream(socketClient.getInputStream());
            out = new ObjectOutputStream(socketClient.getOutputStream());
            while(true) {
                Credenziali credenziali = (Credenziali) in.readObject();
                System.out.println("cred:" + credenziali);
                typeOfRequest = (TypeOfRequest) in.readObject();
                System.out.println("type" + typeOfRequest);
                switch (typeOfRequest) {
                    case REGISTRAZIONE:
                        check = request(credenziali, TypeOfRequest.REGISTRAZIONE);
                        break;
                    case LOGIN:
                        check = request(credenziali, TypeOfRequest.LOGIN);
                        break;
                }
                System.out.println("check" + check);
                out.writeObject(check);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean request (Credenziali credenziali, TypeOfRequest typeOfRequest) {
        return controllerLoginRegistration.request(credenziali, typeOfRequest);
    }
}

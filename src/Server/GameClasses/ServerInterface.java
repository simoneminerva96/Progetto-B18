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
            Credenziali credenziali = (Credenziali) in.readObject();
            typeOfRequest = (TypeOfRequest) in.readObject();
            switch (typeOfRequest){
                case REGISTRAZIONE:
                    check = registration(credenziali);
                    break;
                case LOGIN:
                    check = login(credenziali);
                    break;
            }
            out.writeObject(check);

            /*System.out.println("aspetto un messaggio...");
            DieGUI die = (DieGUI) in.readObject();
            System.out.println("Object received: " +die);
            out.writeObject(die);
            socketClient.close(); //termino la connesione al client*/
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean login(Credenziali credenziali){
        return controllerLoginRegistration.login(credenziali);
    }

    public boolean registration(Credenziali credenziali){ return controllerLoginRegistration.registration(credenziali); }
}

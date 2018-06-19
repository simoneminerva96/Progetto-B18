package Server.GameClasses;

import Server.GameClasses.Interface.ControllerLoginRegistration;
import java.io.*;
import java.net.Socket;

public class ServerInterface extends Thread implements Serializable {
    private ControllerLoginRegistration controllerLoginRegistration;
    private Socket socketClient ;
    private ObjectInputStream in = null;
    private ObjectOutputStream out = null;
    private boolean check = false;

    public ServerInterface(Socket socketClient){
        this.socketClient = socketClient;
        controllerLoginRegistration = new ControllerLoginRegistration();
    }

    @Override
    public void run() {
        try {
            in = new ObjectInputStream(socketClient.getInputStream());
            out = new ObjectOutputStream(socketClient.getOutputStream());

            String usr = (String) in.readObject();
            String psw = (String) in.readObject();
            check = registration(usr,psw);
            out.writeBoolean(check);
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

    public boolean login(String username, String psw){
        return controllerLoginRegistration.login(username,psw);
    }

    public boolean registration(String username, String psw){
        return controllerLoginRegistration.registration(username, psw);
    }
}

package Server.GameClasses;

//(import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.net.Socket;

public class ServerInterface extends Thread implements Serializable {
    private Controller controller;
    private Socket socketClient ;
    private ObjectInputStream in ;
    private ObjectOutputStream out;
    private TypeOfRequest typeOfRequest;
    private int numberOfPlayers;
    private boolean logged = false;

    public ServerInterface(Socket socketClient){
        this.socketClient = socketClient;
        controller = new Controller();
        try{
            in = new ObjectInputStream(socketClient.getInputStream());
            out = new ObjectOutputStream(socketClient.getOutputStream());
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
            while (true) {
                boolean isLogin = false;
                boolean check = false;
                Credenziali credenziali = null;
                try {
                    /*credenziali = (Credenziali) in.readObject();
                    typeOfRequest = (TypeOfRequest) in.readObject();
                    switch (typeOfRequest) {
                        case REGISTRAZIONE:
                            check = request(credenziali, TypeOfRequest.REGISTRAZIONE);
                            break;
                        case LOGIN:
                            check = request(credenziali, TypeOfRequest.LOGIN);
                            isLogin = check;
                            break;
                    }
                    out.writeObject(check);*/
                    numberOfPlayers = (int) in.readObject();
                    System.out.println("NUMERI GIOCATORI: " + numberOfPlayers);
                    controller.initializePlayers(numberOfPlayers);
                }
                catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
    }

    public boolean request (Credenziali credenziali, TypeOfRequest typeOfRequest) {
        return controller.request(credenziali, typeOfRequest);
    }

    public void sendNicknames(){
        try{
            out.writeObject(controller.getOrdinatedNicknames());
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}

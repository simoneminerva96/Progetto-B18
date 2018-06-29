package Server.GameClasses;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.net.Socket;

public class ServerInterface extends Thread implements Serializable {
    private Controller controller;
    private Socket socketClient ;
    private ObjectInputStream in ;
    private ObjectOutputStream out;
    private TypeOfRequest typeOfRequest;
    private boolean check;

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
        try {
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

package Server.GameClasses;

import Client.Graphics.DieGUI;

import java.io.*;
import java.net.Socket;

public class ServerInterface extends Thread implements Serializable {
    private Socket socketClient ;
    private ObjectInputStream in1 = null;
    private ObjectOutputStream out = null;

    public ServerInterface(Socket socketClient){
        this.socketClient = socketClient;
    }

    @Override
    public void run() {
        try {
            in1 = new ObjectInputStream(socketClient.getInputStream());
            out = new ObjectOutputStream(socketClient.getOutputStream());
            System.out.println("aspetto un messaggio...");
            DieGUI die = (DieGUI) in1.readObject();
            System.out.println("Object received: " +die);
            out.writeObject(die);
            socketClient.close(); //termino la connesione al client
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

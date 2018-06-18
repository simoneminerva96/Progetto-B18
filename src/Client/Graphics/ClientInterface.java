package Client.Graphics;

import java.io.Serializable;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientInterface implements Serializable {
    private ObjectOutputStream out1 = null;

    public ClientInterface(){ }

    public void comunica(){
        try {
            Socket server = new Socket(InetAddress.getLocalHost(),8888);
            System.out.println("Connessione al server effettuata");
            out1 = new ObjectOutputStream(server.getOutputStream());
            DieGUI prova = new DieGUI();
            System.out.println("Object to be written: "+ prova);
            out1.writeObject(prova);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package Server.GameClasses;

import java.io.*;
import java.net.Socket;

public class ServerInterface extends Thread implements Serializable {
    //private DataInputStream in; //per ricevere da client
    //private DataOutputStream out; //per inviare al client
    private Socket socketClient ;
    private String letto;
    private ObjectOutputStream out1 = null;
    private ObjectInputStream in1 = null;

    public ServerInterface(Socket socketClient){
        this.socketClient = socketClient;
    }

    @Override
    public void run() {
        try {
            //in = new DataInputStream(socketClient.getInputStream()); //input da client
            //out = new DataOutputStream(socketClient.getOutputStream()); //output a client
            in1 = new ObjectInputStream(socketClient.getInputStream());
            out1 = new ObjectOutputStream(socketClient.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            do {
                System.out.println("aspetto un messaggio...");
                //letto = in.readLine();
                letto = (String) in1.readObject();

                System.out.println("messaggio ricevuto:" + " " + letto);

                String risposta = "Ok"; //riposta del server
                //out.writeBytes(risposta + "\n");
                out1.writeObject(risposta + "\n");

            } while (!letto.toLowerCase().equals("esci"));
            //out.writeBytes("Connessione chiusa" + "\n");
            socketClient.close(); //termino la connesione al client

        } catch (IOException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

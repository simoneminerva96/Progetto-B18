package Server.GameClasses;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerInterface implements Runnable {
    private DataInputStream in; //per ricevere da client
    private DataOutputStream out; //per inviare al client
    private Socket socketClient ;
    private String letto;

    public ServerInterface(Socket socketClient){
        this.socketClient = socketClient;
    }

    @Override
    public void run() {
        try {
            in = new DataInputStream(socketClient.getInputStream()); //input da client
            out = new DataOutputStream(socketClient.getOutputStream()); //output a client
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            do {
                System.out.println("aspetto un messaggio...");
                letto = in.readLine();

                System.out.println("messaggio ricevuto:" + " " + letto);

                String risposta = "Ok"; //riposta del server
                out.writeBytes(risposta + "\n");

            } while (!letto.toLowerCase().equals("esci"));
            out.writeBytes("Connessione chiusa" + "\n");
            socketClient.close(); //termino la connesione al client

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

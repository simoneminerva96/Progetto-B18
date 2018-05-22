package ClientServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    Socket server = null;

    int porta = 8888; //porta server

    DataInputStream in; //per ricevere da server
    DataOutputStream out; //per inviare al server

    public Socket connetti(){

        try {

            System.out.println("Provo a connettermi.....");
            //prova lo connessione con le credenziali date
            Socket server = new Socket(InetAddress.getLocalHost(),porta);

            System.out.println("Connessione al server effettuata");

            in = new DataInputStream(server.getInputStream());
            out = new DataOutputStream(server.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return server;

    }

    public static void main(String[] args) throws IOException {

        Client C = new Client();
        C.connetti();

    }
}

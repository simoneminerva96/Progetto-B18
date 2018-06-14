package Client.Graphics;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ClientInterface {

    private DataInputStream in; //per ricevere da server
    private DataOutputStream out; //per inviare al server
    private BufferedReader tastiera;
    private String messaggio;

    public ClientInterface(){
        try {
            System.out.println("Provo a connettermi.....");
            //prova lo connessione con le credenziali date
            Socket server = new Socket(InetAddress.getLocalHost(),8888);

            System.out.println("Connessione al server effettuata");

            in = new DataInputStream(server.getInputStream());
            out = new DataOutputStream(server.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void comunica(){
        try {
            do {
                System.out.print("Messaggio da inviare al server: ");
                tastiera = new BufferedReader(new InputStreamReader(System.in));
                messaggio = tastiera.readLine();

                out.writeBytes(messaggio + "\n");

            } while (!messaggio.toLowerCase().equals("esci"));
            System.out.println("Connessione chiusa");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

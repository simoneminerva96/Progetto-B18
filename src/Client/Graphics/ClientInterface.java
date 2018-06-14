package Client.Graphics;

import java.io.Serializable;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ClientInterface implements Serializable {

    //private DataInputStream in; //per ricevere da server
    //private DataOutputStream out; //per inviare al server
    private BufferedReader tastiera;
    private String messaggio;
    private ObjectOutputStream out1 = null;
    private ObjectInputStream in1 = null;
    private int porta = 8888;

    public ClientInterface(){
        try {
            System.out.println("Provo a connettermi.....");
            //prova lo connessione con le credenziali date
            Socket server = new Socket(InetAddress.getLocalHost(),porta);

            System.out.println("Connessione al server effettuata");

            //in = new DataInputStream(server.getInputStream());
            in1 = new ObjectInputStream(server.getInputStream());

            //out = new DataOutputStream(server.getOutputStream());
            out1 = new ObjectOutputStream(server.getOutputStream());

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

                //out.writeBytes(messaggio + "\n");
                out1.writeObject(messaggio + "\n");

            } while (!messaggio.toLowerCase().equals("esci"));
            System.out.println("Connessione chiusa");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

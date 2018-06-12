package ClientServer;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client2 {

    private int porta = 8888; //porta server

    private DataInputStream in; //per ricevere da server
    private DataOutputStream out; //per inviare al server
    private BufferedReader tastiera;
    private String messaggio;

    public Client2(){
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

    public static void main(String[] args) throws IOException {

        Client2 C = new Client2();
        C.comunica();
    }
}



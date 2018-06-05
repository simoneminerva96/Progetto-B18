package ClientServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    ServerSocket server = null;
    Socket socketClient = null;

    int porta = 8888; //porta server

    DataInputStream in; //per ricevere da client
    DataOutputStream out; //per inviare al client

    String letto;

    public Socket attendi() throws IOException {

        try {

            System.out.println("Serve attivo.....");
            //inizializzazione servizio
            server = new ServerSocket(porta);

            System.out.println("In ascolto sulla porta....");
            //mi metto in ascolto sulla porta aperta
            socketClient = server.accept();


            System.out.println("Connessione con client, stabilita....");
            server.close(); //in questo modo evito connessioni multiple (accetto un solo client)

            in = new DataInputStream(socketClient.getInputStream()); //input da client
            out = new DataOutputStream(socketClient.getOutputStream()); //output a client



        }
        catch (IOException e){
            e.printStackTrace();
        }
        return socketClient;

    }

    public void comunica(){

        try {

            do {


                System.out.println("aspetto un messaggio...");
                letto = in.readLine();


                System.out.println("messaggio ricevuto:" + letto);

                String risposta = "Ok"; //riposta del server
                out.writeBytes(risposta + "\n");


            } while (!letto.toLowerCase().equals("esci"));
            out.writeBytes("Connessione chiusa" + "\n");
            socketClient.close(); //termino la connesione al client

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //il compito del server è quella di accettare degli input dai client e processi e ridare indietro qualcosa
    public static void main(String[] args) throws IOException {

        Server s = new Server();
        s.attendi();
        s.comunica();

    }
}

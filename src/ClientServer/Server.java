package ClientServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Server implements Runnable {

    private DataInputStream in; //per ricevere da client
    private DataOutputStream out; //per inviare al client

    private String letto;
    private Socket socketClient ;


    public Server(Socket socketClient){
        this.socketClient=socketClient;
    }

    //il compito del server è quella di accettare degli input dai client e processi e ridare indietro qualcosa
    public static void main(String[] args) throws IOException {

        ServerSocket server ;
        Socket socketClient ;
        int porta = 8888; //porta server
        List<Thread> clients = new ArrayList<>();

        //inizializzazione servizio
        server = new ServerSocket(porta);
        System.out.println("SERVER IS READY!!!!");

        while(true) {
            //mi metto in ascolto sulla porta aperta
            socketClient = server.accept();
            System.out.println("Connesso..............");
            Thread t = new Thread(new Server(socketClient));
            t.start();
            clients.add(t);
        }

    }

    /**
     *  Contiene tutto quello che va da server verso client.
     */
    @Override
    public void run() {

        try {
            in = new DataInputStream(socketClient.getInputStream()); //input da client
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
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

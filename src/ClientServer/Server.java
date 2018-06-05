package ClientServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server implements Runnable {

    DataInputStream in; //per ricevere da client
    DataOutputStream out; //per inviare al client

    String letto;
    Socket socketClient ;


    public Server(Socket socketClient){
        this.socketClient=socketClient;
    }

    //il compito del server è quella di accettare degli input dai client e processi e ridare indietro qualcosa
    public static void main(String[] args) throws IOException {

        ServerSocket server ;
        Socket socketClient ;
        int porta = 8888; //porta server

        //inizializzazione servizio
        server = new ServerSocket(porta);
        System.out.println("Connesso..............");

        while(true) {
            //mi metto in ascolto sulla porta aperta
            socketClient = server.accept();
            System.out.println("Connesso..............");
            new Thread(new Server(socketClient)).start();

        }

    }

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

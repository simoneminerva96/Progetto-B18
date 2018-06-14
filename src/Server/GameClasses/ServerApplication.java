package Server.GameClasses;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerApplication {
    public static void main(String[] args) {
        ServerInterface serverInterface;
        ServerSocket server;
        Socket socketClient ;
        int porta = 8888; //porta server
        List<Thread> clients = new ArrayList<>();

        //inizializzazione servizio
        System.out.println("SERVER IS READY!!!!");
        try {
            server = new ServerSocket(porta);
            while(true) {
                //mi metto in ascolto sulla porta aperta
                socketClient = server.accept();
                serverInterface = new ServerInterface(socketClient);
                System.out.println("Connesso..............");
                Thread t = new Thread(serverInterface);
                t.start();
                clients.add(t);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}

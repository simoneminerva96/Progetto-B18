package Server.GameClasses;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerApplication {
    public static void main(String[] args) throws IOException {
        ServerInterface serverInterface;
        ServerSocket server;
        Socket socketClient ;
        int porta = 8888; //porta server

        //inizializzazione servizio
        System.out.println("SERVER IS READY!!!!");
        server = new ServerSocket(porta);
        try {
            while(true) {
                //mi metto in ascolto sulla porta aperta
                socketClient = server.accept();
                serverInterface = new ServerInterface(socketClient);
                System.out.println("Connesso..............");
                ServerInterface st = new ServerInterface(socketClient);
                st.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}

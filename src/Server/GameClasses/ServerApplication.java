package Server.GameClasses;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerApplication {
    public static void main(String[] args) {
        ServerInterface serverInterface;
        Socket socketClient ;

        try {
            ServerSocket server = new ServerSocket(8888);
            System.out.println("SERVER IS READY!!!!");

            while(true) {
                //mi metto in ascolto sulla porta aperta
                socketClient = server.accept();
                serverInterface = new ServerInterface(socketClient);
                System.out.println("Connesso al client");
                //serverInterface.start();     QUANDO C'è IL DB è DISPONIBILE
                serverInterface.sendNicknames();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

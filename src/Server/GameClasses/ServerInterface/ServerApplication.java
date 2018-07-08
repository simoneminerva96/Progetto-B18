package Server.GameClasses.ServerInterface;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Classe Server Application che accetta nuovi client, crea la socket e avvia il metodo start del ServerInterface
 */

public class ServerApplication {
    public static void main(String[] args) {
        ServerInterface serverInterface;
        Socket socketClient ;

        try {
            ServerSocket server = new ServerSocket(8888);
            System.out.println("Server on");

            while(true) {
                socketClient = server.accept();
                serverInterface = new ServerInterface(socketClient);
                System.out.println("Connesso al client");
                serverInterface.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

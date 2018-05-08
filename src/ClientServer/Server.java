package ClientServer;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    //il compito del server è quella di accettare degli input dai client e processi e ridare indietro qualcosa
    public static void main(String[] args) throws IOException {

        //il server delle avere la stessa porta usata nel client
        ServerSocket s1 = new ServerSocket(1342);

        //usata per accettare la richiesta ad s1 da parte el client
        Socket ss =  s1.accept();

        for(;;) {
            //prende il numero dalla richiesta del client
            Scanner sc = new Scanner(ss.getInputStream());
            int number = sc.nextInt();


            int temp = number * 2;

            //bisogna ora ripassare il risultato al client
            PrintStream p = new PrintStream(ss.getOutputStream());
            p.println(temp);
        }

    }
}

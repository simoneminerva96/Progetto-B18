package ClientServer;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        //inizializza una socket collegata all'indirizzo localhost e ad un porta qualsiasi, ovviamente libera
        Socket s = new Socket("127.0.0.1", 1342);

        //prende l'output dal server
        Scanner sc1 = new Scanner(s.getInputStream());

        System.out.println("Enter any number");
        int number = sc.nextInt();

        //gli passo il numero al server appena letto
        PrintStream p = new PrintStream(s.getOutputStream());
        p.println(number);

        //metto il risulato letto dal server nella variabile temp e la stampo
        int temp = sc1.nextInt();
        System.out.println(temp);

    }
}

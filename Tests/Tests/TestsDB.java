package Tests;

import Server.GameClasses.ConnectionDB.ConnectionDB;
import Server.GameClasses.Credenziali;

import java.util.Scanner;


public class TestsDB {

    private static Scanner scanner = new Scanner(System.in);

    private static void TestDB(){
        Object test = null;
        String Nome;
        String Pass;
        ConnectionDB connection = new ConnectionDB();
        Credenziali cred;

        System.out.println("Dammi un nome da provare ");
        Nome = scanner.nextLine();

        System.out.println("Dammi la password");
        Pass = scanner.nextLine();

        cred = new Credenziali(Nome, Pass);

        test = connection.ExistsPlayer(cred);
        System.out.println(test);

        assert test != null : "Login andato male";
    }

    public static void main(String[] args) {
        TestDB();
    }

}

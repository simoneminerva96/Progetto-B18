package Client.Graphics;

import Server.GameClasses.Credenziali;
import Server.GameClasses.TypeOfRequest;

import java.io.Serializable;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientInterface implements Serializable {
    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;

    public ClientInterface(){

        try {
            Socket server = new Socket(InetAddress.getLocalHost(),8888);
            System.out.println("Connessione al server effettuata");
            out = new ObjectOutputStream(server.getOutputStream());
            in = new ObjectInputStream(server.getInputStream());
            Credenziali credenziali = new Credenziali("ciao", "ciao");
            Boolean checks = true;
            out.writeObject(credenziali);
            out.writeObject(checks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void comunica(){
        /*try {
            Socket server = new Socket(InetAddress.getLocalHost(),8888);
            System.out.println("Connessione al server effettuata");
            out = new ObjectOutputStream(server.getOutputStream());
            in = new ObjectInputStream(server.getInputStream());
            //DieGUI prova = new DieGUI();
            //System.out.println("Object to be written: "+ prova);
            //out1.writeObject(prova);

            //DieGUI die = (DieGUI) in.readObject();
            //System.out.println("Object received: " +die);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public void sendCredential(String usr, String psw, TypeOfRequest typeOfRequest){
        Credenziali credenziali = new Credenziali(usr, psw);
        try {
            out.writeObject(credenziali);
            out.writeObject(typeOfRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendForLogin (String usr, String psw) {
        Credenziali credenziali = new Credenziali(usr, psw);
        try {
            out.writeObject(credenziali);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendForRegistration (String usr, String psw) {
        Credenziali credenziali = new Credenziali(usr, psw);
        try {
            out.writeObject(credenziali);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean receiveOutcome (){
        boolean check = false;
        try {
            check = (boolean) in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return check;
    }

    public boolean login()  {
        boolean check = false;
        try {
            check = (boolean) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return check;
    }

    public boolean registration(){
        boolean check = false;
        try {
            check = (boolean) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return check;
    }
}

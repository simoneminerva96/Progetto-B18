package Client.Graphics;

import Server.GameClasses.*;

import java.io.Serializable;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class ClientInterface implements Serializable {
    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;

    public ClientInterface(){
        try {
            Socket server = new Socket(InetAddress.getLocalHost(),8888);
            System.out.println("Connessione al server effettuata");
            out = new ObjectOutputStream(server.getOutputStream());
            in = new ObjectInputStream(server.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public int getIndex () {
        int index = 0;
        try {
            index = (int) in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return index;
    }

    public void sendNumberOfPlayers(int n){
        try {
            out.writeObject(n);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> getResultsOfRoll(){
        ArrayList<Integer> results=new ArrayList<>();
        try {
            results.addAll((ArrayList< Integer>)in.readObject());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return results;
    }

    public int getDiceValue () {
        int diceValue = 0;
        try {
            diceValue = (int) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return diceValue;
    }

    public void setDirection (Direction direction) {
        try {
            out.writeObject(direction);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Question getQuestion () {
        Question question = null;
        try {
            question = (Question) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return question;
    }

    public boolean answerQuestion () {
        boolean esito = false;
        try {
            esito = (boolean) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return esito;
    }

    public boolean checkBonusMalus () {
        boolean esito = false;
        try {
            esito = (boolean) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return esito;
    }

    public ArrayList<String>getNicknames(){
        ArrayList<String>nicknames=new ArrayList<>();
        try{
            nicknames.addAll((ArrayList<String>) in.readObject());
        }catch(IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return nicknames;
    }

}

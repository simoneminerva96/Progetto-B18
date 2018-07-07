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
    private boolean connected = false;

    ClientInterface(){
        try {
            //93.41.247.149 1201
            //InetAddress.getLocalHost() 8888
            Socket server = new Socket(InetAddress.getLocalHost(), 8888);
            System.out.println("Connessione al server effettuata");
            connected = true;
            out = new ObjectOutputStream(server.getOutputStream());
            in = new ObjectInputStream(server.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void sendCredential(String usr, String psw, TypeOfRequest typeOfRequest){
        Credenziali credenziali = new Credenziali(usr, psw);
        try {
            out.writeObject(credenziali);
            out.writeObject(typeOfRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    boolean receiveOutcome (){
        boolean check = false;
        try {
            check = (boolean) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    /**metodo generico che invia un check al server (per fargli eseguire determinare operaz.) */
    void sendOutcome(){
        try {
            out.writeObject(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getIndex () {
        int index =-1;
        try {
            index = (Integer)in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return index;
    }

    void sendindex(int n){
        try {
            out.writeObject(n);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ArrayList<Integer> getResultsOfRoll(){
        ArrayList<Integer> results=new ArrayList<>();
        try {
            results.addAll((ArrayList< Integer>)in.readObject());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    int getDiceValue () {
        int diceValue = 0;
        try {
            diceValue = (int) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return diceValue;
    }

    void sendDirection (Direction direction) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return question;
    }


    boolean checkBonusMalus () {
        boolean esito = false;
        try {
            esito = (boolean) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return esito;
    }

    BonusMalusRandom getType () {
        BonusMalusRandom bonusMalus=null;
        try {
            bonusMalus = (BonusMalusRandom) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bonusMalus;
    }

    Boolean getCheckInitialSquare() {
        boolean check=false;
        try {
            check = (boolean) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    ArrayList<String>getNicknames(){
        ArrayList<String>nicknames=new ArrayList<>();
        try{
            nicknames.addAll((ArrayList<String>) in.readObject());
        }catch(Exception e){
            e.printStackTrace();
        }
        return nicknames;
    }

    String getCategoriesOfTheSliceObtained () {
        String c = null;
        try {
            c = (String) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    boolean isConnected() { return connected; }
}

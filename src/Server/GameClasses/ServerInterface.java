package Server.GameClasses;

import java.io.*;
import java.net.Socket;

public class ServerInterface extends Thread implements Serializable {
    private Controller controller;
    private Socket socketClient ;
    private ObjectInputStream in ;
    private ObjectOutputStream out;
    private TypeOfRequest typeOfRequest;
    private int numberOfPlayers;
    private boolean esitoRisposta;
    private int index;

    public ServerInterface(Socket socketClient){
        this.socketClient = socketClient;
        controller = new Controller();
        try{
            in = new ObjectInputStream(socketClient.getInputStream());
            out = new ObjectOutputStream(socketClient.getOutputStream());
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        //getCredenziali();
        //riceve il numero di giocatori selezionato nel client
        numberOfPlayers = getIndex();
        //istanzia i giocatori e esegue il lancio iniziale del dado
        controller.initializePlayers(numberOfPlayers);
        controller.beginningDieRoll();
        //invio i nicknames dei giocatori
        sendNicknames();
        //invio i risultati dei lanci del dado
        sendResultsOfRoll();
        //invio i nicknames ordinati per lo state trivia
        sendNicknames();
        while (true)
        {
            if(receiveOutcome()){
                controller.setPlayerOnTurn();
            }
            sendIndex();
            sendDiceValue();
            controller.setDirection(getDirection());
            sendcheckBonusMalus();
            if(controller.checkBonusMalus()) sendType();
            else {
                sendCheckInitialSquare();
                if(!controller.checkInitialSquare()){
                    sendQuestion();
                    System.out.println("sended question");
                    index=getIndex();
                    System.out.println("received index");
                    esitoRisposta=controller.answerQuestion(index);
                    sendCheck(esitoRisposta);
                    System.out.println("sended check");
                }
                else {
                    sendCheck(controller.verifyVictory());
                }
            }
        }
    }
    //riceve le credenziali per il login/registrazione dal client
    public void getCredenziali(){
        boolean check = false;
        Credenziali credenziali = null;
        try {
            credenziali = (Credenziali) in.readObject();
            typeOfRequest = (TypeOfRequest) in.readObject();
            switch (typeOfRequest) {
                case REGISTRAZIONE:
                    check = request(credenziali, TypeOfRequest.REGISTRAZIONE);
                    break;
                case LOGIN:
                    check = request(credenziali, TypeOfRequest.LOGIN);
                    break;
            }
            sendCheck(check);
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public int getIndex(){
        Integer index=0;
        try {
            index = (int) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return index;
    }
    public boolean receiveOutcome (){
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

    //invia al client la conferma di avvenuta registrazione o login
    public void sendCheck(boolean check){
        try {
            out.writeObject(check);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public boolean request (Credenziali credenziali, TypeOfRequest typeOfRequest) {
        return controller.request(credenziali, typeOfRequest);
    }

    public void sendNicknames(){
        try{
            out.writeObject(controller.getOrdinatedNicknames());
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void sendResultsOfRoll(){
        try{
            out.writeObject(controller.getResultsOfRoll());
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    //invia l'indice del giocatore che è di turno
    public void sendIndex(){
        try{
            out.writeObject(controller.getIndex());
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    //invia il risultato del lancio del dado
    public void sendDiceValue () {
        try {
            out.writeObject(controller.getDiceValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Direction getDirection () {
        Direction direction=null;
        try {
            direction=(Direction)in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return direction;
    }

    public void sendcheckBonusMalus () {
        try {
            out.writeObject(controller.checkBonusMalus());
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void sendType () {
        try {
            out.writeObject(controller.checkType());
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void sendCheckInitialSquare () {
        try {
            out.writeObject(controller.checkInitialSquare());
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void sendQuestion () {
        try {
            out.writeObject(controller.getQuestion());
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}

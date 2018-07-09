package Server.GameClasses.ServerInterface;

import Server.GameClasses.GameClasses.Credenziali;
import Server.GameClasses.GameClasses.Direction;
import Server.GameClasses.GameClasses.TypeOfRequest;

import java.io.*;
import java.net.Socket;

public class ServerInterface extends Thread implements Serializable {
    private Controller controller;
    private Socket socketClient ;
    private ObjectInputStream in ;
    private ObjectOutputStream out;
    private TypeOfRequest typeOfRequest;
    private int numberOfPlayers;
    private boolean esitoRisposta, loginEffettuato;
    private int index;
    private String nicknameOfPlayerLogged;

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
        while (!loginEffettuato) {
            loginEffettuato = getCredenziali();
        }
        //riceve il numero di giocatori selezionato nel client
        numberOfPlayers = getIndex();
        //istanzia i giocatori e esegue il lancio iniziale del dado
        controller.initializePlayers(numberOfPlayers,nicknameOfPlayerLogged);
        controller.beginningDieRoll();
        //invio i nicknames dei giocatori
        sendNicknames();
        //invio i risultati dei lanci del dado
        sendResultsOfRoll();
        //invio i nicknames ordinati per lo state trivia
        sendNicknames();

        while (true) {
            if(receiveOutcome())
                controller.setPlayerOnTurn();
            sendIndex();
            sendDiceValue();
            controller.setDirection(getDirection());
            sendcheckBonusMalus();
            if(controller.checkBonusMalus()) sendType();
            else {
                sendCheckInitialSquare();
                if(!controller.checkInitialSquare()) {
                    sendQuestion();
                    index = getIndex();
                    esitoRisposta = controller.answerQuestion(index);
                    sendCheck(esitoRisposta);
                    if (esitoRisposta) {
                        if (controller.isFinalQuestion())
                            sendCategories(controller.getCategoriesOfTheSliceObtained().name());
                        else
                        sendCategories("Nessuna");
                    }
                }
                else
                    sendCheck(controller.verifyVictory());
            }
        }
    }

    /** riceve le credenziali per il login/registrazione dal client. Restituisce true se è stato effettuato il login*/
    private boolean getCredenziali(){
        boolean check = false;
        Credenziali credenziali;
        try {
            credenziali = (Credenziali) in.readObject();
            nicknameOfPlayerLogged=credenziali.getUser();
            typeOfRequest = (TypeOfRequest) in.readObject();
            switch (typeOfRequest) {
                case REGISTRAZIONE:
                    check = request(credenziali, TypeOfRequest.REGISTRAZIONE);
                    break;
                case LOGIN:
                    check = request(credenziali, TypeOfRequest.LOGIN);
                    if (check)
                        loginEffettuato = true;
                    break;
            }
            sendCheck(check);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return loginEffettuato;
    }

    /** riceve l'indice della risposta */
    private int getIndex(){
        Integer index=0;
        try {
            index = (int) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return index;
    }
    /** riceve un boolean che indica se è stato lanciato il dado*/
    private boolean receiveOutcome (){
        boolean check = false;
        try {
            check = (boolean) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    /** invia al client la conferma di avvenuta registrazione o login */
    private void sendCheck(boolean check){
        try {
            out.writeObject(check);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    /** esegue il login o la registrazione */
    private boolean request (Credenziali credenziali, TypeOfRequest typeOfRequest) { return controller.request(credenziali, typeOfRequest); }

    /** invia al client i nickname dei giocatori */
    private void sendNicknames(){
        try{
            out.writeObject(controller.getOrdinatedNicknames());
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /** invia al client i lanci dei dadi iniziali ottenuti */
    private void sendResultsOfRoll(){
        try{
            out.writeObject(controller.getResultsOfRoll());
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /** invia l'indice del giocatore che è di turno */
    private void sendIndex(){
        try{
            out.writeObject(controller.getIndex());
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /** invia il risultato del lancio del dado */
    private void sendDiceValue () {
        try {
            out.writeObject(controller.getDiceValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** riceve dal client la direzione presa dal giocatore */
    private Direction getDirection () {
        Direction direction=null;
        try {
            direction=(Direction)in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return direction;
    }

    /** invia al client un boolean se la casella è di bonus o malus */
    private void sendcheckBonusMalus () {
        try {
            out.writeObject(controller.checkBonusMalus());
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    /** invia al client il tipo della bonus */
    private void sendType () {
        try {
            out.writeObject(controller.checkType());
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    /** invia al client se la casella è iniziale o no */
    private void sendCheckInitialSquare () {
        try {
            out.writeObject(controller.checkInitialSquare());
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    /** invia al client la domanda e le relative risposte */
    private void sendQuestion () {
        try {
            out.writeObject(controller.getQuestion());
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    /** invia al client la categoria dello spicchio ottenuto */
    private void sendCategories (String categoria) {
        try {
            out.writeObject(categoria);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

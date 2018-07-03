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
        try {
            while (true)
            {
                //getCredenziali();
                //riceve il numero di giocatori selezionato nel client
                numberOfPlayers = (int) in.readObject();
                //istanzia i giocatori e esegue il lancio iniziale del dado
                controller.initializePlayers(numberOfPlayers);
                controller.beginningDieRoll();
                //invio i nicknames dei giocatori
                sendNicknames();
                //invio i risultati dei lanci del dado
                sendResultsOfRoll();
                //invio i nicknames ordinati per lo state trivia
                sendNicknames();

            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
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
    //invia al client la conferma di avvenuta registrazione
    public void sendCheck(boolean check) throws IOException{
        out.writeObject(check);
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
}

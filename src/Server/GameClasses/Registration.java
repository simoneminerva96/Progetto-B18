package Server.GameClasses;

import Server.GameClasses.ConnectionDB.ConnectionDB;

/**
 * classe che prende i dati in ingresso dall'interfaccia di registrazione,
 * controlla se i dati inseriti sono disponibili e aggiorna il db in caso di successo
 *  @author Ansaldi Jacopo <jacopo.ansaldi01@universitadipavia.it>
 */

public class Registration {
    private  ConnectionDB connection;

    public Registration(){
        connection=new ConnectionDB();
    }

    /**
     * Prelevati i dati inseriti dall'utente, richiama il db per effettuare la registrazione
     * @param nicknameInserted username inserito dall'utente
     * @param passwordInserted password inserita dall'utente
     * @return boolean se è andata a buon fine o no
     */
    public boolean Registration(String nicknameInserted,String passwordInserted){
        return connection.getPlayer(nicknameInserted,passwordInserted);
    }

}

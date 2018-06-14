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

    /*
       metodo da chiamare quando l'utente preme il tasto register sull'interfaccia
       prende i dati inseriti ed effettua la registrazione
    */
    public boolean Registration(String nicknameInserted,String passwordInserted){
        //effettuare il controllo sui dati inseriti, se consentiti effettuare la reg senno mess. di errore
        return connection.getPlayer(nicknameInserted,passwordInserted);
    }

}

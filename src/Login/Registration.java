package Login;

import ConnectionDB.ConnectionDB;

/**
 * classe che prende i dati in ingresso dall'interfaccia di registrazione,
 * controlla se i dati inseriti sono disponibili e aggiorna il db in caso di successo
 *  @author Ansaldi Jacopo <jacopo.ansaldi01@universitadipavia.it>
 */

public class Registration {
    private String nicknameInserted,passwordInserted;
    private  ConnectionDB connection;

    public Registration(){
        nicknameInserted=null;
        passwordInserted=null;
        connection=new ConnectionDB();
    }

    /*
       metodo da chiamare quando l'utente preme il tasto register sull'interfaccia
       prende i dati inseriti ed effettua la registrazione
    */
    public boolean Registration(String nicknameInserted,String passwordInserted){
        this.nicknameInserted=nicknameInserted;
        this.passwordInserted=passwordInserted;
        boolean check=false;
        //effettuare il controllo sui dati inseriti, se consentiti effettuare la reg senno mess. di errore
        String mess=connection.getPlayer(nicknameInserted,passwordInserted);
        System.out.println(mess);
        return check;
    }

    //MAIN DI PROVA PER TESTAR LA CLASSE
    public static void main(String[] args) {
        Registration registration=new Registration();
        registration.Registration("prova","prova");
    }
}

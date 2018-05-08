package Login;

import ConnectionDB.ConnectionDB;

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
        //effettuare il controllo sui dati inseriti
        return check;
    }
}

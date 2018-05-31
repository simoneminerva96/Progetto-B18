package Login;

import ConnectionDB.ConnectionDB;

/**
 * classe che prende i dati in ingresso dall'interfaccia grafica di login(Login_interface)
 * ed effettua gli opportuni controlli,controlla se il login
 * viene effettuato,ecc..
 * @author Ansaldi Jacopo <jacopo.ansaldi01@universitadipavia.it>
*/
public class Login {
    private String nicknameInserted,passwordInserted;
    private ConnectionDB connection;

    public Login(){
        nicknameInserted = null;
        passwordInserted = null;
        connection = new ConnectionDB();
    }

    /**
     Metodo da chiamare quando l'utente preme il tasto login sull'interfaccia. Prende i dati inseriti
     ed effettua il login se sono corretti, altrimenti segna messaggio di errore.
     @param nicknameInserted nickname inserito
     @param passwordInserted password inserita
     @return flag di avvenuto login
     */
    public boolean Login(String nicknameInserted,String passwordInserted){
        this.nicknameInserted = nicknameInserted;
        this.passwordInserted = passwordInserted;
        boolean check = false;

        String mess=connection.ExistsPlayer(nicknameInserted,passwordInserted);
        System.out.println(mess);
        return check;
    }

}

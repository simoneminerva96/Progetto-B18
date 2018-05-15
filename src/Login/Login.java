package Login;

import ConnectionDB.ConnectionDB;

import javax.swing.*;

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
        nicknameInserted=null;
        passwordInserted=null;
        connection=new ConnectionDB();
    }
    /*
        metodo da chiamare quando l'utente preme il tasto login sull'interfaccia
        prende i dati inseriti ed effettua il login
     */
    public boolean Login(String nicknameInserted,String passwordInserted){
        this.nicknameInserted=nicknameInserted;
        this.passwordInserted=passwordInserted;
        boolean check=false;
        //effettuare il controllo sui dati inseriti, se corretti loggarsi, se non corrispondono dare mess di errore
        String mess=connection.ExistsPlayer(nicknameInserted,passwordInserted);
        //se il messaggio corrisponde a quello di avvenuto login, il login è stato effettuato ==> ritorna true
        if(mess.equalsIgnoreCase("benvenuto!!")){
            check=true;
        }
        System.out.println(mess);
        return check;
    }

    //MAIN DI PROVA
    public static void main(String[] args) {
        Login l=new Login();
        l.Login("rita","ok");
    }

}

import javax.swing.*;

/*
    classe che prende i dati in ingresso dall'interfaccia grafica di login(Login_interface)
    ed effettua gli opportuni controlli, aggiorna il database in caso di registrazione,controlla se il login
    viene effettuato,ecc..
*/
public class Login_registrazione {
    private InterfacciaLogin login_interface;  // interfaccia di login associata, poi bisognerà metter la classe giusta
    private String nicknameInserted,passwordInserted; // campi per i dati inseriti che riceverà dall'interfaccia grafica
    public Login_registrazione(){
        login_interface =new InterfacciaLogin();  //istanzio l'interfaccia
        login_interface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login_interface.setVisible(true);
    }







}

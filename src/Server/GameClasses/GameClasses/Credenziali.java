package Server.GameClasses.GameClasses;

import java.io.Serializable;

/** Classe che rappresenta le credenziali per poter effettuare il login o la registrazione */
public class Credenziali implements Serializable {
    private String username;
    private String password;

    public Credenziali (String user, String password){
        this.username = user;
        this.password = password;
    }

    public String getUser() { return username; }

    public String getPassword() { return password; }
}

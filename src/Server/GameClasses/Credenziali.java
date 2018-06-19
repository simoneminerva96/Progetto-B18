package Server.GameClasses;

import java.io.Serializable;

public class Credenziali implements Serializable {
    private String user;
    private String password;

    public Credenziali (String user, String password){
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}

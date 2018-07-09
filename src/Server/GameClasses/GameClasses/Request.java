package Server.GameClasses.GameClasses;

import Server.GameClasses.ConnectionDB.ConnectionDB;

/**
 * classe che rappresenta la richiesta di login o registrazione che avvengono collegandosi al database
 */
    public class Request {
        private ConnectionDB connection;

    public Request () {
        connection = new ConnectionDB();
    }

    public boolean request (Credenziali credenziali, TypeOfRequest typeOfRequest) {
        boolean check = false;
        switch (typeOfRequest) {
            case LOGIN:
                check = connection.ExistsPlayer(credenziali);
                break;
            case REGISTRAZIONE:
                check = connection.getPlayer(credenziali);
                break;
        }
        return check;
    }
}

package Server.GameClasses;

import Server.GameClasses.ConnectionDB.ConnectionDB;

/**
 * classe che rappresenta la richiesta di login o registrazione che avvengono collegandosi al database
 */
    class Request {
        private ConnectionDB connection;

    Request () {
        connection = new ConnectionDB();
    }

    boolean request (Credenziali credenziali, TypeOfRequest typeOfRequest) {
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

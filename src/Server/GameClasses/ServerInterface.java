package Server.GameClasses;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ServerInterface {
    private DataInputStream in; //per ricevere da client
    private DataOutputStream out; //per inviare al client
    private Socket socketClient ;

    public ServerInterface(){

    }
}

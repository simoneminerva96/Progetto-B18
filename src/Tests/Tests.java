package Tests;
import Client.Graphics.CheckControls;
import Client.Graphics.ClientInterface;
import Client.Graphics.Player.Player;
import Server.GameClasses.*;
import Server.GameClasses.ConnectionDB.ConnectionDB;
import org.junit.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Tests {

    @Test
    public void testconnection(){
        Boolean test;
        String Nome="admin";
        String Pass="admin";
        ConnectionDB connection = new ConnectionDB();
        Credenziali cred;
        cred = new Credenziali(Nome, Pass);
        test = connection.ExistsPlayer(cred);
        Assertions.assertEquals(test,true);
    }

    @Test
    public void testMovement(){
        Server.GameClasses.Player p=new Server.GameClasses.Player("prova");
        Turn turn=new Turn(p,new BoardProva());
        turn.dieLaunch();
        turn.setChosenDirection(Direction.FORWARD);
        turn.movePlayer();
        Assertions.assertEquals(p.getActualPosition(),turn.getDieresult());
    }

    @Test
    public void testClient(){
        boolean test;
        CheckControls checkControls=new CheckControls();
        ClientInterface clientInterface = new ClientInterface(checkControls);
        test = clientInterface.isConnected();
        Assertions.assertEquals(test,true);

    }
}

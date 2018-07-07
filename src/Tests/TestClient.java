package Tests;
import Client.Graphics.CheckControls;
import Client.Graphics.ClientInterface;
import Client.Graphics.Player.Player;
import Server.GameClasses.*;
import Server.GameClasses.ConnectionDB.ConnectionDB;
import org.junit.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestClient {
    @Test
    public void testClient(){
        boolean test;
        CheckControls checkControls=new CheckControls();
        ClientInterface clientInterface = new ClientInterface(checkControls);
        test = clientInterface.isConnected();
        Assertions.assertEquals(test,true);

    }


}

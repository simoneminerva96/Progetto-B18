package Tests;
import Client.Graphics.CheckControls;
import Client.Graphics.ClientInteface.ClientInterface;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestClient {
    @Test
    public void testClient(){
        boolean test;
        ClientInterface clientInterface = new ClientInterface();
        CheckControls checkControls=new CheckControls(clientInterface);
        test = clientInterface.isConnected();
        Assertions.assertEquals(test,true);

    }


}

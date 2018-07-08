package Tests;
import Client.Graphics.CheckControls;
import Client.Graphics.ClientInteface.ClientInterface;
import Client.Graphics.Player.Coordinate;
import Client.Graphics.Player.Player;
import Client.Graphics.Map.Map;
import Server.GameClasses.Board;
import Server.GameClasses.Direction;
import Server.GameClasses.Turn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.newdawn.slick.SlickException;

import java.sql.SQLException;
import java.util.Random;

public class TestClient {
    @Test
    public void testClient(){
        boolean test;
        ClientInterface clientInterface = new ClientInterface();
        CheckControls checkControls=new CheckControls(clientInterface);
        test = clientInterface.isConnected();
        Assertions.assertEquals(test,true);

    }

    @Test
    public void testplayerguiforward() throws SlickException, SQLException {
        Random generator = new Random();
        int extractNumber;

        Coordinate cor ;
        Coordinate cor2;

        Map m = new Map(20,20,50);
        Client.Graphics.Player.Player player = new Client.Graphics.Player.Player("test",1,m);


        int min = 1;
        int max = 6 ;
        int range = ((max-min) + 1);

        extractNumber = generator.nextInt(range) + min;
        cor2 = new Coordinate(18-extractNumber*2,18);


        player.update(extractNumber, Direction.FORWARD);
        cor = new Coordinate(player.getX(), player.getY());

        Assertions.assertEquals(cor.getX(),cor2.getX());
        Assertions.assertEquals(cor.getY(),cor2.getY());

    }


    @Test
    public void testplayerguiback() throws SlickException, SQLException {
        Random generator = new Random();
        int extractNumber;

        Coordinate cor ;
        Coordinate cor2;

        Map m = new Map(20,20,50);
        Client.Graphics.Player.Player player = new Client.Graphics.Player.Player("test",1,m);


        int min = 1;
        int max = 6 ;
        int range = ((max-min) + 1);

        extractNumber = generator.nextInt(range) + min;
        cor2 = new Coordinate(18,18-extractNumber*2);


        player.update(extractNumber, Direction.BACK);
        cor = new Coordinate(player.getX(), player.getY());

        Assertions.assertEquals(cor.getX(),cor2.getX());
        Assertions.assertEquals(cor.getY(),cor2.getY());

    }


}

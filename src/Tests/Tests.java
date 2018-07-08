package Tests;
import Client.Graphics.Player.Player;
import Server.GameClasses.*;
import Server.GameClasses.ConnectionDB.ConnectionDB;
import org.junit.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.sql.SQLException;

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
    //testa il movimento partendo da casella iniziale in avanti
    @Test
    public void testMovementForward(){
        Server.GameClasses.Player p=new Server.GameClasses.Player("prova");
        Turn turn= null;
        try {
            turn = new Turn(p,new Board());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int DieResult=turn.dieLaunch();
        turn.setChosenDirection(Direction.FORWARD);
        turn.movePlayer();
        Assertions.assertEquals(p.getActualPosition(),DieResult);
    }
    //testa il movimento all'indietro partendo dalla casella iniziale
    @Test
    public void testMovementBack(){
        Server.GameClasses.Player p=new Server.GameClasses.Player("prova");
        Turn turn= null;
        try {
            turn = new Turn(p,new Board());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int DieResult=turn.dieLaunch();
        turn.setChosenDirection(Direction.BACK);
        turn.movePlayer();
        Assertions.assertEquals(p.getActualPosition(),36-DieResult);
    }
    //testa il movimento in avanti partendo dall'ultima casella (index=35)
    @Test
    public void testMovementForwardWithDifferentStart(){
        Server.GameClasses.Player p=new Server.GameClasses.Player("prova");
        p.setActualPosition(35);    //ultima casella
        Turn turn= null;
        try {
            turn = new Turn(p,new Board());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int DieResult=turn.dieLaunch();
        turn.setChosenDirection(Direction.FORWARD);
        turn.movePlayer();
        Assertions.assertEquals(p.getActualPosition(),DieResult -1);
    }

    @Test
    public void testDomandaFinale(){

        boolean test = true;
        Server.GameClasses.Player p=new Server.GameClasses.Player("prova");
        p.setActualPosition(32); //setto una caseela con una domanda
        Turn turn= null;
        try {
            turn = new Turn(p,new Board());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        turn.obtainSlice();

        Assertions.assertEquals(turn.isFinalQuestion(),test);

    }

    @Test
    public void testSpicchio(){

        Categories test = Categories.GEOGRAFIA;
        Server.GameClasses.Player p=new Server.GameClasses.Player("prova");
        p.setActualPosition(32); //setto una caseela con una domanda
        Turn turn= null;
        try {
            turn = new Turn(p,new Board());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        turn.obtainSlice();

        Assertions.assertEquals(turn.getCategoriesOfTheSliceObtained(),test);

    }

}

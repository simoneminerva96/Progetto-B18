package Test;


import GameClasses.Die;
import GameClasses.Player;
import GameClasses.TrivialGame;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TrivialGameTest {
    @Test
    public void DieLaunch(){
        Die die=new Die();
        int roll=die.Launch();
        assertTrue(roll== 1 || roll== 2 || roll== 3 || roll== 4 || roll== 5 || roll== 6,"result: " + roll);
    }
    @Test
    public void InitializePlayersTest(){
        TrivialGame match=new TrivialGame();
        ArrayList<String> gamingPlayers=new ArrayList<String>();
        gamingPlayers.add("JackTheBoss");
        gamingPlayers.add("RitaTheLoser");
        gamingPlayers.add("SteIlNiggaz");
        gamingPlayers.add("quelloCheMiRubaLaTipa");
        match.initializePlayers(gamingPlayers);
        assertNotNull(match.getPlayers());
    }
    @Test
    public void InitializePiecesTest(){
        TrivialGame match=new TrivialGame();
        match.InitializePossiblePieces();
        assertNotNull(match.getPossiblePieces());
    }
}
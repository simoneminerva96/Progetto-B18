package Test;

import GameClasses.Board;
import GameClasses.Die;
import GameClasses.TrivialGame;
//import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;


class TrivialGameTest {
   //@Test
    public void DieLaunch(){
        Die die=new Die();
        int roll=die.Launch();
        assertTrue(roll== 1 || roll== 2 || roll== 3 || roll== 4 || roll== 5 || roll== 6);
    }
   // @Test
    public void Initialize4PlayersTest(){
        TrivialGame match=new TrivialGame();
        ArrayList<String> gamingPlayers=new ArrayList<String>();
        gamingPlayers.add("JackTheBoss");
        gamingPlayers.add("RitaTheLoser");
        gamingPlayers.add("SteIlNiggaz");
        gamingPlayers.add("quelloCheMiRubaLaTipa");
        match.initializePlayers(gamingPlayers);
        assertTrue(match.getPlayers().size() == 4);
    }
  // @Test
    public void Initialize3PlayersTest(){
        TrivialGame match=new TrivialGame();
        ArrayList<String> gamingPlayers=new ArrayList<String>();
        gamingPlayers.add("JackTheBoss");
        gamingPlayers.add("RitaTheLoser");
        gamingPlayers.add("SteIlNiggaz");
        match.initializePlayers(gamingPlayers);
        assertTrue(match.getPlayers().size() == 3);
    }
  // @Test
    public void Initialize2PlayersTest(){
        TrivialGame match=new TrivialGame();
        ArrayList<String> gamingPlayers=new ArrayList<String>();
        gamingPlayers.add("JackTheBoss");
        gamingPlayers.add("RitaTheLoser");
        match.initializePlayers(gamingPlayers);
        assertTrue(match.getPlayers().size() == 2);
    }
   // @Test
    public void checkOrderedPlayer(){
        TrivialGame match=new TrivialGame();
        ArrayList<String> gamingPlayers=new ArrayList<String>();
        gamingPlayers.add("JackTheBoss");
        gamingPlayers.add("RitaTheLoser");
        gamingPlayers.add("SteIlNiggaz");
        gamingPlayers.add("quelloCheMiRubaLaTipa");
        match.initializePlayers(gamingPlayers);
        match.BeginningDieRoll();
        for(int i=0;i<match.getPlayers().size() -1 ;i++){
            assertTrue(match.getPlayers().get(i).getInitialRollResult() > match.getPlayers().get(i+1).getInitialRollResult());
        }
    }

}
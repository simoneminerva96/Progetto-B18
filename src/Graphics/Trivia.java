package Graphics;

import GameClasses.Die;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import javax.swing.*;

public class Trivia extends BasicGameState {

    Map map;
    Player p;
    PlayerGUI pGUI;
    Pedina piece;

    Die die=new Die();




    public String mouse= "No input";
    float x=100,y=100;
    Boolean b=false;

    public Trivia(int id) throws Exception {
    }

    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

        map=new Map(22,22,"res/map/1.0.png",32);
        p=new Player("ONE",3,map);
        String dwn="res/char/FFIV/Palom/palomdwn.png";
        String lft="res/char/FFIV/Palom/palomsx.png";
        String rght="res/char/FFIV/Palom/palomdx.png";
        String up="res/char/FFIV/Palom/palomup.png";

        piece=new Pedina(up,dwn,lft,rght,32,32);
        pGUI=new PlayerGUI(p,piece);
        pGUI.getPedina().getCurrentImage().stop();



    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {

        Image map=new Image("res/map/1.0.png");
        graphics.drawImage(map,0,0);
        pGUI.getPedina().getCurrentImage().draw(pGUI.getxUpdate(),pGUI.getyUpdate());
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        float xpos=Mouse.getX();
        float ypos=Mouse.getY();

        mouse="Mouse position x:"+xpos+ " y: "+ypos;
        Input input=gameContainer.getInput();

        if(input.isKeyPressed(Input.KEY_W)){//vai indietro
            b=true;
            int diceN=die.Launch();
            System.out.println("DADO: "+diceN);
            p.update(diceN,Direction.BACK);
            pGUI.updateCoordinates();
        }
        if(input.isKeyPressed(Input.KEY_S)){ //vai avanti
            b=true;
            int diceN=die.Launch();
            System.out.println("DADO: "+diceN);
            p.update(diceN,Direction.FORWARD);
            pGUI.updateCoordinates();
        }

            pGUI.updateOnEachFrame(i);




    }
}

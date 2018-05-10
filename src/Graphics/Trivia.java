package Graphics;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Trivia extends BasicGameState {
    Image backgroundMap, currentDie, back, forward;
    DieGUI d;
    Map map;
    Player p;
    PlayerGUI pGUI;
    Pedina piece;
    boolean moved = false;
    Domanda prova = new Domanda(3);
    public String mouse= "No input";
    Boolean b=false;

    public Trivia(int id) {
    }

    public int getID() {
        return 2;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        map=new Map(20,20,35);
        backgroundMap = new Image("res/map/Tabella3.png");
        p=new Player("ONE",3,map);
        String dwn="res/char/FFIV/Palom/palomdwn.png";
        String lft="res/char/FFIV/Palom/palomsx.png";
        String rght="res/char/FFIV/Palom/palomdx.png";
        String up="res/char/FFIV/Palom/palomup.png";
        back = new Image("res/buttons/Freccia1.png");
        forward = new Image("res/buttons/Freccia2.png");
        d = new DieGUI();
        currentDie = d.getCurrentDie();
        piece=new Pedina(up,dwn,lft,rght,35,35);
        pGUI=new PlayerGUI(p,piece);
        pGUI.getPedina().getCurrentImage().stop();
        prova.init(gameContainer, stateBasedGame);

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        graphics.drawString(mouse, 900, 650);
        graphics.drawImage(backgroundMap,0,0);
        pGUI.getPedina().getCurrentImage().draw(pGUI.getxUpdate(),pGUI.getyUpdate());
        currentDie.draw(1100,550);
        back.draw(800,550);
        forward.draw(900,550);
        if(pGUI.ready){
            try {
                prova.render(gameContainer, stateBasedGame, graphics);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) {
        float xpos=Mouse.getX();
        float ypos=Mouse.getY();

        mouse="Mouse position x:"+xpos+ " y: "+ypos;
        Input input=gameContainer.getInput();

            if (xpos>815 && xpos<880 && ypos>48 && ypos<147) {
                if (input.isMousePressed(0)) { //vai indietro
                    pGUI.clicked = true;
                    b = true;
                    int diceN = d.setCurrentDie();
                    currentDie = d.getCurrentDie();
                    p.update(diceN, Direction.BACK);
                    pGUI.updateCoordinates();
                }
            }
            if (xpos>916 && xpos<983 && ypos>48 && ypos<147) { //vai avanti
                if (input.isMousePressed(0)) {
                        moved = true;
                        b = true;
                        int diceN = d.setCurrentDie();
                        currentDie = d.getCurrentDie();
                        p.update(diceN, Direction.FORWARD);
                        pGUI.updateCoordinates();
                }
            }

        try {
            prova.update(gameContainer, stateBasedGame, i);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        pGUI.updateOnEachFrame(i);

    }
}

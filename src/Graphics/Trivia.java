package Graphics;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Trivia extends BasicGameState {
    Image backgroundMap, currentDie;
    DieGUI d;
    Map map;
    Player p;
    PlayerGUI pGUI;
    Pedina piece;
    boolean moved = false;
    Prova prova = new Prova(2);
    Image g;
    public String mouse= "No input";
    Boolean b=false;

    public Trivia(int id) throws Exception {
    }

    public int getID() {
        return 1;
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
        d = new DieGUI();
        currentDie = d.getCurrentDie();
        piece=new Pedina(up,dwn,lft,rght,35,35);
        pGUI=new PlayerGUI(p,piece);
        pGUI.getPedina().getCurrentImage().stop();
        g = new Image("res/buttons/blue_button02.png");
        prova.init(gameContainer, stateBasedGame);

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawString(mouse, 1100, 800);
        //backgroundMap=backgroundMap.getScaledCopy(gameContainer.getHeight(),gameContainer.getHeight()); //ridimensiona l'immagine a seconda della dim dello schermo
        graphics.drawImage(backgroundMap,0,0);
        pGUI.getPedina().getCurrentImage().draw(pGUI.getxUpdate(),pGUI.getyUpdate());
        currentDie.draw(1000,500);
        g.draw(1000,500);
        //d.getCurrentDie().draw(1000,500);
        /*if(pGUI.ready){
            prova.render(gameContainer, stateBasedGame, graphics);
        }*/
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) {
        float xpos=Mouse.getX();
        float ypos=Mouse.getY();

        mouse="Mouse position x:"+xpos+ " y: "+ypos;
        Input input=gameContainer.getInput();

        if(input.isKeyPressed(Input.KEY_W)){//vai indietro
            pGUI.clicked=true;
            b=true;
            int diceN=d.setCurrentDie();
            currentDie = d.getCurrentDie();
            //System.out.println("DADO: "+diceN);
            p.update(diceN,Direction.BACK);
            pGUI.updateCoordinates();
        }
        if(input.isKeyPressed(Input.KEY_S)){ //vai avanti
            moved = true;
            b=true;
            int diceN=d.setCurrentDie();
            currentDie = d.getCurrentDie();
            //System.out.println("DADO: "+diceN);
            p.update(diceN,Direction.FORWARD);
            pGUI.updateCoordinates();
        }

        try {
            prova.update(gameContainer, stateBasedGame, i);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        pGUI.updateOnEachFrame(i);

    }
}

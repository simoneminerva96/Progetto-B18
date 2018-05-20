package Graphics;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

public class Trivia extends BasicGameState {
    private Image backgroundMap, currentDie, back, forward, background;
    private Image rydia, ceodore, kain, luca;
    private Image playerBack1, playerBack2, playerBack3, playerBack4;
    private DieGUI d;
    private Map map;
    private Player p, p1, p2, p3;
    private ArrayList<PlayerGUI> pGUI;
    private Pedina piece, piece1, piece2, piece3;
    private String mouse= "No input";
    private Image launch;
    private boolean launched = false;
    private boolean first = false;
    private int diceN = 0;
    private TurnMaster turn;
    private int index = 0;
    private Domanda prova = new Domanda(6);
    private Escape esc = new Escape(7);

    public Trivia(int id) {
        turn = new TurnMaster();
        pGUI = new ArrayList<PlayerGUI>();
        pGUI.clear();
    }

    public int getID() { return 5; }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        playerBack1 = new Image("res/backgrounds/PlayerBackgrounds/SfondoGiocatore.png");
        playerBack2 = new Image("res/backgrounds/PlayerBackgrounds/sfondoGiocatore(plus).png");
        playerBack3 = new Image("res/backgrounds/PlayerBackgrounds/SfondoGiocatore2.png");
        playerBack4 = new Image("res/backgrounds/PlayerBackgrounds/SfondoGiocatore3.png");
        map = new Map(20,20,35);
        backgroundMap = new Image("res/map/Tabella.png");
        p = new Player("ONE",1,map);
        p1 = new Player("TWO",2, map);
        p2 = new Player("THREE", 3, map);
        p3 = new Player("FOUR", 4, map);
        back = new Image("res/buttons/Frecce/freccia1.png");
        forward = new Image("res/buttons/Frecce/freccia2.png");
        d = new DieGUI();

        piece=new Pedina("res/char/FFIV/Rydia/rydiaup.png","res/char/FFIV/Rydia/Rydiadwn.png","res/char/FFIV/Rydia/rydiasx.png","res/char/FFIV/Rydia/rydiadx.png",35,35);
        piece1 = new Pedina("res/char/FFIV/Ceodore/ced_up.png", "res/char/FFIV/Ceodore/ced_dwn.png", "res/char/FFIV/Ceodore/ced_lft.png", "res/char/FFIV/Ceodore/ced_rht.png", 35,35 );
        piece2 = new Pedina("res/char/FFIV/Kain/kainup.png","res/char/FFIV/Kain/kaindwn.png", "res/char/FFIV/Kain/kainsx.png", "res/char/FFIV/Kain/kaindx.png", 35, 35);
        piece3 = new Pedina("res/char/FFIV/Luca/lucaup.png", "res/char/FFIV/Luca/lucadwn.png", "res/char/FFIV/Luca/lucasx.png", "res/char/FFIV/Luca/lucadx.png", 35, 35);

        pGUI.add(0, new PlayerGUI(p, piece));
        pGUI.add(1, new PlayerGUI(p1,piece1));
        pGUI.add(2, new PlayerGUI(p2, piece2));
        pGUI.add(3, new PlayerGUI(p3, piece3));

        for (PlayerGUI p: pGUI) {
           p.getPedina().getCurrentImage().stop();
        }

        rydia = new Image ("res/char/rydia.png");
        ceodore = new Image("res/char/ceodore.png");
        kain = new Image("res/char/kain.png");
        luca = new Image("res/char/luca.png");
        background = new Image("res/backgrounds/green_landscape (copia).png");
        launch = new Image("res/buttons/Button_Launch/Button_Login_02.png");

        prova.init(gameContainer, stateBasedGame);
        esc.init(gameContainer, stateBasedGame);

        turn.addPlayer(p);
        turn.addPlayer(p1);
        turn.addPlayer(p2);
        turn.addPlayer(p3);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        background.draw(0,0);
        backgroundMap.draw(0,0);
        for (PlayerGUI p: pGUI){
            p.getPedina().getCurrentImage().draw(p.getxUpdate(),p.getyUpdate());
        }

        if (launched) { currentDie.draw(1200,575); }
        back.draw(750,575);
        forward.draw(850,575);
        if(pGUI.get(index).isReady()){
            try {
                prova.render(gameContainer, stateBasedGame, graphics);
                if (prova.isCaso()){
                    pGUI.get(index).setReady(false);
                    launched = false;
                }
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
        graphics.drawImage(playerBack1, 750, 30);
        graphics.drawImage(playerBack2, 1050, 30);
        graphics.drawImage(playerBack3, 750, 130);
        graphics.drawImage(playerBack4, 1050, 130);
        rydia.draw(750,30);
        ceodore.draw(1050,30);
        kain.draw(750,130);
        luca.draw(1050,130);
        graphics.drawString(p.getName(), 850,30);
        graphics.drawString(p1.getName(), 1150, 30);
        graphics.drawString(p2.getName(), 850, 130);
        graphics.drawString(p3.getName(), 1150, 130);
        launch.draw(990,580);

        if (esc.isQuit()){
            try {
                esc.render(gameContainer,stateBasedGame,graphics);
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


        if (xpos>990 && xpos<1130 && ypos>55 && ypos<120){
            if(input.isMousePressed(0)) {
                if(index==3) {
                    first = false;
                    index =0;
                    for (PlayerGUI p: pGUI){
                        p.setClicked(false);
                    }
                }
                diceN = d.setCurrentDie();
                currentDie = d.getCurrentDie();
                launched = true;
                prova.setAnswered(false);
                prova.setEsito(false);
                if (first) {
                    index++;
                }
            }
        }

        if (xpos>870 && xpos<930 && ypos>46 && ypos<109) {
            if (input.isMousePressed(0)) { //vai indietro
                if (launched) {
                    turn.nextPlayer(index, diceN, pGUI.get(index), Direction.BACK);
                    if (index==0) first = true;

                    }
                }
            }
            if (xpos>770 && xpos<830 && ypos>46 && ypos<109) { //vai avanti
                if (input.isMousePressed(0)) {
                    if (launched) {
                        turn.nextPlayer(index, diceN, pGUI.get(index), Direction.FORWARD);
                        if (index==0) first = true;
                    }
                }
        }

        try {
            prova.update(gameContainer, stateBasedGame, i);
            esc.update(gameContainer, stateBasedGame, i);
            } catch (SlickException e) {
            e.printStackTrace();
            }
            pGUI.get(index).updateOnEachFrame(i);

        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            esc.setQuit(true);
        }
    }

}

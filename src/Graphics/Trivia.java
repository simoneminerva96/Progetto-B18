package Graphics;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Trivia extends BasicGameState {
    private Image backgroundMap, currentDie, back, forward, background;
    private Image rydia, ceodore, kain, luca;
    private Image playerBack1, playerBack2, playerBack3, playerBack4;
    private DieGUI d;
    private Map map;
    private Player p;
    private PlayerGUI pGUI;
    private Pedina piece;
    private boolean moved = false;
    private Domanda prova = new Domanda(6);
    private String mouse= "No input";
    private Boolean b=false;
    String dwn="res/char/FFIV/Rydia/Rydiadwn.png";
    String lft="res/char/FFIV/Rydia/rydiasx.png";
    String rght="res/char/FFIV/Rydia/rydiadx.png";
    String up="res/char/FFIV/Rydia/rydiaup.png";

    public Trivia(int id) { }

    public int getID() {
        return 5;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        playerBack1 = new Image("res/backgrounds/PlayerBackgrounds/SfondoGiocatore.png");
        playerBack2 = new Image("res/backgrounds/PlayerBackgrounds/sfondoGiocatore(plus).png");
        playerBack3 = new Image("res/backgrounds/PlayerBackgrounds/SfondoGiocatore2.png");
        playerBack4 = new Image("res/backgrounds/PlayerBackgrounds/SfondoGiocatore3.png");
        map = new Map(20,20,35);
        backgroundMap = new Image("res/map/Tabella.png");
        p = new Player("ONE",3,map);
        back = new Image("res/buttons/Frecce/freccia1.png");
        forward = new Image("res/buttons/Frecce/freccia2.png");
        d = new DieGUI();
        currentDie = d.getCurrentDie();
        piece=new Pedina(up,dwn,lft,rght,35,35);
        pGUI=new PlayerGUI(p,piece);
        pGUI.getPedina().getCurrentImage().stop();
        prova.init(gameContainer, stateBasedGame);
        rydia = new Image ("res/char/rydia.png");
        ceodore = new Image("res/char/ceodore.png");
        kain = new Image("res/char/kain.png");
        luca = new Image("res/char/luca.png");
        background = new Image("res/backgrounds/green_landscape.png");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        //graphics.drawString(mouse, 900, 650);
        graphics.drawImage(background, 0,0);
        graphics.drawImage(backgroundMap,0,0);
        pGUI.getPedina().getCurrentImage().draw(pGUI.getxUpdate(),pGUI.getyUpdate());
        currentDie.draw(1100,550);
        back.draw(800,550);
        forward.draw(900,550);
        if(pGUI.isReady()){
            try {
                prova.render(gameContainer, stateBasedGame, graphics);
                if (prova.isCaso()){
                    pGUI.setReady(false);
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
        graphics.drawString(p.getName(), 800,30);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) {
        float xpos=Mouse.getX();
        float ypos=Mouse.getY();

        mouse="Mouse position x:"+xpos+ " y: "+ypos;
        Input input=gameContainer.getInput();

            if (xpos>916 && xpos<983 && ypos>48 && ypos<147) {
                if (input.isMousePressed(0)) { //vai indietro
                    pGUI.setReady(true);
                    b = true;
                    int diceN = d.setCurrentDie();
                    currentDie = d.getCurrentDie();
                    p.update(diceN, Direction.BACK);
                    pGUI.updateCoordinates();
                    prova.setAnswered(false);
                    prova.setEsito(false);
                }
            }
            if (xpos>815 && xpos<880 && ypos>48 && ypos<147) { //vai avanti
                if (input.isMousePressed(0)) {
                    pGUI.setClicked(true);
                    moved = true;
                    b = true;
                    int diceN = d.setCurrentDie();
                    currentDie = d.getCurrentDie();
                    p.update(diceN, Direction.FORWARD);
                    pGUI.updateCoordinates();
                    prova.setAnswered(false);
                    prova.setEsito(false);
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

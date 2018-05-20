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
    private Domanda prova = new Domanda(6);
    private String mouse= "No input";
    private String dwn="res/char/FFIV/Rydia/Rydiadwn.png";
    private String lft="res/char/FFIV/Rydia/rydiasx.png";
    private String rght="res/char/FFIV/Rydia/rydiadx.png";
    private String up="res/char/FFIV/Rydia/rydiaup.png";
    private Image launch;
    private boolean launched = false;
    int diceN = 0;

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
        launch = new Image("res/buttons/Button_Launch/Button_Login_02.png");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        //graphics.drawString(mouse, 900, 650);
        background.draw(0,0);
        backgroundMap.draw(0,0);
        pGUI.getPedina().getCurrentImage().draw(pGUI.getxUpdate(),pGUI.getyUpdate());
        if (launched) {
            currentDie.draw(1200,575);
        }
        back.draw(750,575);
        forward.draw(850,575);
        if(pGUI.isReady()){
            try {
                prova.render(gameContainer, stateBasedGame, graphics);
                if (prova.isCaso()){
                    pGUI.setReady(false);
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
        graphics.drawString(p.getName(), 800,30);
        launch.draw(990,580);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) {
        float xpos=Mouse.getX();
        float ypos=Mouse.getY();

        mouse="Mouse position x:"+xpos+ " y: "+ypos;
        Input input=gameContainer.getInput();

        if (xpos>990 && xpos<1130 && ypos>55 && ypos<120){
            if(input.isMousePressed(0)) {
                diceN = d.setCurrentDie();
                currentDie = d.getCurrentDie();
                launched = true;
                prova.setAnswered(false);
                prova.setEsito(false);
                pGUI.setClicked(false);
            }
        }

        if (xpos>870 && xpos<930 && ypos>46 && ypos<109) {
            if (input.isMousePressed(0)) { //vai indietro
                if (launched) {
                        pGUI.setClicked(true);
                        p.update(diceN, Direction.BACK);
                        pGUI.updateCoordinates();
                    }
                }
            }
            if (xpos>770 && xpos<830 && ypos>46 && ypos<109) { //vai avanti
                if (input.isMousePressed(0)) {
                    if (launched) {
                        pGUI.setClicked(true);
                        p.update(diceN, Direction.FORWARD);
                        pGUI.updateCoordinates();
                    }
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

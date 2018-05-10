package Graphics;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Play extends BasicGameState {
    static final int DIM_CASELLA = 15;
    Animation pacman, movingUp, movingDown, movingLeft, movingRight;
    Image worldMap, freccia, freccia2, freccia3, freccia4;
    boolean quit = false;
    boolean q = false;
    int [] duration = {200,200};
    float pacPositionX = 770;
    float pacPositionY = 608;
    Domanda p = new Domanda(2);

    public Play (int state) {
    }

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        worldMap = new Image("Immagini/sfondo.png");
        freccia = new Image ("Immagini/freccia.png");
        freccia2 = new Image ("Immagini/freccia2.png");
        freccia3 = new Image("Immagini/freccia3.png");
        freccia4 = new Image ("Immagini/freccia4.png");
        Image[] walkUp = {new Image("Immagini/blinky_up1.png"), new Image("Immagini/blinky_up2.png")};
        Image[] walkDown = {new Image("Immagini/blinky_down1.png"), new Image("Immagini/blinky_down2.png")};
        Image[] walkLeft = {new Image("Immagini/blinky_left1.png"), new Image("Immagini/blinky_left2.png")};
        Image[] walkRight = {new Image("Immagini/blinky_right1.png"), new Image("Immagini/blinky_right2.png")};
        movingUp = new Animation(walkUp, duration, true);
        movingDown = new Animation(walkDown, duration, true);
        movingLeft = new Animation(walkLeft, duration, true);
        movingRight = new Animation(walkRight, duration, true);
        pacman = movingDown;
        p.init(gc,sbg);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
       worldMap.draw(185, 27);
       freccia.draw(840,500);
       freccia2.draw(900, 500);
       freccia3.draw(870, 460);
       freccia4.draw(870, 540);
       pacman.draw(pacPositionX, pacPositionY);
       g.drawString("x:"+ pacPositionX + "\ny:" +pacPositionY, 0,300);

       if (quit == true){
           g.drawString("Resume (R)", 250,330);
           g.drawString("Main Menu (M)", 250,350);
           g.drawString("Quit game (Q)", 250,370);
           if (quit == false)
               g.clear();
       }

       if (q == true){
           p.render(gc,sbg,g);
       }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();
        int posX = Mouse.getX();
        int posY = Mouse.getY();
        //System.out.println("x: "+posX+ " y: "+posY);

        if (input.isKeyDown(Input.KEY_L)){
            if (questionAndAnswers.isAnswered() == false) {
                sbg.enterState(2);
            }
        }
        if (input.isKeyDown(Input.KEY_G)){
            q = true;
        }

        if (input.isKeyDown(Input.KEY_W)){
            pacPositionX -= .1f * delta;
            if (pacPositionX<=200){
                pacPositionX = 200;
                pacPositionY -= .1f * delta;
            }
        }

        // freccia sinistra
        if (posX>858 && posX<885 && posY>160 && posY<190){
            if (Mouse.isButtonDown(0)){
                pacPositionX -= 1 * DIM_CASELLA;
                if (pacPositionX<187) {
                    pacPositionX += 1 * DIM_CASELLA;
                }
            }
        }

        // freccia destra
        if (posX>915 && posX<935 && posY>160 && posY<190){
            if (Mouse.isButtonDown(0)){
                pacPositionX += 1 * DIM_CASELLA;
                if (pacPositionX>800) {
                    pacPositionX -= 1 * DIM_CASELLA;
                }
            }
        }

        // freccia su
        if (posX>875 && posX<910 && posY>205 && posY<225){
            if (Mouse.isButtonDown(0)){
                pacPositionY -= 1 * DIM_CASELLA;
                if (pacPositionY<28) {
                    pacPositionY += 1 * DIM_CASELLA;
                }
            }
        }

        // freccia giù
        if (posX>875 && posX<910 && posY>120 && posY<145){
            if (Mouse.isButtonDown(0)){
                pacPositionY += 1 * DIM_CASELLA;
                if (pacPositionY>643) {
                    pacPositionY -= 1 * DIM_CASELLA;
                }
            }
        }

        if (input.isKeyDown(Input.KEY_ESCAPE)){
            quit = true;
        }
        if (quit == true) {
            if (input.isKeyDown(Input.KEY_R)){
                quit = false;
            }
            if (input.isKeyDown(Input.KEY_M)){
                sbg.enterState(0);
            }
            if (input.isKeyDown(Input.KEY_Q)){
                System.exit(0);
            }
        }

    }
}

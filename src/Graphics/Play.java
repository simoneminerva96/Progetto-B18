package Graphics;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Play extends BasicGameState {
    Animation pacman, movingUp, movingDown, movingLeft, movingRight;
    Image worldMap;
    boolean quit = false;
    int [] duration = {200,200};
    float pacPositionX = 770;
    float pacPositionY = 608;

    public Play (int state) {
    }

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        worldMap = new Image("Immagini/sfondo.png");
        Image[] walkUp = {new Image("Immagini/blinky_up1.png"), new Image("Immagini/blinky_up2.png")};
        Image[] walkDown = {new Image("Immagini/blinky_down1.png"), new Image("Immagini/blinky_down2.png")};
        Image[] walkLeft = {new Image("Immagini/blinky_left1.png"), new Image("Immagini/blinky_left2.png")};
        Image[] walkRight = {new Image("Immagini/blinky_right1.png"), new Image("Immagini/blinky_right2.png")};
        movingUp = new Animation(walkUp, duration, true);
        movingDown = new Animation(walkDown, duration, true);
        movingLeft = new Animation(walkLeft, duration, true);
        movingRight = new Animation(walkRight, duration, true);
        pacman = movingDown;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
       worldMap.draw(185, 27);
       pacman.draw(pacPositionX, pacPositionY);
       g.drawString("x:"+ pacPositionX + "\ny:" +pacPositionY, 0,300);


       if (quit == true){
           g.drawString("Resume (R)", 250,330);
           g.drawString("Main Menu (M)", 250,350);
           g.drawString("Quit game (Q)", 250,370);
           if (quit == false)
               g.clear();
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

        if (pacPositionX>686 && pacPositionX<743) {
            if (questionAndAnswers.isAnswered() == false) {
                sbg.enterState(2);
            }
        }
        if (input.isKeyDown(Input.KEY_UP)){
            pacman = movingUp;
            pacPositionY -= delta * .1f;
            if (pacPositionY<28) {
                pacPositionY += delta * .1f;
            }
        }

        if (input.isKeyDown(Input.KEY_DOWN)){
            pacman = movingDown;
            pacPositionY += delta * .1f;
            if (pacPositionY>643){
                pacPositionY -= delta * .1f;
            }
        }

        if (input.isKeyDown(Input.KEY_LEFT)){
            pacman = movingLeft;
                pacPositionX -= delta * .1f;
            if (pacPositionX<187){
                pacPositionX += delta * .1f;
            }
        }

        if (input.isKeyDown(Input.KEY_RIGHT)){
            pacman = movingRight;
            pacPositionX += delta * .1f;
            if (pacPositionX>800){
                pacPositionX -= delta * .1f;
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

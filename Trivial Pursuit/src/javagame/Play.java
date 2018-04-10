package javagame;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Play extends BasicGameState {

    Animation pacman, movingUp, movingDown, movingLeft, movingRight;
    Image worldMap;
    boolean quit = false;
    int [] duration = {200,200};
    float pacPositionX = 260;
    float pacPositionY = 260;
    //float shiftX = pacPositionX + 260;
    //float shiftY = pacPositionY + 260;


    public Play (int state) {
    }

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        worldMap = new Image("res/sfondo.png");
        Image[] walkUp = {new Image("res/blinky_up1.png"), new Image("res/blinky_up2.png")};
        Image[] walkDown = {new Image("res/blinky_down1.png"), new Image("res/blinky_down2.png")};
        Image[] walkLeft = {new Image("res/blinky_left1.png"), new Image("res/blinky_left2.png")};
        Image[] walkRight = {new Image("res/blinky_right1.png"), new Image("res/blinky_right2.png")};

        movingUp = new Animation(walkUp, duration, false);
        movingDown = new Animation(walkDown, duration, false);
        movingLeft = new Animation(walkLeft, duration, false);
        movingRight = new Animation(walkRight, duration, false);
        pacman = movingDown;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
       worldMap.draw(0, 0);
       pacman.draw(pacPositionX, pacPositionY);
       g.drawString("Blinky x position:"+ pacPositionX + "\nBlinky y position: " +pacPositionY, 0,300);

       if (quit== true){
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

        /* if (input.isKeyDown(Input.KEY_D)){
            pacPositionX -= 2*3;
        } */
        if (input.isKeyDown(Input.KEY_UP)){
            pacman = movingUp;
            pacPositionY -= delta * .1f;
            if (pacPositionX>13 && pacPositionX<255) {
                pacPositionY += delta * .1f;
            }
            if (pacPositionY<0) {
                pacPositionY += delta * .1f;
            }
        }

        if (input.isKeyDown(Input.KEY_DOWN)){
            pacman = movingDown;
            pacPositionY += delta * .1f;
            if (pacPositionX>13 && pacPositionX<255) {
                pacPositionY -= delta * .1f;
            }
            if (pacPositionY>260){
                pacPositionY -= delta * .1f;
            }
        }

        if (input.isKeyDown(Input.KEY_LEFT)){
            pacman = movingLeft;
                pacPositionX -= delta * .1f;
            if (pacPositionY<250 && pacPositionY>14) {
                pacPositionX += delta * .1f;
            }
            if (pacPositionX<0){
                pacPositionX += delta * .1f;
            }
        }

        if (input.isKeyDown(Input.KEY_RIGHT)){
            pacman = movingRight;
            pacPositionX += delta * .1f;
            if (pacPositionY<255 && pacPositionY>7 && pacPositionX>5) {
                pacPositionX -= delta * .1f;
            }
            if (pacPositionX>260){
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

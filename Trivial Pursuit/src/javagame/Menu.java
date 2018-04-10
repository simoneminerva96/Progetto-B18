package javagame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState {
    Image playNow;
    Image exitGame;

    public Menu (int state){

    }
    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        playNow = new Image("res/play.png");
        exitGame = new Image("res/exit.png");

    }
    // we need to draw everything in render, it's like a paint brush
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawString("Welcome to Trivial Pursuit",50,50);
        playNow.draw(100,100);
        exitGame.draw(100,200);
    }
    // I use this when things are gonna change
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        int posX = Mouse.getX();
        int posY = Mouse.getY();
        //play now button
        if ((posX>90 && posX<215)&&(posY>199 && posY<300)){
            if (Mouse.isButtonDown(0)){
                sbg.enterState(1);
            }
        }
        // exit now button
        if ((posX>90 && posX<215)&&(posY>120 && posY<200)){
            if (Mouse.isButtonDown(0)){
                System.exit(0);
            }
        }
    }
}

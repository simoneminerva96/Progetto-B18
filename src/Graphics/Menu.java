package Graphics;


import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.BlobbyTransition;

public class Menu extends BasicGameState {
    Image playNow;
    Image exitGame;
    Music music;

    public Menu (int state){

    }
    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        playNow = new Image("Immagini/play.png");
        exitGame = new Image("Immagini/exit.png");
        music = new Music("Immagini/Wallpaper.wav");
        music.loop();
    }
    // we need to draw everything in render, it's like a paint brush
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawString("Welcome to Trivial Pursuit",400,200);
        playNow.draw(450,300);
        exitGame.draw(450,400);
    }
    // I use this when things are gonna change
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        int posX = Mouse.getX();
        int posY = Mouse.getY();
        //System.out.println("x: "+posX+ " y: "+posY);
        //play now button
        if ((posX>436 && posX<562)&&(posY>304 && posY<412)){
            if (Mouse.isButtonDown(0)){
                sbg.enterState(1, null, new BlobbyTransition());
            }
        }
        // exit now button
        if ((posX>436 && posX<562)&&(posY>305 && posY<224)){
            if (Mouse.isButtonDown(0)){
                System.exit(0);
            }
        }
    }
}

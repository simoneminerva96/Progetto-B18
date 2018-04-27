package javagame.com.stefano.rpg;

import javagame.Background;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


public class Core extends StateBasedGame{

    public static final String gamename= "Ham Blaster!";
    public static final int bckg=0;


    public Core(String gamename) {
        super(gamename);
        this.addState(new Background(bckg));
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        //gamecontainer main game window loop; inizializza schermate del gioco
        this.getState(bckg).init(gameContainer,this);
        this.enterState(bckg);
    }

    public static void main(String[] args) throws Exception {
        AppGameContainer appgc;
        try{
            appgc=new AppGameContainer(new javagame.Game(gamename));
            appgc.setDisplayMode(1280,720,false);
            appgc.start();
        }catch(SlickException e){
            e.printStackTrace();
        }
    }


}


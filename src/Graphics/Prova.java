package Graphics;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Prova extends BasicGameState {
    //Image torna;
    boolean answered = false;
    boolean end = false;

    public Prova (int state){

    }
    @Override
    public int getID() {
        return 2;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        //torna = new Image ("Immagini/freccia.png");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
            graphics.drawString(questionAndAnswers.question, 1100, 350);
            graphics.drawString(questionAndAnswers.a.answer, 1100, 450);
            graphics.drawString(questionAndAnswers.a1.answer, 1100, 500);
            graphics.drawString(questionAndAnswers.a2.answer, 1100, 550);
            graphics.drawString(questionAndAnswers.a3.answer, 1100, 600);
            //torna.draw(900,400);

            if (answered == true) {
                questionAndAnswers.setAnswered(true);
                if (end == true) {
                    graphics.drawString("Risposta esatta!!", 500, 500);
                } else {
                    graphics.drawString("Risposta sbagliata buuu", 500, 500);
                }
            }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gameContainer.getInput();
        int posX = Mouse.getX();
        int posY = Mouse.getY();

        //System.out.println("x: "+posX+ " y: "+posY);
        // freccia torna indietro
        if (posX>305 && posX<348 && posY>349 && posY<394){
            if (Mouse.isButtonDown(0)){
                if (answered == true) {
                    stateBasedGame.enterState(1, new FadeOutTransition(), new FadeInTransition());
                }
            }
        }

        if (posX>1100 && posX<1150){
            if (posY<538 && posY>502) {
                if (Mouse.isButtonDown(0) && answered == false) {
                    end = questionAndAnswers.a.esito;
                    answered = true;
                }
            }
            if (posY<510 && posY>452) {
                if (Mouse.isButtonDown(0) && answered == false) {
                    end = questionAndAnswers.a1.esito;
                    answered = true;
                }
            }
            if (posY<455 && posY>400) {
                if (Mouse.isButtonDown(0) && answered == false) {
                    end = questionAndAnswers.a2.esito;
                    answered = true;
                }
            }

            if (posY<410 && posY>349) {
                if (Mouse.isButtonDown(0) && answered == false){
                    end = questionAndAnswers.a3.esito;
                    answered = true;
                }
            }
        }

    }
}

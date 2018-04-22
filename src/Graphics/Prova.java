package Graphics;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Prova extends BasicGameState {
    Image torna;
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
        torna = new Image ("Immagini/freccia.png");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
            graphics.drawString(questionAndAnswers.question, 100, 100);
            graphics.drawString(questionAndAnswers.a.answer, 100, 200);
            graphics.drawString(questionAndAnswers.a1.answer, 100, 300);
            graphics.drawString(questionAndAnswers.a2.answer, 100, 400);
            graphics.drawString(questionAndAnswers.a3.answer, 100, 500);
            torna.draw(300,300);

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

        if (posX>100 && posX<165){
            if (posY<500 && posY>470) {
                if (Mouse.isButtonDown(0) && answered == false) {
                    end = questionAndAnswers.a.esito;
                    answered = true;
                }
            }
            if (posY<400 && posY>375) {
                if (Mouse.isButtonDown(0) && answered == false) {
                    end = questionAndAnswers.a1.esito;
                    answered = true;
                }
            }
            if (posY<300 && posY>275) {
                if (Mouse.isButtonDown(0) && answered == false) {
                    end = questionAndAnswers.a2.esito;
                    answered = true;
                }
            }

            if (posY<200 && posY>175) {
                if (Mouse.isButtonDown(0) && answered == false){
                    end = questionAndAnswers.a3.esito;
                    answered = true;
                }
            }
        }

    }
}

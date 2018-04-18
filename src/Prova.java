package javagame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Prova extends BasicGameState {
    boolean answered = false;
    boolean end = false;

    public Prova (int id){

    }
    @Override
    public int getID() {
        return 2;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        //graphics.drawString("Sei entrato in prova", 100, 100);
            graphics.drawString(questionAndAnswers.question, 100, 100);
            graphics.drawString(questionAndAnswers.a.answer, 100, 200);
            graphics.drawString(questionAndAnswers.a1.answer, 100, 300);
            graphics.drawString(questionAndAnswers.a2.answer, 100, 400);
            graphics.drawString(questionAndAnswers.a3.answer, 100, 500);

            if (answered == true) {
                if (end == true) {
                    graphics.clear();
                    graphics.drawString("Risposta esatta!!", 500, 500);
                } else {
                    graphics.clear();
                    graphics.drawString("Risposta sbagliata buuu", 500, 500);
                }

            }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gameContainer.getInput();
        int posX = Mouse.getX();
        int posY = Mouse.getY();

        if (input.isKeyDown(Input.KEY_W)){
            stateBasedGame.enterState(1);
        }
        //System.out.println("x: "+posX+ " y: "+posY);

        if (posX>100 && posX<165){
            if (posY<500 && posY>470) {
                if (Mouse.isButtonDown(0)) {
                    end = questionAndAnswers.a.esito;
                    answered = true;
                }
            }
            if (posY<400 && posY>375) {
                if (Mouse.isButtonDown(0)) {
                    end = questionAndAnswers.a1.esito;
                    answered = true;
                }
            }
            if (posY<300 && posY>275) {
                if (Mouse.isButtonDown(0)) {
                    end = questionAndAnswers.a2.esito;
                    answered = true;
                }
            }

            if (posY<200 && posY>175) {
                if (Mouse.isButtonDown(0)){
                    end = questionAndAnswers.a3.esito;
                    answered = true;
                }
            }

        }

    }
}

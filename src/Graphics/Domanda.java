package Graphics;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class Domanda extends BasicGameState {
    boolean answered = false;
    boolean esito = false;
    QuestionAndAnswers q = new QuestionAndAnswers();

    public Domanda(int state) {
    }

    @Override
    public int getID() {
        return 3;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawString(q.question, 850, 250);
        graphics.drawString(q.a.answer, 900, 300);
        graphics.drawString(q.a1.answer, 900, 350);
        graphics.drawString(q.a2.answer, 900, 400);
        graphics.drawString(q.a3.answer, 900, 450);

        if (answered == true) {
            q.setAnswered(true);
            if (esito == true) {
                graphics.drawString("Risposta esatta!!", 200, 500);
            } else {
                graphics.drawString("Risposta sbagliata buuu", 200, 500);
            }
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gameContainer.getInput();
        int posX = Mouse.getX();
        int posY = Mouse.getY();

        //System.out.println("x: "+posX+ " y: "+posY);

        if (posX>850 && posX<1135){
            if (posY<414 && posY>373) {
                if (Mouse.isButtonDown(0) && answered == false) {
                    esito = q.a.esito;
                    answered = true;
                }
            }
            if (posY<360 && posY>325) {
                if (Mouse.isButtonDown(0) && answered == false) {
                    esito = q.a1.esito;
                    answered = true;
                }
            }
            if (posY<305 && posY>275) {
                if (Mouse.isButtonDown(0) && answered == false) {
                    esito = q.a2.esito;
                    answered = true;
                }
            }

            if (posY<260 && posY>230) {
                if (Mouse.isButtonDown(0) && answered == false){
                    esito = q.a3.esito;
                    answered = true;
                }
            }
        }

    }

}

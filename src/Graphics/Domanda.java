package Graphics;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class Domanda extends BasicGameState {
    private String risposta1 = "risposta esatta";
    private String risposta2 = "risposta sbagliata";
    private boolean answered = false;
    private boolean esito = false;
    private boolean caso = false;
    private QuestionAndAnswers q = new QuestionAndAnswers();

    public Domanda(int state) {
    }

    @Override
    public int getID() {
        return 6;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawString(q.getQuestion(), 850, 250);
        graphics.drawString(q.getA().getAnswer(), 900, 300);
        graphics.drawString(q.getA1().getAnswer(), 900, 350);
        graphics.drawString(q.getA2().getAnswer(), 900, 400);
        graphics.drawString(q.getA3().getAnswer(), 900, 450);

        if (answered == true) {
            q.setAnswered(true);
            if (esito == true) {
                graphics.drawString(risposta1, 200, 500);
            } else {
                graphics.drawString(risposta2, 200, 500);
            }
            caso = true;
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
                    esito = q.getA().isEsito();
                    answered = true;
                }
            }
            if (posY<360 && posY>325) {
                if (Mouse.isButtonDown(0) && answered == false) {
                    esito = q.getA1().isEsito();
                    answered = true;
                }
            }
            if (posY<305 && posY>275) {
                if (Mouse.isButtonDown(0) && answered == false) {
                    esito = q.getA2().isEsito();
                    answered = true;
                }
            }

            if (posY<260 && posY>230) {
                if (Mouse.isButtonDown(0) && answered == false){
                    esito = q.getA3().isEsito();
                    answered = true;
                }
            }
        }

    }

    public boolean isCaso() {
        return caso;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public void setEsito(boolean esito) {
        this.esito = esito;
    }

    public boolean isEsito() {
        return esito;
    }
}

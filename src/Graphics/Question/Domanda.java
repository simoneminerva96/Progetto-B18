package Graphics.Question;

import GameClasses.Question;
import Interface.Controller;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Rita
 * La classe Domanda rappresenta lo state che viene renderizzato sul tabellone {@see Trivia} che
 * comprende la domanda e le relative 4 risposte. Inoltre, prevede dei controlli sulla gestione
 * della domanda corretta e di mostrare una stringa che informa se la risposta cliccata è corretta o
 * meno.
 *
 * - esito: flag in cui salvo l'esito della risposta che ho cliccato
 * - end: flag che indica se ho risposto e se ho ricevuto l'esito
 * - q: {@see QuestionAndAnswers} oggetto che contiene le domande e le risposte
 */

public class Domanda extends BasicGameState {
    private String risposta1 = "risposta esatta";
    private String risposta2 = "risposta sbagliata";
    private boolean esito = false;
    private boolean end = false;
    private Controller controller; //interfaccia controller per prelevare le domande dalla logica
    private Question question; //domanda estratta
    //private QuestionAndAnswers q;
    private boolean Answered=false;     //valore di default per ora

    public Domanda(int state) {
        /*
        q = new QuestionAndAnswers();
        q.setAnswer("Roma", true);
        q.setAnswer("Milano", false);
        q.setAnswer("Napoli", false);
        q.setAnswer("Firenze", false);
        */

    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public int getID() { return 6; }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
    }

    /**
     * Visualizzo nella grafica le domande e le risposte.
     * Se ho risposto, aggiorno il flag relativo. In base al valore di esito visualizzo risposta1 o
     * risposta2. Se ho risposto e ho visualizzato la stringa, end = true.
     */
    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        question=controller.getQuestion();
        graphics.drawString(question.getQuestion(), 850, 250);
        graphics.drawString(question.getAnswers().get(0).getAnswer(), 900, 300);
        graphics.drawString(question.getAnswers().get(1).getAnswer(), 900, 350);
        graphics.drawString(question.getAnswers().get(2).getAnswer(), 900, 400);
        graphics.drawString(question.getAnswers().get(3).getAnswer(), 900, 450);

        if (Answered) {
            if (esito == true) {
                graphics.drawString(risposta1, 200, 500);
            } else {
                graphics.drawString(risposta2, 200, 500);
            }
            end = true;
        }
    }

    /**
     * Controllo le coordinate delle risposte in cui l'utente clicca. In ogni caso, posso cliccare solo
     * se premo il tasto sinistro del mouse e se non ho già risposto. Una volta cliccato, aggiorno
     * il flag answered e mi salvo l'esito della risposta.
     */
    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gameContainer.getInput();
        int posX = Mouse.getX();
        int posY = Mouse.getY();

        //System.out.println("x: "+posX+ " y: "+posY);

        if (posX>850 && posX<1135){
            if (posY<414 && posY>373) {
                if (Mouse.isButtonDown(0) && Answered == false) {
                    esito = question.getAnswers().get(0).getCorrect();
                    Answered=true;
                }
            }
            if (posY<360 && posY>325) {
                if (Mouse.isButtonDown(0) && Answered == false) {
                    esito = question.getAnswers().get(1).getCorrect();
                    Answered=true;
                }
            }
            if (posY<305 && posY>275) {
                if (Mouse.isButtonDown(0) && Answered == false) {
                    esito = question.getAnswers().get(2).getCorrect();
                    Answered=true;
                }
            }

            if (posY<260 && posY>230) {
                if (Mouse.isButtonDown(0) && Answered == false){
                    esito = question.getAnswers().get(3).getCorrect();
                    Answered=true;
                }
            }
        }

    }

    public boolean isEnd() {
        return end;
    }

    public void setAnswered(boolean answered) {
        Answered=answered;
    }

    public void setEsito(boolean esito) {
        this.esito = esito;
    }

    public boolean isEsito() {
        return esito;
    }
}

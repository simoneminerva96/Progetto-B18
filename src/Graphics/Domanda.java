package Graphics;

import GameClasses.Question;
import Interface.Controller;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Rita
 * La classe Domanda rappresenta lo state che viene renderizzato sul tabellone {@see Trivia} che
 * comprende la domanda e le relative 4 risposte. Inoltre, prevede dei controlli sulla gestione
 * della domanda corretta e di mostrare una stringa che informa se la risposta cliccata è corretta o
 * meno.
 * - esito: flag in cui salvo l'esito della risposta che ho cliccato
 * - end: flag che indica se ho risposto e ricevuto l'esito
 * - controller: interfaccia controller con la logica per prelevare le domande
 * - answered: flag che indica se ho risposto o meno
 * - question: oggetto di tipo Question che contiene la domanda estratta
 */

public class Domanda extends BasicGameState {
    private String risposta1 = "Risposta esatta";
    private String risposta2 = "Risposta sbagliata";
    private boolean esito = false;
    private boolean end = false;
    private Controller controller;
    private Question question;
    private boolean answered = false;

    public Domanda(int state) { }

    @Override
    public int getID() { return 6; }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) {
    }

    /**
     * Visualizzo nella grafica le domande e le risposte.
     * Se ho risposto, aggiorno il flag relativo. In base al valore di esito visualizzo risposta1 o
     * risposta2. Se ho risposto e ho visualizzato la stringa, end = true.
     */
    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        question=controller.getQuestion();
        graphics.drawString(question.getQuestion(), 850, 250);
        graphics.drawString(question.getAnswers().get(0).getAnswer(), 900, 300);
        graphics.drawString(question.getAnswers().get(1).getAnswer(), 900, 350);
        graphics.drawString(question.getAnswers().get(2).getAnswer(), 900, 400);
        graphics.drawString(question.getAnswers().get(3).getAnswer(), 900, 450);

        if (answered) {
            if (esito) {
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
     * il flag answered e mi salvo l'esito della risposta utilizzando l'interfaccia controller.
     */
    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) {
        int posX = Mouse.getX();
        int posY = Mouse.getY();

        if (posX>850 && posX<1135){
            if (posY<414 && posY>373) {
                if (Mouse.isButtonDown(0) && !answered) {
                    esito = controller.answerQuestion(0);
                    answered = true;
                }
            }
            if (posY<360 && posY>325) {
                if (Mouse.isButtonDown(0) && !answered) {
                    esito = controller.answerQuestion(1);
                    answered = true;
                }
            }
            if (posY<305 && posY>275) {
                if (Mouse.isButtonDown(0) && !answered) {
                    esito = controller.answerQuestion(2);
                    answered = true;
                }
            }

            if (posY<260 && posY>230) {
                if (Mouse.isButtonDown(0) && !answered){
                    esito = controller.answerQuestion(3);
                    answered = true;
                }
            }
        }
    }

    public boolean isEnd() {
        return end;
    }

    public void setAnswered(boolean answered) {
        this.answered=answered;
    }

    public void setEsito(boolean esito) {
        this.esito = esito;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

}

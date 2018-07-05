package Client.Graphics;

import Server.GameClasses.Question;
import Client.Graphics.Fonts.TriviaFont;
import Server.GameClasses.Controller;
import org.lwjgl.input.Mouse;
import org.lwjgl.opencl.CL;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Rita
 * La classe Domanda rappresenta lo state che viene renderizzato sul tabellone {@see Trivia} che
 * comprende la domanda e le relative 4 risposte. Inoltre, prevede dei controlli sulla gestione
 * della domanda corretta e di mostrare una stringa che informa se la risposta cliccata è corretta o
 * meno.
 * - esito: flag in cui salvo l'esito della risposta che ho cliccato
 * - answered: flag che indica se ho risposto o meno
 * - question: oggetto di tipo Question che contiene la domanda estratta
 */

public class Domanda extends BasicGameState {
    private boolean esito = false;
    private boolean answered = false;
    private Question question;
    private TrueTypeFont fonx1;
    private TriviaFont f;
    private ClientInterface clientInterface;
    private boolean checkreceivedQuestion;

    public Domanda(ClientInterface clientInterface) {
        f = new TriviaFont();
        this.clientInterface = clientInterface;
    }

    @Override
    public int getID() { return 6; }

    public void setCheckreceivedQuestion(boolean check){
        this.checkreceivedQuestion=check;
    }
    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) {
        fonx1 = new TrueTypeFont(f.getFont().deriveFont(23f), false);
    }

    /**
     * Visualizzo nella grafica le domande e le risposte.
     * Se ho risposto, aggiorno il flag relativo. In base al valore di esito visualizzo risposta1 o
     * risposta2. Se ho risposto e ho visualizzato la stringa, end = true.
     */
    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        int x = 1100;
        int y = 350;

        if(!checkreceivedQuestion){
            question=clientInterface.getQuestion();
            checkreceivedQuestion=true;
        }

        if (question.getQuestion().length() > 20) {
            y += 20;
        }
        fonx1.drawString( x, y, question.getQuestion(), Color.black);
        fonx1.drawString(x+90,420, question.getAnswers().get(0).getAnswer(), Color.black);
        fonx1.drawString(x+90,480, question.getAnswers().get(1).getAnswer(), Color.black);
        fonx1.drawString(x+90,540, question.getAnswers().get(2).getAnswer(), Color.black);
        fonx1.drawString(x+90,600, question.getAnswers().get(3).getAnswer(), Color.black);
        if (answered) {
            if (esito) {
                fonx1.drawString(1190, 700, "RISPOSTA ESATTA!", Color.black);
            } else {
                fonx1.drawString(1190,700, "RISPOSTA SBAGLIATA!", Color.black);
            }
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

        if (posX>1105 && posX<1410){
            if (posY<604 && posY>542) {
                if (Mouse.isButtonDown(0) && !answered) {
                    clientInterface.sendindex(0);
                    esito = clientInterface.receiveOutcome();
                    answered = true;
                }
            }
            if (posY<527 && posY>486) {
                if (Mouse.isButtonDown(0) && !answered) {
                    clientInterface.sendindex(1);
                    esito = clientInterface.receiveOutcome();
                    answered = true;
                }
            }
            if (posY<472 && posY>430) {
                if (Mouse.isButtonDown(0) && !answered) {
                    clientInterface.sendindex(2);
                    esito=clientInterface.receiveOutcome();
                    answered = true;
                }
            }
            if (posY<410 && posY>372) {
                if (Mouse.isButtonDown(0) && !answered){
                    clientInterface.sendindex(3);
                    esito=clientInterface.receiveOutcome();
                    answered = true;
                }
            }
        }
    }

    public void reset(){
        esito = false;
        answered = false;
    }

    public boolean isEsito() {
        return esito;
    }
}

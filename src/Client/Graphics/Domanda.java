package Client.Graphics;

import Client.Graphics.ClientInteface.ClientInterface;
import Server.GameClasses.GameClasses.Question;
import Client.Graphics.Fonts.TriviaFont;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/** @author Rita
 * La classe Domanda rappresenta lo state che viene renderizzato sul tabellone che comprende la domanda e le relative
 * 4 risposte. Inoltre, prevede dei controlli sulla gestione della domanda corretta e di mostrare una stringa che
 * informa se la risposta cliccata è corretta o meno.
 * - esito: flag in cui salvo l'esito della risposta che ho cliccato
 * - answered: flag che indica se ho risposto o meno
 * - question: oggetto di tipo Question che contiene la domanda estratta
 * - clicked: flag che indica se posso inviare la direzione al server nella prossima mossa
 * - f, fonx1: font
 * - clientInterface: oggetto per comunicare con il server
 * - checkReceivedQuestion: flag che indica se ho già ricevuto l'informazione dal server o no
 * - clicked: flag che indica se ho cliccato sulla domanda
 * @see Trivia per il tabellone */

public class Domanda extends BasicGameState {
    private boolean esito = false;
    private boolean answered = false;
    private boolean clicked =true;
    private Question question;
    private TrueTypeFont fonx1;
    private TriviaFont f;
    private ClientInterface clientInterface;
    private boolean checkreceivedQuestion;

    Domanda(ClientInterface clientInterface) {
        f = new TriviaFont();
        this.clientInterface = clientInterface;
    }

    @Override
    public int getID() { return 6; }

    void setCheckreceivedQuestion(){
        this.checkreceivedQuestion= false;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) {
        fonx1 = new TrueTypeFont(f.getFont().deriveFont(23f), false);
    }

    /** Visualizzo nella grafica le domande e le risposte. Se ho risposto, aggiorno il flag relativo. In base al valore
     * di esito visualizzo la stringa appropriata. Se ho risposto ho cliccato e quindi clicked=true */
    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        if(!checkreceivedQuestion){
            question=clientInterface.getQuestion();
            checkreceivedQuestion=true;
        }
        drawQuestion();
        fonx1.drawString(1190,420, question.getAnswers().get(0).getAnswer(), Color.black);
        fonx1.drawString(1190,480, question.getAnswers().get(1).getAnswer(), Color.black);
        fonx1.drawString(1190,540, question.getAnswers().get(2).getAnswer(), Color.black);
        fonx1.drawString(1190,600, question.getAnswers().get(3).getAnswer(), Color.black);
        if (answered) {
            if (esito)
                fonx1.drawString(1190, 700, "RISPOSTA ESATTA!", Color.black);
            else
                fonx1.drawString(1190,700, "RISPOSTA SBAGLIATA!", Color.black);
        }
    }

    /** Controllo le coordinate delle risposte in cui l'utente clicca. In ogni caso, posso cliccare solo
     * se premo il tasto sinistro del mouse e se non ho già risposto. Una volta cliccato, aggiorno
     * il flag answered, clicked e mi salvo l'esito della risposta ricevuta dal server.*/
    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) {
        int posX = Mouse.getX();
        int posY = Mouse.getY();

        if (posX>1105 && posX<1410){
            if (posY<604 && posY>542) {
                if (Mouse.isButtonDown(0) && !answered)
                    updateInformation(0);
            }
            if (posY<527 && posY>486) {
                if (Mouse.isButtonDown(0) && !answered)
                   updateInformation(1);
            }
            if (posY<472 && posY>430) {
                if (Mouse.isButtonDown(0) && !answered)
                    updateInformation(2);
            }
            if (posY<410 && posY>372) {
                if (Mouse.isButtonDown(0) && !answered)
                    updateInformation(3);
            }
        }
    }

    private void updateInformation (int index) {
        clientInterface.sendindex(index);
        esito = clientInterface.receiveOutcome();
        answered = true;
        clicked = true;
    }

    private void drawQuestion(){
        if(question.getQuestion().length()>70) {
            String part1=question.getQuestion().substring(0,35);
            String part2=question.getQuestion().substring(35,70);
            String part3=question.getQuestion().substring(70);
            fonx1.drawString( 1100,310, part1+ "-", Color.black);
            fonx1.drawString( 1100,340, part2+"-", Color.black);
            fonx1.drawString( 1100,370, part3, Color.black);
        }
        else if(question.getQuestion().length() > 35 ){
            String part1=question.getQuestion().substring(0,35);
            String part2=question.getQuestion().substring(35);
            fonx1.drawString( 1100,340, part1+ "-", Color.black);
            fonx1.drawString( 1100,370, part2, Color.black);
        }
        else fonx1.drawString( 1100,340, question.getQuestion(), Color.black);
    }

    void reset(){
        esito = false;
        answered = false;
    }

    boolean isEsito() { return esito; }

    boolean isClicked() { return clicked; }

    void setClicked(boolean clicked) { this.clicked = clicked; }
}

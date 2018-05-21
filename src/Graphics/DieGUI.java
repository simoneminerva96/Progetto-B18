package Graphics;

import GameClasses.Die;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * @author Rita
 * La classe DieGUI rappresenta un'interfaccia tra la classe Die e Trivia.
 * - Image[] die: vettore di 6 immagini (facce dei dadi)
 * - Image currentDie: immagine attuale da visualizzare sul tabellone
 * - Die d: oggetto di tipo dado
 */

public class DieGUI {
    private Image[] die = new Image[6];
    private Image currentDie;
    private Die d = new Die();

    public DieGUI() {
        for (int i=0; i<6; i++){
            try {
                die[i] = new Image("res/die/Dado"+(i+1)+".png");
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
        currentDie = die[1];
    }

    public Image getCurrentDie() {
        return currentDie;
    }

    /**
     * Metodo che restituisce il numero del dado estratto e imposta currentDie in base a quest'ultimo.
     * @return result: numero del dado estratto
     */

    public int setCurrentDie() {
        int result;
        result = d.Launch();
        currentDie = die[result-1];
        return result;
    }
}

package Client.Graphics;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.io.Serializable;

/**
 * @author Rita
 * La classe DieGUI rappresenta un'interfaccia tra la classe Die e Trivia.
 * - Image[] die: vettore di 6 immagini (facce dei dadi)
 * - Image currentDie: immagine attuale da visualizzare sul tabellone
 */

public class DieGUI {
    private Image[] die = new Image[6];
    private Image currentDie;

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
     * Metodo che aggiorna la faccia del dado con il numero estratto nella logica
     */

    public void setCurrentDie(int result) {
        currentDie = die[result-1];
    }
}

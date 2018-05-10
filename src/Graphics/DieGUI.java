package Graphics;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class DieGUI {
    Image[] die = new Image[6];
    Image currentDie;
    Die d = new Die();

    public DieGUI() {
        for (int i=0; i<6; i++){
            try {
                die[i] = new Image("res/die/dado"+(i+1)+".png");
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
        currentDie = die[1];
    }

    public Image getCurrentDie() {
        return currentDie;
    }

    public int setCurrentDie() {
        int result;
        result = d.Launch();
        currentDie = die[result-1];
        return result;
    }
}

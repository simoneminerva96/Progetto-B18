package Interface;

import GameClasses.TrivialGame;

public class Controller {
    private TrivialGame tg;

    public Controller(){

    }

    public int getDiceValue(){
       return tg.throwDie();
    }
}

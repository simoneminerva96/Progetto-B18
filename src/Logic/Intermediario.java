package Logic;

import GameClasses.Die;

public class Intermediario {
    Die d;

    public Intermediario(){
        d = new Die();
    }

    public int generateNumber () {
        return d.Launch();
    }


}

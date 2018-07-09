package Server.GameClasses.GameClasses;

import Server.GameClasses.GameClasses.Categories;

/**
 * classe che corrisponde a uno spicchio, ottenibile rispondendo a una domanda finale
 * @author Ansaldi Jacopo <jacopo.ansaldi01@universitadipavia.it>
 */

public class Slice {
    private Categories category;

    public Slice(Categories category){
        this.category=category;
    }

    public Categories getCategory() {
        return category;
    }
}

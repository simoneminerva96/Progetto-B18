package GameClasses;

/**
 * classe che corrisponde a uno spicchio, ottenibile rispondendo a una domanda finale
 * @author Ansaldi Jacopo <jacopo.ansaldi01@universitadipavia.it>
 */

public class Slice {
    Categories category;

    public Slice(Categories category){
        this.category=category;
    }

    public Categories getCategory() {
        return category;
    }
}

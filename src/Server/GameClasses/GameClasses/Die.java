package Server.GameClasses.GameClasses;

import java.util.Random;
/**
 * Classe corrispondente a un dado
 * @author Ansaldi Jacopo <jacopo.ansaldi01@universitadipavia.it>
 */
public class Die {
    private Random generator;
    private int extractNumber;

    public Die(){
        generator=new Random();
    }

    /** Singolo lancio del dado
     * @return un intero compreso tra 1 e 6
     */

    public int Launch(){
        int min = 1;
        int max = 6 ;
        int range = ((max-min) + 1);

        extractNumber = generator.nextInt(range) + min;
        return extractNumber;
    }
}

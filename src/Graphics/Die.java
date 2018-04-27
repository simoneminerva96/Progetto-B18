package Graphics;

import java.util.Random;
/*
    classe corrispondente a un dado
 */
public class Die {
    private Random generator;
    public Die(){
        generator=new Random();
    }
    //singolo lancio del dado, ritorna un intero tra 1 e 6
    public int Launch(){
        int min = 1; // numero minimo
        int max=6 ; // numero massimo
        int range = ((max-min) + 1);
        return generator.nextInt(range) + min;
    }
}
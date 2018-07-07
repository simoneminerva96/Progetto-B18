package Tests;

import Server.GameClasses.Die;

public class TestDado {


    public static void main(String[] args) {
        Die dado = new Die();

        while (true){
            if(dado.Launch() < 6 || dado.Launch() > 1){
                System.out.println("ok, il dado funziona");
                break;
            }

            assert false : "Il lancio del dado ha dei problemi";
            break;

        }


    }
}

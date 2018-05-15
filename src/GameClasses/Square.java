package GameClasses;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *  Classe che corrisponde a una singola casella di gioco
 *  @author Ansaldi Jacopo <jacopo.ansaldi01@universitadipavia.it>
 */
public class Square {
    private Integer index;      //indice che identifica ogni casella, da 0 a 40, partendo dallo start(0) conteggiati in senso orario
    private ArrayList<Question> possibleQuestions;      //POSSIBILI DOMANDE

    public Square(Integer index){
        this.index=index;
        possibleQuestions=new ArrayList<Question>();
    }

    public Integer getIndex() {
        return index;
    }

    // metodo per inizializzare le possibili domande ( da prendere dal db quando inizializzo la partita)
    public void setPossibleQuestions(ArrayList<Question> possibleQuestions){
        this.possibleQuestions.addAll(possibleQuestions);
    }
    //metodo che esegue la domanda quando il giocatore arriva su questa casella
    public Boolean goOnIt(){
        Boolean check=false;
        Random questionChooser=new Random();
        //genero un numero casuale compreso tra 0 e il numero di domande disponibili
        int max=possibleQuestions.size() -1 ; // numero massimo
        int range = ((max) + 1);
        int indexOfQuestion =questionChooser.nextInt(range);
        // il numero generato corrisponderà all'indice della domanda da effettuare
        System.out.println("domanda di " + possibleQuestions.get(indexOfQuestion).getCategory() + ":");
        System.out.println(possibleQuestions.get(indexOfQuestion).getQuestion());
        System.out.println("1) " + possibleQuestions.get(indexOfQuestion).getAnswers().get(0).getAnswer());
        System.out.println("2) " + possibleQuestions.get(indexOfQuestion).getAnswers().get(1).getAnswer());
        System.out.println("3) " + possibleQuestions.get(indexOfQuestion).getAnswers().get(2).getAnswer());
        System.out.println("4) " + possibleQuestions.get(indexOfQuestion).getAnswers().get(3).getAnswer());
        Scanner sc=new Scanner(System.in);
        String answer="";
        while (!(answer.equalsIgnoreCase("1") ||answer.equalsIgnoreCase("2") ||answer.equalsIgnoreCase("3") ||answer.equalsIgnoreCase("4"))){
            answer=sc.next();
            if(!(answer.equalsIgnoreCase("1") ||answer.equalsIgnoreCase("2") ||answer.equalsIgnoreCase("3") ||answer.equalsIgnoreCase("4"))){
                System.out.println("inserisci un numero corrispondente alla risposta");
            }
        }
        Integer intAnswer=Integer.parseInt(answer) -1;
        check=possibleQuestions.get(indexOfQuestion).getAnswers().get(intAnswer).getCorrect();
        return check;
    }
}

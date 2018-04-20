package GameClasses;

import java.util.ArrayList;

/*
    Classe che corrisponde a una singola casella di gioco
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
        //DA IMPLEMENTARE
        return check;
    }
}

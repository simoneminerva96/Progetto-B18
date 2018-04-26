package GameClasses;

import java.util.ArrayList;
import java.util.Random;

/*
     classe corrispondente a un singolo giocatore che partecipa alla partita
 */
public class Player {
    private String nickname;
    private Piece chosenPiece;
    private int initialRollResult;
    private int actualPosition;
    private ArrayList<Slice> slicesObtained;

    public Player(String nickname){
        this.nickname=nickname;
        chosenPiece=null;        //quando viene istanziato un giocatore esso non ha ancora nessuna pedina associata
        initialRollResult=0;
        actualPosition=0;       //quando viene istanziato un giocatore viene inizializzato sulla casella iniziale(index=0)
        slicesObtained=new ArrayList<Slice>();
    }
    //metodo per ottenere uno spicchio
    public void obtainSlice(Categories category){
        //controllo se il gicoatore ha gia ottenuto lo spicchio di quella categoria
        Boolean check=false;    //variabile che indica se lo spicchio è gia presente
        for(int i=0;i<slicesObtained.size();i++){
            if(slicesObtained.get(i).getCategory().equals(category)){
                System.out.println("il giocatore ha gia ottenuto lo spicchio di questa categoria!\n");
                check=true;
            }
        }
        if(check==false){
            slicesObtained.add(new Slice(category));
        }
    }

    public int getInitialRollResult() {
        return initialRollResult;
    }

    public void setInitialRollResult(int initialRollResult) {
        this.initialRollResult = initialRollResult;
    }

    public String getNickname() {
        return nickname;
    }

    public Piece getChosenPiece() {
        return chosenPiece;
    }

    public void setChosenPiece(Piece chosenPiece) {
        this.chosenPiece = chosenPiece;
    }

    public int getActualPosition() {
        return actualPosition;
    }

    public void setActualPosition(int actualPosition) {
        this.actualPosition = actualPosition;
    }

    public ArrayList<Slice> getSlicesObtained() {
        return slicesObtained;
    }

    public void removeSlice(){
        if(slicesObtained.size()!=0){
            Random random=new Random();
            int max=slicesObtained.size() -1 ; // numero massimo
            int range = max + 1;
            int index= random.nextInt(range);
            slicesObtained.remove(index);
        }
    }
}

package GameClasses;
/*
    Classe che corrisponde a una singola casella di gioco
 */
public class Square {
    private Categories category;
    private Integer index;      //indice che identifica ogni casella, da 0 a 40, partendo dallo start(0) conteggiati in senso orario

    public Square(Integer index,Categories category){
        this.index=index;
        this.category=category;
    }

    public Categories getCategory() {
        return category;
    }

    public Integer getIndex() {
        return index;
    }
}

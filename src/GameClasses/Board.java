package GameClasses;

import GameClasses.Squares.*;
import ConnectionDB.ConnectionDB;

import java.sql.SQLException;
import java.util.ArrayList;
/*
    classe che corrisponde al tabellone di gioco
 */
public class Board {
    private ArrayList<Square> squares;
    private ConnectionDB connectionDB;
    // inserire le domande tramite il metodo della classe connesione db
    //MANCANO DA INSERIRE LE DOMANDE FINALI
    public Board() throws SQLException{
        connectionDB=new ConnectionDB();
        squares=new ArrayList<Square>();
        for(int i=0;i<40;i++){
            if(i==0) squares.add(new InitialSquare(i)); //casella start
            if(i==1 || i==11 || i==21 || i==31){
                squares.add(new GeographySquare(i));
                ArrayList<Question> possibleQuestionGeo=new ArrayList<Question>();
                possibleQuestionGeo.addAll(connectionDB.getQuestion("GEO"));
                squares.get(i).setPossibleQuestions(possibleQuestionGeo);
            }
            if(i==2 || i==12 || i==22 || i==32){
                squares.add(new ArtLicteratureSquare(i));
                ArrayList<Question> possibleQuestionArt=new ArrayList<Question>();
                possibleQuestionArt.addAll(connectionDB.getQuestion("ART"));
                squares.get(i).setPossibleQuestions(possibleQuestionArt);
            }
            if(i==3 || i==14 || i==24 || i==34) {
                squares.add(new ScienceSquare(i));
                ArrayList<Question> possibleQuestionSci=new ArrayList<Question>();
                possibleQuestionSci.addAll(connectionDB.getQuestion("SCI"));
                squares.get(i).setPossibleQuestions(possibleQuestionSci);
            }
            if(i==4 || i==16 || i==27 || i==36) {
                squares.add(new ActualitySquare(i));
                ArrayList<Question> possibleQuestionAtt=new ArrayList<Question>();
                possibleQuestionAtt.addAll(connectionDB.getQuestion("ATT"));
                squares.get(i).setPossibleQuestions(possibleQuestionAtt);
            }
            if(i==8 || i==18 || i==28 || i==38){
                squares.add(new SportSquare(i));
                ArrayList<Question> possibleQuestionSpo=new ArrayList<Question>();
                possibleQuestionSpo.addAll(connectionDB.getQuestion("SPO"));
                squares.get(i).setPossibleQuestions(possibleQuestionSpo);
            }
            if(i==6|| i==17 || i==26 || i==37) {
                squares.add(new ShowSquare(i));
                ArrayList<Question> possibleQuestionSpe=new ArrayList<Question>();
                possibleQuestionSpe.addAll(connectionDB.getQuestion("SPE"));
                squares.get(i).setPossibleQuestions(possibleQuestionSpe);
            }
            if(i==9 || i==19|| i==29 || i==39){
                squares.add(new HistorySquare(i));
                ArrayList<Question> possibleQuestionSto=new ArrayList<Question>();
                possibleQuestionSto.addAll(connectionDB.getQuestion("STO"));
                squares.get(i).setPossibleQuestions(possibleQuestionSto);
            }
            if(i==5){
                squares.add(new FinalQuestionSquare(i,Categories.Sport));
            }
            if(i==10){
                squares.add(new FinalQuestionSquare(i,Categories.ArteLetteratura));
            }
            if(i==15){
                squares.add(new FinalQuestionSquare(i,Categories.Spettacolo));
            }
            if(i==20){
                squares.add(new FinalQuestionSquare(i,Categories.Attualità));
            }
            if(i==25){
                squares.add(new FinalQuestionSquare(i,Categories.Storia));
            }
            if(i==30){
                squares.add(new FinalQuestionSquare(i,Categories.Scienze));
            }
            if(i==35){
                squares.add(new FinalQuestionSquare(i,Categories.Geografia));
            }
            if(i==7)squares.add(new BonusMalusSquare(i,"malus1"));
            if(i==13)squares.add(new BonusMalusSquare(i,"bonus1"));
            if(i==23)squares.add(new BonusMalusSquare(i,"bonus2"));
            if(i==33)squares.add(new BonusMalusSquare(i,"malus2"));
        }
    }
    //metodo che crea una domanda
    private Question createQuestion(String question,Categories category,String answ1,Boolean correct1,String answ2,Boolean correct2,String answ3,Boolean correct3,String answ4,Boolean correct4){
        ArrayList<Answer> answers=new ArrayList<Answer>();
        answers.add(new Answer(answ1,correct1));
        answers.add(new Answer(answ2,correct2));
        answers.add(new Answer(answ3,correct3));
        answers.add(new Answer(answ4,correct4));
        Question q=new Question(question,category,answers);
        return q;
    }
    public ArrayList<Square> getSquares() {
        return squares;
    }
}

package GameClasses;

import GameClasses.Squares.*;
import ConnectionDB.ConnectionDB;
import java.util.ArrayList;
/*
    classe che corrisponde al tabellone di gioco
 */
public class Board {
    private ArrayList<Square> squares;
    private ConnectionDB connectionDB;
    // inserire le domande tramite il metodo della classe connesione db
    // inserendo il codice corrispondente alla categoria e settando la categoria
    public Board(){
        connectionDB=new ConnectionDB();
        squares=new ArrayList<Square>();
        for(int i=0;i<40;i++){
            if(i==0) squares.add(new InitialSquare(i)); //casella start
            if(i==1 || i==11 || i==21 || i==31){
                squares.add(new GeographySquare(i));
                squares.get(i).setPossibleQuestions(createQuestion("qual'è la capitale dell'italia?",Categories.Geografia,"milano",false,"roma",true,"firenze",false,"torino",false));
            }
            if(i==2 || i==12 || i==22 || i==32){
                squares.add(new ArtLicteratureSquare(i));
                squares.get(i).setPossibleQuestions(createQuestion("chi ha scritto la divina commedia?",Categories.ArteLetteratura,"Boccaccio",false,"Manzoni",false,"Dante Alighieri",true,"Foscolo",false));
            }
            if(i==3 || i==14 || i==24 || i==34) {
                squares.add(new ScienceSquare(i));
                squares.get(i).setPossibleQuestions(createQuestion("cosa ha scoperto Newton?",Categories.Scienze,"gravitazione" ,true,"elettricità",false,"teoria della relatività",false,"moto dei pianeti",false));
            }
            if(i==4 || i==16 || i==27 || i==36) {
                squares.add(new ActualitySquare(i));
                squares.get(i).setPossibleQuestions(createQuestion("chi è l'attuale presidente degli USA?",Categories.Attualità,"Bush",false,"Obama",false,"Clinton",false,"Trump",true));
            }
            if(i==8 || i==18 || i==28 || i==38){
                squares.add(new SportSquare(i));
                squares.get(i).setPossibleQuestions(createQuestion("quale squadra ha vinto gli ultimi mondiali di calcio?",Categories.Sport,"Italia",false,"Spagna",false,"Germania",true,"Brasile",false));
            }
            if(i==6|| i==17 || i==26 || i==37) {
                squares.add(new ShowSquare(i));
                squares.get(i).setPossibleQuestions(createQuestion("quale film ha vinto l'ultima edizione degli Oscar come miglior film?",Categories.Spettacolo,"la forma dell'acqua",true,"call me be your name",false,"l'ora piu buia",false,"dunkirk",false));
            }
            if(i==9 || i==19|| i==29 || i==39){
                squares.add(new HistorySquare(i));
                squares.get(i).setPossibleQuestions(createQuestion("in quale anno è iniziata la prima guerra mondiale?",Categories.Storia,"1894",false,"1904",false,"1914",true,"1924",false));
            }
            if(i==5){
                squares.add(new FinalQuestionSquare(i,Categories.Sport));
                squares.get(i).setPossibleQuestions(createQuestion("DOMANDA FINALE: quale squadra ha vinto gli ultimi mondiali di calcio?",Categories.Sport,"Italia",false,"Spagna",false,"Germania",true,"Brasile",false));
            }
            if(i==10){
                squares.add(new FinalQuestionSquare(i,Categories.ArteLetteratura));
                squares.get(i).setPossibleQuestions(createQuestion("DOMANDA FINALE: chi ha scritto la divina commedia?",Categories.ArteLetteratura,"Boccaccio",false,"Manzoni",false,"Dante Alighieri",true,"Foscolo",false));
            }
            if(i==15){
                squares.add(new FinalQuestionSquare(i,Categories.Spettacolo));
                squares.get(i).setPossibleQuestions(createQuestion("DOMANDA FINALE: quale film ha vinto l'ultima edizione degli Oscar come miglior film?",Categories.Spettacolo,"la forma dell'acqua",true,"call me be your name",false,"l'ora piu buia",false,"dunkirk",false));
            }
            if(i==20){
                squares.add(new FinalQuestionSquare(i,Categories.Attualità));
                squares.get(i).setPossibleQuestions(createQuestion("DOMANDA FINALE: chi è l'attuale presidente degli USA?",Categories.Attualità,"Bush",false,"Obama",false,"Clinton",false,"Trump",true));
            }
            if(i==25){
                squares.add(new FinalQuestionSquare(i,Categories.Storia));
                squares.get(i).setPossibleQuestions(createQuestion("DOMANDA FINALE: in quale anno è iniziata la prima guerra mondiale?",Categories.Storia,"1894",false,"1904",false,"1914",true,"1924",false));
            }
            if(i==30){
                squares.add(new FinalQuestionSquare(i,Categories.Scienze));
                squares.get(i).setPossibleQuestions(createQuestion("DOMANDA FINALE: cosa ha scoperto Newton?",Categories.Scienze,"gravitazione" ,true,"elettricità",false,"teoria della relatività",false,"moto dei pianeti",false));
            }
            if(i==35){
                squares.add(new FinalQuestionSquare(i,Categories.Geografia));
                squares.get(i).setPossibleQuestions(createQuestion("DOMANDA FINALE: qual'è la capitale dell'italia?",Categories.Geografia,"milano",false,"roma",true,"firenze",false,"torino",false));
            }
            if(i==7)squares.add(new BonusMalusSquare(i,"malus1"));
            if(i==13)squares.add(new BonusMalusSquare(i,"bonus1"));
            if(i==23)squares.add(new BonusMalusSquare(i,"bonus2"));
            if(i==33)squares.add(new BonusMalusSquare(i,"malus2"));
        }
    }
    //metodo che crea una domanda di prova(arraylist con 1 domanda sola), poi va cancellato in quanto le domande vanno riempite dal db
    private ArrayList<Question> createQuestion(String question,Categories category,String answ1,Boolean correct1,String answ2,Boolean correct2,String answ3,Boolean correct3,String answ4,Boolean correct4){
        ArrayList<Question> questions= new ArrayList<Question>();
        ArrayList<Answer> answers=new ArrayList<Answer>();
        answers.add(new Answer(answ1,correct1));
        answers.add(new Answer(answ2,correct2));
        answers.add(new Answer(answ3,correct3));
        answers.add(new Answer(answ4,correct4));
        questions.add(new Question(question,category,answers));
        return questions;
    }
    public ArrayList<Square> getSquares() {
        return squares;
    }
}

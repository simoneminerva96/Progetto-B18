package Server.GameClasses;

import Server.GameClasses.ConnectionDB.ConnectionDB;
import Server.GameClasses.Squares.*;
import Server.GameClasses.Interface.BonusMalusRandom;

import java.sql.SQLException;
import java.util.ArrayList;
/**
 * classe che corrisponde al tabellone di gioco
 * @author Ansaldi Jacopo <jacopo.ansaldi01@universitadipavia.it>
 */
public class BoardProva{
    private final static int NSQUARES=36;   //NUMERO TOT DI CASELLE
    private ArrayList<Square> squares;
    private ConnectionDB connectionDB;

    //inserire le domande tramite il metodo della classe connesione db
    //MANCANO DA INSERIRE LE DOMANDE FINALI
    public BoardProva() throws SQLException {
        connectionDB=new ConnectionDB();
        squares=new ArrayList<Square>();
        for(int i=0;i<NSQUARES;i++){
            if(i==0) squares.add(new InitialSquare(i)); //casella start
            if(i==1 || i==10 || i==19 || i==28){
                squares.add(new GeographySquare(i));
                ArrayList<Question> possibleQuestionGeo=new ArrayList<Question>();
                //possibleQuestionGeo.addAll(connectionDB.getQuestion("GEO"));
                possibleQuestionGeo.addAll(creaDomandaDiProva("provaGeo",Categories.Geografia,"A",true,"b",false,"c",false,"d",false));
                squares.get(i).setPossibleQuestions(possibleQuestionGeo);
            }
            if(i==2 || i==11 || i==20|| i==29){
                squares.add(new ArtLicteratureSquare(i));
                ArrayList<Question> possibleQuestionArt=new ArrayList<Question>();
                //possibleQuestionArt.addAll(connectionDB.getQuestion("ART"));
                possibleQuestionArt.addAll(creaDomandaDiProva("provaArt",Categories.ArteLetteratura,"A",true,"b",false,"c",false,"d",false));
                squares.get(i).setPossibleQuestions(possibleQuestionArt);
            }
            if(i==3 || i==13 || i==22|| i==31) {
                squares.add(new ScienceSquare(i));
                ArrayList<Question> possibleQuestionSci=new ArrayList<Question>();
                //possibleQuestionSci.addAll(connectionDB.getQuestion("SCI"));
                possibleQuestionSci.addAll(creaDomandaDiProva("provaSci",Categories.Scienze,"A",true,"b",false,"c",false,"d",false));
                squares.get(i).setPossibleQuestions(possibleQuestionSci);
            }
            if(i==7 || i==16 || i==25|| i==34){
                squares.add(new SportSquare(i));
                ArrayList<Question> possibleQuestionSpo=new ArrayList<Question>();
                //possibleQuestionSpo.addAll(connectionDB.getQuestion("SPO"));
                possibleQuestionSpo.addAll(creaDomandaDiProva("provaSpo",Categories.Sport,"A",true,"b",false,"c",false,"d",false));
                squares.get(i).setPossibleQuestions(possibleQuestionSpo);
            }
            if(i==4|| i==15 || i==24 || i==33) {
                squares.add(new ShowSquare(i));
                ArrayList<Question> possibleQuestionSpe=new ArrayList<Question>();
                //possibleQuestionSpe.addAll(connectionDB.getQuestion("SPE"));
                possibleQuestionSpe.addAll(creaDomandaDiProva("provaSpe",Categories.Spettacolo,"A",true,"b",false,"c",false,"d",false));
                squares.get(i).setPossibleQuestions(possibleQuestionSpe);
            }
            if(i==8|| i==17|| i==26 || i==35){
                squares.add(new HistorySquare(i));
                ArrayList<Question> possibleQuestionSto=new ArrayList<Question>();
                //possibleQuestionSto.addAll(connectionDB.getQuestion("STO"));
                possibleQuestionSto.addAll(creaDomandaDiProva("provaSto",Categories.Storia,"A",true,"b",false,"c",false,"d",false));
                squares.get(i).setPossibleQuestions(possibleQuestionSto);
            }
            if(i==5){
                squares.add(new FinalQuestionSquare(i,Categories.Sport));
                ArrayList<Question> possibleQuestionSpoFinal=new ArrayList<Question>();
                //possibleQuestionSpoFinal.addAll(connectionDB.getQuestion("FSPO"));
                possibleQuestionSpoFinal.addAll(creaDomandaDiProva("provaSpoFinal",Categories.Geografia,"A",true,"b",false,"c",false,"d",false));
                squares.get(i).setPossibleQuestions(possibleQuestionSpoFinal);
            }
            if(i==9){
                squares.add(new FinalQuestionSquare(i,Categories.ArteLetteratura));
                ArrayList<Question> possibleQuestionArtFinal=new ArrayList<Question>();
                //possibleQuestionArtFinal.addAll(connectionDB.getQuestion("FART"));
                possibleQuestionArtFinal.addAll(creaDomandaDiProva("provaArtFinal",Categories.ArteLetteratura,"A",true,"b",false,"c",false,"d",false));
                squares.get(i).setPossibleQuestions(possibleQuestionArtFinal);
            }
            if(i==14){
                squares.add(new FinalQuestionSquare(i,Categories.Spettacolo));
                ArrayList<Question> possibleQuestionShowFinal=new ArrayList<Question>();
                //possibleQuestionShowFinal.addAll(connectionDB.getQuestion("FSPE"));
                possibleQuestionShowFinal.addAll(creaDomandaDiProva("provaShowFinal",Categories.Spettacolo,"A",true,"b",false,"c",false,"d",false));
                squares.get(i).setPossibleQuestions(possibleQuestionShowFinal);
            }
            if(i==23){
                squares.add(new FinalQuestionSquare(i,Categories.Storia));
                ArrayList<Question> possibleQuestionStoFinal=new ArrayList<Question>();
                //possibleQuestionStoFinal.addAll(connectionDB.getQuestion("FSTO"));
                possibleQuestionStoFinal.addAll(creaDomandaDiProva("provaStoFinal",Categories.Storia,"A",true,"b",false,"c",false,"d",false));
                squares.get(i).setPossibleQuestions(possibleQuestionStoFinal);
            }
            if(i==27){
                squares.add(new FinalQuestionSquare(i,Categories.Scienze));
                ArrayList<Question> possibleQuestionScieFinal=new ArrayList<Question>();
                //possibleQuestionScieFinal.addAll(connectionDB.getQuestion("FSCI"));
                possibleQuestionScieFinal.addAll(creaDomandaDiProva("provaSciFinal",Categories.Scienze,"A",true,"b",false,"c",false,"d",false));
                squares.get(i).setPossibleQuestions(possibleQuestionScieFinal);
            }
            if(i==32){
                squares.add(new FinalQuestionSquare(i,Categories.Geografia));
                ArrayList<Question> possibleQuestionGeofinal=new ArrayList<Question>();
                //possibleQuestionGeofinal.addAll(connectionDB.getQuestion("FGEO"));
                possibleQuestionGeofinal.addAll(creaDomandaDiProva("provaGeoFinal",Categories.Geografia,"A",true,"b",false,"c",false,"d",false));
                squares.get(i).setPossibleQuestions(possibleQuestionGeofinal);
            }
            if(i==6)squares.add(new BonusMalusSquare(i, BonusMalusRandom.Malus));
            if(i==12)squares.add(new BonusMalusSquare(i,BonusMalusRandom.Bonus));
            if(i==18)squares.add(new BonusMalusSquare(i,BonusMalusRandom.Random));
            if(i==21)squares.add(new BonusMalusSquare(i,BonusMalusRandom.Bonus));
            if(i==30)squares.add(new BonusMalusSquare(i,BonusMalusRandom.Malus));
        }
    }
    public ArrayList<Square> getSquares() {
        return squares;
    }

    private ArrayList<Question> creaDomandaDiProva(String domanda,Categories categoria,String ans1,boolean corr1,String ans2,boolean corr2,String ans3,boolean corr3,String ans4,boolean corr4){
        ArrayList<Question> q=new ArrayList<Question>();
        ArrayList<Answer> a=new ArrayList<Answer>();
        a.add(new Answer(ans1,corr1));
        a.add(new Answer(ans2,corr2));
        a.add(new Answer(ans3,corr3));
        a.add(new Answer(ans4,corr4));
        q.add(new Question(domanda,categoria,a));
        return q;
    }
}



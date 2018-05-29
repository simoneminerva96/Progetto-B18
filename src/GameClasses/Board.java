package GameClasses;

import GameClasses.Squares.*;
import ConnectionDB.ConnectionDB;

import java.sql.SQLException;
import java.util.ArrayList;
/**
 * classe che corrisponde al tabellone di gioco
 * @author Ansaldi Jacopo <jacopo.ansaldi01@universitadipavia.it>
 */
public class Board {
    private final static int NSQUARES=36;   //NUMERO TOT DI CASELLE
    private ArrayList<Square> squares;
    private ConnectionDB connectionDB;
    // inserire le domande tramite il metodo della classe connesione db
    //MANCANO DA INSERIRE LE DOMANDE FINALI
    public Board() throws SQLException{
        connectionDB=new ConnectionDB();
        squares=new ArrayList<Square>();
        for(int i=0;i<NSQUARES;i++){
            if(i==0) squares.add(new InitialSquare(i)); //casella start
            if(i==1 || i==10 || i==19 || i==28){
                squares.add(new GeographySquare(i));
                ArrayList<Question> possibleQuestionGeo=new ArrayList<Question>();
                possibleQuestionGeo.addAll(connectionDB.getQuestion("GEO"));
                squares.get(i).setPossibleQuestions(possibleQuestionGeo);
            }
            if(i==2 || i==11 || i==20|| i==29){
                squares.add(new ArtLicteratureSquare(i));
                ArrayList<Question> possibleQuestionArt=new ArrayList<Question>();
                possibleQuestionArt.addAll(connectionDB.getQuestion("ART"));
                squares.get(i).setPossibleQuestions(possibleQuestionArt);
            }
            if(i==3 || i==13 || i==22|| i==31) {
                squares.add(new ScienceSquare(i));
                ArrayList<Question> possibleQuestionSci=new ArrayList<Question>();
                possibleQuestionSci.addAll(connectionDB.getQuestion("SCI"));
                squares.get(i).setPossibleQuestions(possibleQuestionSci);
            }
            if(i==7 || i==16 || i==25|| i==34){
                squares.add(new SportSquare(i));
                ArrayList<Question> possibleQuestionSpo=new ArrayList<Question>();
                possibleQuestionSpo.addAll(connectionDB.getQuestion("SPO"));
                squares.get(i).setPossibleQuestions(possibleQuestionSpo);
            }
            if(i==4|| i==15 || i==24 || i==33) {
                squares.add(new ShowSquare(i));
                ArrayList<Question> possibleQuestionSpe=new ArrayList<Question>();
                possibleQuestionSpe.addAll(connectionDB.getQuestion("SPE"));
                squares.get(i).setPossibleQuestions(possibleQuestionSpe);
            }
            if(i==8|| i==17|| i==26 || i==35){
                squares.add(new HistorySquare(i));
                ArrayList<Question> possibleQuestionSto=new ArrayList<Question>();
                possibleQuestionSto.addAll(connectionDB.getQuestion("STO"));
                squares.get(i).setPossibleQuestions(possibleQuestionSto);
            }
            if(i==5){
                squares.add(new FinalQuestionSquare(i,Categories.Sport));
                ArrayList<Question> possibleQuestionSpoFinal=new ArrayList<Question>();
                possibleQuestionSpoFinal.addAll(connectionDB.getQuestion("FSPO"));
                squares.get(i).setPossibleQuestions(possibleQuestionSpoFinal);
            }
            if(i==9){
                squares.add(new FinalQuestionSquare(i,Categories.ArteLetteratura));
                ArrayList<Question> possibleQuestionArtFinal=new ArrayList<Question>();
                possibleQuestionArtFinal.addAll(connectionDB.getQuestion("FART"));
                squares.get(i).setPossibleQuestions(possibleQuestionArtFinal);
            }
            if(i==14){
                squares.add(new FinalQuestionSquare(i,Categories.Spettacolo));
                ArrayList<Question> possibleQuestionShowFinal=new ArrayList<Question>();
                possibleQuestionShowFinal.addAll(connectionDB.getQuestion("FSPE"));
                squares.get(i).setPossibleQuestions(possibleQuestionShowFinal);
            }
            if(i==23){
                squares.add(new FinalQuestionSquare(i,Categories.Storia));
                ArrayList<Question> possibleQuestionStoFinal=new ArrayList<Question>();
                possibleQuestionStoFinal.addAll(connectionDB.getQuestion("FSTO"));
                squares.get(i).setPossibleQuestions(possibleQuestionStoFinal);
            }
            if(i==27){
                squares.add(new FinalQuestionSquare(i,Categories.Scienze));
                ArrayList<Question> possibleQuestionScieFinal=new ArrayList<Question>();
                possibleQuestionScieFinal.addAll(connectionDB.getQuestion("FSCI"));
                squares.get(i).setPossibleQuestions(possibleQuestionScieFinal);
            }
            if(i==32){
                squares.add(new FinalQuestionSquare(i,Categories.Geografia));
                ArrayList<Question> possibleQuestionGeofinal=new ArrayList<Question>();
                possibleQuestionGeofinal.addAll(connectionDB.getQuestion("FGEO"));
                squares.get(i).setPossibleQuestions(possibleQuestionGeofinal);
            }
            if(i==6)squares.add(new BonusMalusSquare(i,"malus"));
            if(i==12)squares.add(new BonusMalusSquare(i,"bonus"));
            if(i==18)squares.add(new BonusMalusSquare(i,"random"));
            if(i==21)squares.add(new BonusMalusSquare(i,"bonus"));
            if(i==30)squares.add(new BonusMalusSquare(i,"malus"));
        }
    }
    public ArrayList<Square> getSquares() {
        return squares;
    }

}


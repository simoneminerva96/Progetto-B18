package GameClasses;

import GameClasses.Squares.*;

import java.util.ArrayList;
/*
    classe che corrisponde al tabellone di gioco
 */

public class Board {
    private ArrayList<Square> squares;

    public Board(){
        squares=new ArrayList<Square>();
        for(int i=0;i<40;i++){
            if(i==0) squares.add(new Square(i)); //casella start
            if(i==1 || i==11 || i==21 || i==31) squares.add(new GeographySquare(i));
            if(i==2 || i==12 || i==22 || i==32) squares.add(new ArtLicteratureSquare(i));
            if(i==3 || i==14 || i==24 || i==34) squares.add(new ScienceSquare(i));
            if(i==4 || i==16 || i==27 || i==36) squares.add(new ActualitySquare(i));
            if(i==8 || i==18 || i==28 || i==38) squares.add(new SportSquare(i));
            if(i==6|| i==17 || i==26 || i==37) squares.add(new ShowSquare(i));
            if(i==9 || i==19|| i==29 || i==39) squares.add(new HistorySquare(i));
            if(i==5)squares.add(new FinalQuestionSquare(i,Categories.Sport));
            if(i==10)squares.add(new FinalQuestionSquare(i,Categories.ArteLetteratura));
            if(i==15)squares.add(new FinalQuestionSquare(i,Categories.Spettacolo));
            if(i==20)squares.add(new FinalQuestionSquare(i,Categories.Attualità));
            if(i==25)squares.add(new FinalQuestionSquare(i,Categories.Storia));
            if(i==30)squares.add(new FinalQuestionSquare(i,Categories.Scienze));
            if(i==35)squares.add(new FinalQuestionSquare(i,Categories.Geografia));
            if(i==7)squares.add(new BonusMalusSquare(i,"malus1"));
            if(i==13)squares.add(new BonusMalusSquare(i,"bonus1"));
            if(i==23)squares.add(new BonusMalusSquare(i,"bonus2"));
            if(i==33)squares.add(new BonusMalusSquare(i,"malus2"));
        }
    }

    public ArrayList<Square> getSquares() {
        return squares;
    }
}

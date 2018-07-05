package Client.Graphics.Player;

import Server.GameClasses.Direction;
import Server.GameClasses.Slice;
import org.newdawn.slick.Animation;

import java.util.ArrayList;

/**
 * @author Stefano
 * La classe PlayerGUI rappresenta la grafica relativa a un certo Player, cioè quella che
 * effettivamente si muoverà sul tabellone.
 *
 * - x,y: coordinate da raggiungere
 * - xUpdate, yUpdate: coordinate x,y da aggiornare ogni frame
 * - p: oggetto di tipo Player associato alla sua GUI
 * - piece: oggetto di tipo Pedina associato al Player
 * - ready: flag che indica se la pedina è ferma ed è possibile visualizzare la domanda
 * - clicked: flag che indica se l'utente ha cliccato su una freccia per la direzione
 */

public class PlayerGUI {
    private Coordinate finali, update;
    private Player p;
    private Pedina piece;
    private boolean ready = false, clicked=false;
    private static final int minMovement = 50;

    private ArrayList<Slice> slc;


    public PlayerGUI(Player p, Pedina piece){
        this.p = p;
        this.piece = piece;
        finali = new Coordinate((p.getX()*minMovement), (p.getY()*minMovement));
        update = new Coordinate(finali.getX(), finali.getY());

        slc = new ArrayList<>();
    }

    /**
     * Calcolo le coordinate (x,y) che devono essere raggiunte da (xUpdate,yUpdate). Vengono aggiornate
     * ogni volta che viene eseguito il metodo update di Player.
     */
    public void updateCoordinates(){
        finali.setX((p.getX()*minMovement));
        finali.setY((p.getY()*minMovement));
    }

    /**
     * updateOnEachFrame aggiorna le coordinate (xUpdate,yUpdate) di +- 0.1*delta finchè non
     * raggiungono i valori delle coordinate (x,y). Il modo con cui vengono aggiornati dipende
     * dalla direzione di spostamento (BACK o FORWARD).
     *
     * @param delta parametro di Slick2D per aggiornare i frame di gioco.
     **/
    public void updateOnEachFrame(int delta) {
        int x = update.getX();
        int y = update.getY();

       if(p.getDirection()==Direction.FORWARD)
           updateForward(x,y,delta);
       else
           updateBack(x,y,delta);
    }

    public void updateForward(int x,int y,int delta){
        if (x != finali.getX() || y != finali.getY()) {
                if (y == (p.getMinime().getY()*minMovement) && x < (p.getMassime().getX()*minMovement)) {
                    x += 0.1;
                    update.setX(x);
                    piece.setMvdx();
                } else
                if (y < (p.getMassime().getY()*minMovement) && x == (p.getMassime().getX()*minMovement)) {
                    y += 0.1;
                    update.setY(y);
                    piece.setMvdwn();
                } else
                if (y == (p.getMassime().getY()*minMovement) && x > (p.getMinime().getX()*minMovement)) {
                    x -= 0.1;
                    update.setX(x);
                    piece.setMvsx();
                } else
                if (x == (p.getMinime().getX()*minMovement) && y > (p.getMinime().getY()*minMovement)) {
                    y -= 0.1;
                    update.setY(y);
                    piece.setMvup();
                }

        } else {
            piece.getCurrentImage().stop();
            if (clicked) {
                ready = true;
            }
        }

    }

    public void updateBack(int x,int y,int delta){
        if (x != finali.getX() || y != finali.getY()) {
           if(x==(p.getMassime().getX()*minMovement)&&y>(p.getMinime().getY()*minMovement)){
               y-=0.15*delta-0.3;
               update.setY(y);
               piece.setMvup();
           }else if(y==(p.getMinime().getY()*minMovement)&&x>(p.getMinime().getX()*minMovement)){
               x-=0.15*delta-0.3;
               update.setX(x);
               piece.setMvsx();
           }else if(x==(p.getMinime().getX()*minMovement)&&y<(p.getMassime().getY()*minMovement)){
               y+=0.45*delta-0.3;
               update.setY(y);
               piece.setMvdwn();
           }else if(y==(p.getMassime().getY()*minMovement)&&x<(p.getMassime().getX()*minMovement)){
               x+=0.45*delta-0.3;
               update.setX(x);
               piece.setMvdx();
           }
        }else{
            piece.getCurrentImage().stop();
            if(clicked){
                ready=true;
            }
        }
    }
    public float getxUpdate() {
        return update.getX();
    }

    public float getyUpdate() {
        return update.getY();
    }

    public Animation getPedina(){
        return piece.getCurrentImage();
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public String getName() {
        return p.getName();
    }

    //classe player della grafica
    public Player getP() {
        return p;
    }

    public void addSliceObtained (Slice slice) { slc.add(slice); }

    public ArrayList<Slice> getSlc() { return slc; }

}

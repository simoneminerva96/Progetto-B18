package Client.Graphics.Player;

import Server.GameClasses.GameClasses.Direction;
import Server.GameClasses.GameClasses.Slice;
import org.newdawn.slick.Animation;
import java.util.ArrayList;

/**
 * @author Stefano
 * La classe PlayerGUI rappresenta la grafica relativa a un certo Player, cioè quella che
 * effettivamente si muoverà sul tabellone.
 *
 * - finali: coordinate da raggiungere calcolate su Player
 * - update: coordinate x,y da aggiornare ogni frame
 * - p: oggetto di tipo Player associato alla sua GUI
 * - piece: oggetto di tipo Pedina associato al Player
 * - ready: flag che indica se la pedina è ferma ed è possibile visualizzare la domanda
 * - clicked: flag che indica se l'utente ha cliccato su una freccia per la direzione
 * - MINMOVEMENT : costante, ogni passo della pedina sono 50 pixel
 * - slc: array di Slice da visualizzare associati ad ogni player
 */

public class PlayerGUI {
    private Coordinate finali, update;
    private Player p;
    private Pedina piece;
    private boolean ready = false, clicked=false;
    private static final int MINMOVEMENT = 50;
    private ArrayList<Slice> slc;

    public PlayerGUI(Player p, Pedina piece){
        this.p = p;
        this.piece = piece;
        finali = new Coordinate((p.getX()*MINMOVEMENT), (p.getY()*MINMOVEMENT));
        update = new Coordinate(finali.getX(), finali.getY());
        slc = new ArrayList<>();
    }

    /** Calcolo le coordinate (x,y) che devono essere raggiunte da (xUpdate,yUpdate). Vengono aggiornate
     * ogni volta che viene eseguito il metodo update di Player. */
    public void updateCoordinates(){
        finali.setX((p.getX()*MINMOVEMENT));
        finali.setY((p.getY()*MINMOVEMENT));
    }

    /** updateOnEachFrame aggiorna le coordinate (xUpdate,yUpdate) delle animazioni di +- 1 finchè non raggiungono i
     * valori delle coordinate (x,y) sulla matrice. Il modo con cui vengono aggiornati dipende dalla direzione di s
     * postamento (BACK o FORWARD).**/
    public void updateOnEachFrame() {
       if(p.getDirection()==Direction.FORWARD)
           updateForward( update.getX(), update.getY());
       else
           updateBack(update.getX(),update.getY());
    }

    private void updateForward(int x,int y){
        if (x != finali.getX() || y != finali.getY()) {
                if (y == (p.getMinime().getY()*MINMOVEMENT) && x < p.getMassime().getX()*MINMOVEMENT) {
                    x += 1;
                    update.setX(x);
                    piece.setMvdx();
                } else
                if ( x == (p.getMassime().getX()*MINMOVEMENT)  && y < (p.getMassime().getY()*MINMOVEMENT) ) {
                    y += 1;
                    update.setY(y);
                    piece.setMvdwn();
                } else
                if (y == (p.getMassime().getY()*MINMOVEMENT) && x > (p.getMinime().getX()*MINMOVEMENT)) {
                    x -= 0.15;
                    update.setX(x);
                    piece.setMvsx();
                } else
                if (x == (p.getMinime().getX()*MINMOVEMENT) && y > (p.getMinime().getY()*MINMOVEMENT)) {
                    y -= 0.15;
                    update.setY(y);
                    piece.setMvup();
                }
        } else {
            piece.getCurrentImage().stop();
            if (clicked)
                ready = true;
        }
    }

    private void updateBack(int x,int y){
        if (x != finali.getX() || y != finali.getY()) {
            if(x == p.getMassime().getX()*MINMOVEMENT && y> p.getMinime().getY()*MINMOVEMENT){
                y-= 0.15;
                update.setY(y);
                piece.setMvup();
            }else
                if(y==p.getMinime().getY()*MINMOVEMENT && x>p.getMinime().getX()*MINMOVEMENT){
                x-= 0.15;
                update.setX(x);
                piece.setMvsx();
            }else
                if(x==p.getMinime().getX()*MINMOVEMENT && y<p.getMassime().getY()*MINMOVEMENT){
                y+= 1;
                update.setY(y);
                piece.setMvdwn();
            }else
                if(y==p.getMassime().getY()*MINMOVEMENT && x<p.getMassime().getX()*MINMOVEMENT){
                x+= 1;
                update.setX(x);
                piece.setMvdx();
            }
        }else{
            piece.getCurrentImage().stop();
            if(clicked)
                ready=true;
        }
    }

    public float getxUpdate() { return update.getX(); }

    public float getyUpdate() { return update.getY(); }

    public Animation getPedina(){ return piece.getCurrentImage(); }

    public boolean isReady() { return ready; }

    public void setReady(boolean ready) { this.ready = ready; }

    public void setClicked(boolean clicked) { this.clicked = clicked; }

    public String getName() { return p.getName(); }

    public Player getP() { return p; }

    public void addSliceObtained (Slice slice) { slc.add(slice); }

    public ArrayList<Slice> getSlc() { return slc; }

}

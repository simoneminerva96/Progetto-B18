package Client.Graphics.Player;

import Server.GameClasses.Interface.Direction;
import org.newdawn.slick.Animation;

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
    private float x,y;
    private float xUpdate,yUpdate;
    private Player p;
    private Pedina piece;
    private boolean ready = false, clicked=false;
    private static final int minMovement = 50;

    public PlayerGUI(Player p, Pedina piece){
        this.p = p;
        this.piece = piece;
        x = xUpdate = (p.getX()*minMovement)-5;
        y = yUpdate = (p.getY()*minMovement)-5;
    }

    /**
     * Calcolo le coordinate (x,y) che devono essere raggiunte da (xUpdate,yUpdate). Vengono aggiornate
     * ogni volta che viene eseguito il metodo update di Player.
     */
    public void updateCoordinates(){
        x = (p.getX()*minMovement)-5;
        y = (p.getY()*minMovement)-5;
    }

    /**
     * updateOnEachFrame aggiorna le coordinate (xUpdate,yUpdate) di +- 0.1*delta finchè non
     * raggiungono i valori delle coordinate (x,y). Il modo con cui vengono aggiornati dipende
     * dalla direzione di spostamento (BACK o FORWARD).
     *
     * @param delta parametro di Slick2D per aggiornare i frame di gioco.
     **/
    public void updateOnEachFrame(int delta) {
                if (xUpdate < x) {
                    if(this.p.getDirection()==Direction.FORWARD){
                        if (p.isOnLeft == false) {
                            piece.setMvdx();
                            xUpdate += 0.1 * delta;
                            if (xUpdate >= x) {
                                xUpdate = x;
                            }
                        } else {
                            piece.setMvup();
                            yUpdate -= 0.1 * delta;
                            if (yUpdate <= y) {
                                yUpdate = y;
                                p.isOnLeft = false;
                            }
                        }
                    }else {
                        if(p.isOnDown==false){
                            piece.setMvdx();
                            xUpdate += 0.1 * delta;
                            if (xUpdate >= x) {
                                xUpdate = x;
                            }
                        }else{
                            piece.setMvdwn();
                            yUpdate+=0.1*delta;
                            if(yUpdate>=y){
                                yUpdate=y;
                                p.isOnDown=false;
                            }
                        }
                    }
                } else if (yUpdate < y) {
                    if(p.getDirection()==Direction.BACK){
                        if(p.isOnUp==false){
                            piece.setMvdwn();
                            yUpdate += 0.1 * delta;
                            if (yUpdate >= y) {
                                yUpdate = y;
                            }
                        }else{
                            piece.setMvsx();
                            xUpdate-=0.1*delta;
                            if(xUpdate<=x){
                                xUpdate=x;
                                p.isOnUp=false;
                            }
                        }
                    }else{
                        piece.setMvdwn();
                        yUpdate += 0.1 * delta;
                        if (yUpdate >= y) {
                            yUpdate = y;
                        }
                    }
                } else if (xUpdate > x) {
                    if(p.getDirection()==Direction.FORWARD){
                        piece.setMvsx();
                        xUpdate -= 0.1 * delta;
                        if (xUpdate <= x) {
                            xUpdate = x;
                        }
                    }else{
                        if (p.isOnRight == false) {
                            piece.setMvsx();
                            xUpdate -= 0.1 * delta;
                            if (xUpdate <= x) {
                                xUpdate = x;
                            }
                        } else {
                            piece.setMvup();
                            yUpdate -= 0.1 * delta;
                            if (yUpdate <= y) {
                                yUpdate = y;
                                p.isOnRight = false;
                            }
                        }
                    }
                } else if (yUpdate > y) {
                    piece.setMvup();
                    yUpdate -= 0.1 * delta;
                    if (yUpdate <= y) {
                        yUpdate = y;
                    }
                } else {
                    piece.getCurrentImage().stop();
                    if(clicked) {
                        ready = true;
                    }
                }
    }

    public float getxUpdate() {
        return xUpdate;
    }

    public float getyUpdate() {
        return yUpdate;
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
}

package Graphics;

public class PlayerGUI {
    float x,y;
    float xUpdate,yUpdate;//coordinate x e y aggiornate ogni frame di gioco
    Player p;
    Pedina piece;
    int placeOnBoard=1; //posizione nella tavola di gioco;

    public PlayerGUI(Player p,Pedina piece){
        this.p=p;
        this.piece=piece;
        xUpdate=(p.getX()*32)-5;
        yUpdate=(p.getY()*32)-5;

    }
    public void updateCoordinates(){ //coordinate a cui xUpdate e yUpdate devono raggiungere.
        x=(p.getX()*32)-5;
        y=(p.getY()*32)-5;
    }

    //il metodo aggiorna le coordinate xUpdate e yUpdate finche non raggiungono i valori delle coordinate x e y.
    //il modo in cui vengono aggiornati dipende dalla direzione di spostamento (p.getDirection==true spostamento in avanti)
    public void updateOnEachFrame(int F) { //F è il delta del metodo update di SLICK2D.(Guardare metodo update Trivia variabile int i
        if (this.p.getDirection()) {
            if (xUpdate < x) {
                if (p.isOnLeft == false) {
                    piece.setMvdx();
                    xUpdate += 0.1 * F;
                    if (xUpdate >= x) {
                        xUpdate = x;
                    }
                } else {
                    piece.setMvup();
                    yUpdate -= 0.1 * F;
                    if (yUpdate <= y) {
                        yUpdate = y;
                        p.isOnLeft = false;
                    }
                }

            } else if (yUpdate < y) {
                piece.setMvdwn();
                yUpdate += 0.1 * F;
                if (yUpdate >= y) {
                    yUpdate = y;
                }

            } else if (xUpdate > x) {
                piece.setMvsx();
                xUpdate -= 0.1 * F;
                if (xUpdate <= x) {
                    xUpdate = x;
                }
            } else if (yUpdate > y) {
                piece.setMvup();
                yUpdate -= 0.1 * F;
                if (yUpdate <= y) {
                    yUpdate = y;
                }
            }
            else {
                piece.getCurrentImage().stop();
            }
        } else { //direzione indietro
            if (xUpdate > x) {
                if (p.isOnRight == false) {
                    piece.setMvsx();
                    xUpdate -= 0.1 * F;
                    if (xUpdate <= x) {
                        xUpdate = x;
                    }
                } else {
                    piece.setMvup();
                    yUpdate -= 0.1 * F;
                    if (yUpdate <= y) {
                        yUpdate = y;
                        p.isOnRight = false;
                    }
                }

            }else if (yUpdate < y) {
                piece.setMvdwn();
                yUpdate += 0.1 * F;
                if (yUpdate >= y) {
                    yUpdate = y;
                }

            }else if (xUpdate < x) {
                piece.setMvdx();
                xUpdate += 0.1 * F;
                if (xUpdate >= x) {
                    xUpdate = x;
                }
            } else if (yUpdate > y) {
                piece.setMvup();
                yUpdate -= 0.1 * F;
                if (yUpdate <= y) {
                    yUpdate = y;
                }
            }else {
                piece.getCurrentImage().stop();
            }
        }
    }

    public float getxUpdate() {
        return xUpdate;
    }

    public float getyUpdate() {
        return yUpdate;
    }

    public int getPlaceOnBoard(){
        return placeOnBoard;
    }

    public Player getPlayer(){
        return p;
    }
}

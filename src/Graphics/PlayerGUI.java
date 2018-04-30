package Graphics;

public class PlayerGUI {
    float x,y;
    float xUpdate,yUpdate;//coordinate x e y aggiornate ogni frame di gioco
    Player p;
    Pedina piece;

    public PlayerGUI(Player p,Pedina piece){
        this.p=p;
        this.piece=piece;
        x=xUpdate=(p.getX()*32)-5;
        y=yUpdate=(p.getY()*32)-5;

    }
    public void updateCoordinates(){ //coordinate a cui xUpdate e yUpdate devono raggiungere che vengon aggiornate ogni volta che viene
        //eseguito il metodo update di player.
        x=(p.getX()*32)-5;
        y=(p.getY()*32)-5;
    }

    //il metodo aggiorna le coordinate xUpdate e yUpdate (di +- 0.1) finchè non raggiungono i valori delle coordinate x e y .
    //il modo in cui vengono aggiornati dipende dalla direzione di spostamento (p.getDirection==true spostamento in avanti)
    public void updateOnEachFrame(int F) { //F è il delta del metodo update di SLICK2D.(Guardare metodo update() in Trivia variabile int i)
        if (this.p.getDirection()) {
            if (xUpdate < x) {  //in alto
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

            } else if (yUpdate < y) {//a destra
                piece.setMvdwn();
                yUpdate += 0.1 * F;
                if (yUpdate >= y) {
                    yUpdate = y;
                }

            } else if (xUpdate > x) {//in basso
                piece.setMvsx();
                xUpdate -= 0.1 * F;
                if (xUpdate <= x) {
                    xUpdate = x;
                }
            } else if (yUpdate > y) {//a sinistra
                piece.setMvup();
                yUpdate -= 0.1 * F;
                if (yUpdate <= y) {
                    yUpdate = y;
                }
            }
            else {
                piece.getCurrentImage().stop(); //altrimenti ferma animazione
            }
        } else { //direzione indietro
            if (xUpdate > x) {//in alto
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
            }else if (yUpdate < y) {//a sinistra
                piece.setMvdwn();
                yUpdate += 0.1 * F;
                if (yUpdate >= y) {
                    yUpdate = y;
                }
            }else if (xUpdate < x) {//in basso
                piece.setMvdx();
                xUpdate += 0.1 * F;
                if (xUpdate >= x) {
                    xUpdate = x;
                }
            } else if (yUpdate > y) {// a destra
                piece.setMvup();
                yUpdate -= 0.1 * F;
                if (yUpdate <= y) {
                    yUpdate = y;
                }
            }else {
                piece.getCurrentImage().stop(); //altrimenti ferma animazione
            }
        }
    }

    public float getxUpdate() {
        return xUpdate;
    }

    public float getyUpdate() {
        return yUpdate;
    }



    public Player getPlayer(){
        return p;
    }

    public Pedina getPedina(){
        return piece;
    }
}

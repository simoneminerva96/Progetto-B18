package javagame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import javax.swing.*;

public class Trivia extends BasicGameState {

    Map map;
    Player p,o,w,q,temp;

    int counter=0;

    private SpriteSheet MoveLeft; // initate a SprtieSheet
    private Animation MoveLeftAni; // initate a Animation

    Die die=new Die();


    private Animation currentImage;

    public String mouse= "No input";
    float x,y;
    Boolean b=false;
    TurnMaster master=new TurnMaster(4);
    int j=0;

    public Trivia(int id) throws Exception {
    }

    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

        map=new Map(22,22,"res/Tiled/1.0.png",32);
        p=new Player("ONE",1,map,new SpriteSheet("res/char/FFIV/Palom/palomdx.png",32,32));
        p.addMvdwn("res/char/FFIV/Palom/palomdwn.png",32,32);
        p.addMvlft("res/char/FFIV/Palom/palomsx.png",32,32);
        p.addMvrght("res/char/FFIV/Palom/palomdx.png",33,32);
        p.addMvup("res/char/FFIV/Palom/palomup.png",32,32);

        o=new Player("ONE",2,map,new SpriteSheet("res/char/FFIV/Luca/lucadx.png",32,32));
        o.addMvdwn("res/char/FFIV/Luca/lucadwn.png",32,32);
        o.addMvlft("res/char/FFIV/Luca/lucasx.png",32,32);
        o.addMvrght("res/char/FFIV/Luca/lucadx.png",32,32);
        o.addMvup("res/char/FFIV/Luca/lucaup.png",32,32);

        w=new Player("ONE",3,map,new SpriteSheet("res/char/FFIV/Kain/kaindx.png",32,32));
        w.addMvdwn("res/char/FFIV/Kain/kaindwn.png",32,32);
        w.addMvlft("res/char/FFIV/Kain/kainsx.png",32,32);
        w.addMvrght("res/char/FFIV/Kain/kaindx.png",33,32);
        w.addMvup("res/char/FFIV/Kain/kainup.png",32,32);

        q=new Player("ONE",4,map,new SpriteSheet("res/char/FFIV/Rydia/rydiadx.png",32,32));
        q.addMvdwn("res/char/FFIV/Rydia/Rydiadwn.png",32,32);
        q.addMvlft("res/char/FFIV/Rydia/rydiasx.png",32,32);
        q.addMvrght("res/char/FFIV/Rydia/rydiadx.png",33,32);
        q.addMvup("res/char/FFIV/Rydia/rydiaup.png",32,32);

        MoveLeft = new SpriteSheet("res/char/kingdice1.png",105,192);
        MoveLeftAni=new Animation(MoveLeft,90);
        currentImage=MoveLeftAni;

        p.playerGUI.stop();
        o.playerGUI.stop();
        w.playerGUI.stop();
        q.playerGUI.stop();
        try {
            master.addPlayer(p);
            master.addPlayer(o);
            master.addPlayer(q);
            master.addPlayer(w);
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {

       Image map=new Image("res/Tiled/1.0.png");
       graphics.drawImage(map,0,0);


        for(int i=0;i<4;i++){
            master.list[i].playerGUI.draw(master.list[i].xGUItemp,master.list[i].yGUItemp);
        }
        graphics.drawString(mouse,0,0);

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        float xpos=Mouse.getX();
        float ypos=Mouse.getY();

        mouse="Mouse position x:"+xpos+ " y: "+ypos;
        Input input=gameContainer.getInput();

        if (input.isKeyPressed(Input.KEY_W))
        {
            b=true;
            j=master.nextPlayer(j);
            System.out.println("GIOCATORE "+j);
            int diceN=die.Launch();
            System.out.println("DADO "+diceN);


            master.list[j].update(diceN);
            master.list[j].playerGUI.start();
        }

        if(b==false) {
            master.list[j].playerGUI.stop();
        }

        if(b){
            if(master.list[j].xGUItemp<master.list[j].xGUI){
                if(master.list[j].isOnleft==false) {
                    master.list[j].xGUItemp += 0.1 * i;
                    if(master.list[j].xGUItemp>=master.list[j].xGUI){
                        master.list[j].xGUItemp=master.list[j].xGUI;
                    }
                }else{
                    master.list[j].yGUItemp-=0.1*i;
                    if(master.list[j].yGUItemp<=master.list[j].yGUI){
                        master.list[j].yGUItemp=master.list[j].yGUI;
                        master.list[j].isOnleft=false;
                    }
                }


            }else if(master.list[j].yGUItemp<master.list[j].yGUI){
                master.list[j].yGUItemp+=0.1*i;
                if(master.list[j].yGUItemp>=master.list[j].yGUI){
                    master.list[j].yGUItemp=master.list[j].yGUI;
                }

            }else if(master.list[j].xGUItemp>master.list[j].xGUI){
                master.list[j].xGUItemp-=0.1*i;
                if(master.list[j].xGUItemp<=master.list[j].xGUI){
                    master.list[j].xGUItemp=master.list[j].xGUI;
                }

            }else if(master.list[j].yGUItemp>master.list[j].yGUI) {
                master.list[j].yGUItemp -= 0.1 * i;
                if (master.list[j].yGUItemp <= master.list[j].yGUI) {
                    master.list[j].yGUItemp = master.list[j].yGUI;
                }

            }else{
                b=false;
            }
        }



    /*    if(b){


          if(x<p.xGUI){
              x+=0.1*i;
              if(x>=p.xGUI){
                  x=p.xGUI;
              }
          }else if(y<p.yGUI){
              y+=0.1*i;
              if(y>=p.yGUI){
                  y=p.yGUI;
              }
          }else if(x>p.xGUI){
              x-=0.1*i;
              if(x<=p.xGUI){
                  x=p.xGUI;
              }
          }else if(y>p.yGUI){
              y-=0.1*i;
              if(y<=p.yGUI){
                  y=p.yGUI;
              }
          }else{
              System.out.println("Here");
              b=false;
          }
        }

        //System.out.println(b);
        */
    }
}

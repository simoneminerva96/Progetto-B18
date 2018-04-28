package Graphics;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Player {
    int currentX, currentY, id, minPlayerX, minPlayerY, maxPLayerX, maxPlayerY;
    String name;
    int mulsize;
    float xGUI = 0, yGUI = 0, xGUItemp = 0, yGUItemp = 0;
    public SpriteSheet mvup, mvdwn, mvlft, mvrght;
    public Animation playerGUI;
    public Boolean isOnleft = false;
    int mx, my;

    public Player(String name, int id, Map map, SpriteSheet mvlft) {
        this.id = id;
        this.mvlft = mvlft;
        mulsize = map.tilesize;
        playerGUI = new Animation(mvlft, 400);
        mx = map.getXmax();
        my = map.getYmax();

        switch (id) {
            case 1: {
                minPlayerX = 0;
                minPlayerY = 0;
                maxPLayerX = map.xmax - 2;
                maxPlayerY = map.ymax - 2;
                break;
            }
            case 2: {
                minPlayerX = 0;
                minPlayerY = 1;
                maxPLayerX = map.xmax - 2;
                maxPlayerY = map.ymax - 1;
                break;
            }
            case 3: {
                minPlayerX = 1;
                minPlayerY = 0;
                maxPLayerX = map.xmax - 1;
                maxPlayerY = map.ymax - 2;
                break;
            }
            case 4: {
                minPlayerX = 1;
                minPlayerY = 1;
                maxPLayerX = map.xmax - 1;
                maxPlayerY = map.ymax - 1;
                break;
            }
        }

        currentX = minPlayerX;
        currentY = minPlayerY;
        xGUI = (currentX * mulsize) - 5;
        yGUI = (currentY * mulsize) - 5;
        xGUItemp = xGUI;
        yGUItemp = yGUI;
        this.name = name;
    }

    public void addMvup(String path, int tw, int th) throws SlickException {
        mvup = new SpriteSheet(path, tw, th);
    }

    public void addMvdwn(String path, int tw, int th) throws SlickException {
        mvdwn = new SpriteSheet(path, tw, th);
    }

    public void addMvlft(String path, int tw, int th) throws SlickException {
        mvlft = new SpriteSheet(path, tw, th);
    }

    public void addMvrght(String path, int tw, int th) throws SlickException {
        mvrght = new SpriteSheet(path, tw, th);
    }

    public int getX() {
        return currentX;
    }

    public int getY() {
        return currentY;
    }

    public void setX(int x) {
        this.currentX = x;
    }

    public void setY(int y) {
        this.currentY = y;
    }

    public void update(int die) {
        int tempx, tempy;

        if (currentY == minPlayerY) {
            isOnleft = false;
            tempx = currentX + 2*die;
            if (tempx > maxPLayerX) {
                tempx = tempx - maxPLayerX;
                if (currentX == maxPLayerX) {
                    currentY = tempx;
                    playerGUI = new Animation(mvdwn, 150);
                }
            }
            else {
                currentX += 2 * die;
                playerGUI = new Animation(mvrght, 150);
            }
        }
        if (currentX == maxPLayerX) {
            tempy = currentY + (2 * die);
                if (tempy > maxPlayerY) {
                    tempy = tempy - maxPlayerY;
                    currentX -= tempy;
                    currentY = maxPlayerY;
                    playerGUI = new Animation(mvlft, 150);
                } else {
                    currentY += 2 * die;
                    playerGUI = new Animation(mvdwn, 150);
                }
            } else if (currentY == maxPlayerY) {
                tempx = currentX - (2 * die);
                if (tempx < minPlayerX) {
                    tempx = minPlayerX - tempx;
                    currentX = minPlayerX;
                    currentY -= tempx;
                    playerGUI = new Animation(mvup, 150);
                } else {
                    currentX -= 2 * die;
                    playerGUI = new Animation(mvlft, 150);
                }
            } else if (currentX == minPlayerX) {
                tempy = currentY - (2 * die);
                isOnleft = true;
                System.out.println("TEMPY: " + tempy + " MAXY: " + maxPLayerX);
                if (tempy < minPlayerY) {
                    tempy = minPlayerY - tempy;
                    currentX += tempy;
                    currentY = minPlayerY;
                    System.out.println("QUA" + currentY);
                    playerGUI = new Animation(mvrght, 150);
                } else {
                    currentY -= 2 * die;
                    playerGUI = new Animation(mvup, 150);
                }
            }
            xGUI = (currentX * mulsize) - 5;
            yGUI = (currentY * mulsize) - 5;

        }
    }

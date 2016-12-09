package com.t3h.bomb.model;

import com.t3h.bomb.manager.ImageLoader;

import java.awt.*;

/**
 * Created by HaiTran on 7/30/2016.
 */
public class Bomb extends People {
    public static final int WIDTH_BOMB = 45;
    public static final int BOMB_DIE = 3000;
    private int time;
    private int size;

    public Bomb(int x, int y,int size, int time) {
        this.x = (x / People.WIDTH) * (People.WIDTH);
        this.y = (y / People.WIDTH) * (People.WIDTH);
        this.size=size;
        this.time = time;
    }

    public void draw(Graphics2D g2D) {
        g2D.drawImage(ImageLoader.IMG_BOMB, x, y, null);
    }
    public boolean checkImpactBombVsPeople(Bomber bomber) {
        return getRec(x, y).intersects(bomber.getRec());
    }

    public boolean checkCollisionBombVsPeople(People people) {
        if (people.getSetRunPeople() == Bomber.ALLOW_RUN) {
            return false;
        }
        if (getRec(x, y).intersects(people.getRec())) {
            return true;
        }
        return false;
    }

    public Rectangle getRec(int x, int y) {
        return new Rectangle(x, y, WIDTH_BOMB, WIDTH_BOMB);
    }
    public void diminishedTime() {
        this.time--;
    }

    public boolean checkBombVzBomb(int xNew, int yNew) {
        Rectangle rec1 = new Rectangle(x, y, WIDTH_BOMB, WIDTH_BOMB);
        Rectangle rec2 = new Rectangle(xNew, yNew, WIDTH_BOMB, WIDTH_BOMB);
        return rec1.intersects(rec2);
    }

    public int getSize() {
        return size;
    }
    public int getTime() {
        return time;
    }
    @Override
    public int getX() {
        return super.getX();
    }
    @Override
    public int getY() {
        return super.getY();
    }
}

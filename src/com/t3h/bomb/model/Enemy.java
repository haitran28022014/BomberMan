package com.t3h.bomb.model;

import com.t3h.bomb.manager.ImageLoader;

import java.awt.*;
import java.util.Random;

/**
 * Created by HaiTran on 7/25/2016.
 */
public class Enemy extends People {
    private int type;
    public static final int ENEMY = 1;
    public static final int BOSS = 2;
    private Random random = new Random();

    public Enemy(int x, int y, int type, int orient, int delay) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.orient = orient;
        this.delay = delay;
        this.setRunPeople = Bomber.DISALLOW_RUN;
    }

    @Override
    public void draw(Graphics2D g2D) {
        switch (getType()) {
            case ENEMY:
                image = ImageLoader.IMG_ENEMY[orient];
                g2D.drawImage(image, x, y - REDUNDANCY, null);
                break;
            case BOSS:
                break;
        }
    }

    public void randomOrient() {
        int newOrient = random.nextInt(4);
        int value = random.nextInt(2000);
        if (value < 3) {
            setOrient(newOrient);
            image = ImageLoader.IMG_ENEMY[getOrient()];
        }
    }

    public int getType() {
        return type;
    }
}

package com.t3h.bomb.model;

import com.t3h.bomb.manager.ImageLoader;

import java.awt.*;

/**
 * Created by HaiTran on 7/25/2016.
 */
public class Bomber extends People {
    public static final int ALLOW_RUN = 0;
    public static final int DISALLOW_RUN = 1;
    public static final int BOMBER_DEAD = 0;
    public static final int BOMBER_ALIVE = 1;
    public static final int DELAY_BOMBER = 5;
    public static final int QUANTITY_BOMB_ONE = 1;
    public static final int INIT_SCORE = 0;
    public static final int INIT_HEART = 3;
    public static final int SIZE_BOMB = 1;
    private int quantityBomb;
    private int sizeBomb;
    private int score;
    private int heart;
    private int status;

    public Bomber(int x, int y, int delay, int quantityBomb, int sizeBomb) {
        this.x = x;
        this.y = y;
        this.delay = delay;
        this.quantityBomb = quantityBomb;
        this.sizeBomb = sizeBomb;
        this.score = INIT_SCORE;
        this.heart = INIT_HEART;
        image = ImageLoader.IMG_BOMBER[RIGHT];
        setRunPeople = DISALLOW_RUN;
        status = BOMBER_ALIVE;
    }

    @Override
    public void draw(Graphics2D g2D) {
        g2D.drawImage(image, x, y - REDUNDANCY, WIDTH, HEIGHT, null);
    }

    @Override
    public void move(int count) {
        if (status==BOMBER_DEAD){
            return;
        }
        super.move(count);
    }

    @Override
    public void setOrient(int orient) {
        super.setOrient(orient);
        image = ImageLoader.IMG_BOMBER[orient];
    }

    public boolean isImpactBomberVsEnemy(Enemy enemy){
        if(status==BOMBER_DEAD){
            return false;
        }
        Rectangle rec1=new Rectangle(x,y,WIDTH,WIDTH);
        Rectangle rec2 = new Rectangle(enemy.getX(), enemy.getY(),WIDTH,WIDTH);
        return rec1.intersects(rec2);
    }

    public void setNewBomber(int xNew, int yNew) {
        this.x = xNew;
        this.y = yNew;
        this.image = ImageLoader.IMG_BOMBER[DOWN];
        this.status = BOMBER_ALIVE;
    }

    public void setSizeBomb(int sizeBomb) {
        if (getSizeBomb() > 3) {
            return;
        }
        this.sizeBomb = sizeBomb;
    }

    public void setQuantityBomb(int quantityBomb) {
        if (getQuantityBomb() > 5) {
            return;
        }
        this.quantityBomb = quantityBomb;
    }
    @Override
    public void setImage(Image image) {super.setImage(image);}
    @Override
    public int getDelay() {
        return super.getDelay();
    }
    @Override
    public void setDelay(int delay) {
        if (getDelay()<3){
            return;
        }
        super.setDelay(delay);
    }
    public int getQuantityBomb() {
        return quantityBomb;
    }
    public int getSizeBomb() {
        return sizeBomb;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public int getHeart() {return heart;}
    public void setHeart(int heart) {this.heart = heart;}
    public int getStatus() {return status;}
    public void setStatus(int status) {
        this.status = status;
    }
}

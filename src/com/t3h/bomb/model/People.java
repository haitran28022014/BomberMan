package com.t3h.bomb.model;

import com.t3h.bomb.gui.Gui;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by HaiTran on 7/25/2016.
 */
public abstract class People {
    public static final int WIDTH = 45;
    public static final int HEIGHT = 65;
    public static final int REDUNDANCY = 25;
    protected int x;
    protected int y;
    protected int delay;
    protected int orient;
    protected int setRunPeople;
    protected Image image;

    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;


    public void move(int count) {
        if (count%this.delay!=0){
            return;
        }
        switch (orient) {
            case LEFT:
                int xLeft = x - 1;
                if (xLeft < 0) {
                    return;
                }
                x = xLeft;
                break;

            case RIGHT:
                int xRight = x + 1;
                int xBorder = Gui.H_FRAME - WIDTH + 15;
                if (xRight > xBorder) {
                    return;
                }
                x = xRight;
                break;
            case UP:
                int yUp = y - 1;
                if (yUp < 0) {
                    return;
                }
                y = yUp;
                break;
            case DOWN:
                int yDown = y + 1;
                int yBorder = Gui.H_FRAME - HEIGHT;
                if (yDown > yBorder) {
                    return;
                }
                y = yDown;
                break;
        }
    }

    public void checkCollisionVsPeople(ArrayList<Box> arrBox, ArrayList<Bomb> arrBomb) {
        switch (orient) {
            case LEFT:
                for (int i = 0; i < arrBomb.size(); i++) {
                    if (arrBomb.get(i).checkCollisionBombVsPeople(this) == true) {
                        x = x + 1;
                        return;
                    }
                }
                for (int i = 0; i < arrBox.size(); i++) {
                    int k = arrBox.get(i).checkCollisionBoxVsPeople(this);
                    if (k != 0) {
                        if (k >= -REDUNDANCY && k <= REDUNDANCY) {
                            if (k > 0) {
                                y = y + 1;
                            } else {
                                y = y - 1;
                            }
                        }
                        x = x + 1;
                        return;
                    }
                }
                break;
            case RIGHT:
                for (int i = 0; i < arrBomb.size(); i++) {
                    if (arrBomb.get(i).checkCollisionBombVsPeople(this) == true) {
                        x = x - 1;
                        return;
                    }
                }

                for (int i = 0; i < arrBox.size(); i++) {
                    int k = arrBox.get(i).checkCollisionBoxVsPeople(this);
                    if (k != 0) {
                        if (k >= -REDUNDANCY && k <= REDUNDANCY) {
                            if (k > 0) {
                                y = y + 1;
                            } else {
                                y = y - 1;
                            }
                        }
                        x = x - 1;
                        return;
                    }
                }
                break;
            case UP:
                for (int i = 0; i < arrBomb.size(); i++) {
                    if (arrBomb.get(i).checkCollisionBombVsPeople(this) == true) {
                        y = y + 1;
                        return;
                    }
                }

                for (int i = 0; i < arrBox.size(); i++) {
                    int k = arrBox.get(i).checkCollisionBoxVsPeople(this);
                    if (k != 0) {
                        if (k >= -REDUNDANCY && k <= REDUNDANCY) {
                            if (k > 0) {
                                x = x + 1;
                            } else {
                                x = x - 1;
                            }
                        }
                        y = y + 1;
                        return;
                    }
                }
                break;
            case DOWN:
                for (int i = 0; i < arrBomb.size(); i++) {
                    if (arrBomb.get(i).checkCollisionBombVsPeople(this) == true) {
                        y = y - 1;
                        return;
                    }
                }

                for (int i = 0; i < arrBox.size(); i++) {
                    int k = arrBox.get(i).checkCollisionBoxVsPeople(this);
                    if (k != 0) {
                        if (k >= -REDUNDANCY && k <= REDUNDANCY) {
                            if (k > 0) {
                                x = x + 1;
                            } else {
                                x = x - 1;
                            }
                        }
                        y = y - 1;
                        return;
                    }
                }
                break;
        }
    }

    public abstract void draw(Graphics2D g2D);

    public void setOrient(int orient) {
        this.orient = orient;
    }

    public int getOrient() {
        return orient;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getRec() {
        return new Rectangle(x, y, WIDTH, WIDTH);
    }

    public int getSetRunPeople() {
        return setRunPeople;
    }

    public void setSetRunPeople(int setRunPeople) {
        this.setRunPeople = setRunPeople;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}

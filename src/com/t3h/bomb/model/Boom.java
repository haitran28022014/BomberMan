package com.t3h.bomb.model;

import com.t3h.bomb.manager.ImageLoader;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by HaiTran on 8/1/2016.
 */
public class Boom {
    private int x;
    private int y;
    private Image imageLeft, imageRight, imageUp, imageDown;
    public static final int WIDTH_BOOM = 45;
    public static final int BOOM_DIE = 200;
    private int size;
    private int time;


    public Boom(int x, int y, int size, int time, ArrayList<Box> arrBox) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.time = time;
        imageLeft = ImageLoader.IMG_BOOM_1[Bomber.LEFT];
        imageRight = ImageLoader.IMG_BOOM_1[Bomber.RIGHT];
        imageUp = ImageLoader.IMG_BOOM_1[Bomber.UP];
        imageDown = ImageLoader.IMG_BOOM_1[Bomber.DOWN];
        handlerDrawBoom(arrBox);

    }


    public void setImageBoom(int orient, int size) {
        switch (orient) {
            case Bomber.LEFT:
                if (size == 2)
                    imageLeft = ImageLoader.IMG_BOOM_2[Bomber.LEFT];
                break;
            case Bomber.RIGHT:
                if (size == 2)
                    imageRight = ImageLoader.IMG_BOOM_2[Bomber.RIGHT];
                break;
            case Bomber.UP:
                if (size == 2)
                    imageUp = ImageLoader.IMG_BOOM_2[Bomber.UP];
                break;
            case Bomber.DOWN:
                if (size == 2)
                    imageDown = ImageLoader.IMG_BOOM_2[Bomber.DOWN];
                break;
        }
    }

    public void draw(Graphics2D g2D) {
        g2D.drawImage(imageLeft, x + WIDTH_BOOM - imageLeft.getWidth(null), y, null);
        g2D.drawImage(imageRight, x, y, null);
        g2D.drawImage(imageUp, x, y + WIDTH_BOOM - imageUp.getHeight(null), null);
        g2D.drawImage(imageDown, x, y, null);
    }

    public boolean checkCollisionBoomVsBox(int x, int y, int width, int height, Box box) {
        Rectangle recBoom = new Rectangle(x, y, width, height);
        return recBoom.intersects(box.getRec());
    }

    /**
     * Khi bombSize==2 lúc đó ta sẽ xét giả sử va chạm với bombSize=1 nếu nổ mà va chạm với box nghĩa là
     * bên cạnh quả bomb có box với hướng tùy,lúc đó ta sẽ xét hình ảnh mà va chạm cạnh nó là ảnh ngắn
     * còn nếu không va chạm ta sẽ xét ảnh là ảnh dài,khi i tăng lên 2 sẽ không nhảy vào đâu cả
     *
     * @param arrBox
     */
    public void handlerDrawBoom(ArrayList<Box> arrBox) {
        for (int i = 1; i < size; i++) {
            int boomLeft = 0, boomRight = 0, boomUp = 0, boomDown = 0;
            for (int j = 0; j < arrBox.size(); j++) {
                if (checkCollisionBoomVsBox(x - (i * WIDTH_BOOM), y, WIDTH_BOOM * (i + 1), WIDTH_BOOM, arrBox.get(j))) {
                    boomLeft = 1;
                }
                if (checkCollisionBoomVsBox(x, y, WIDTH_BOOM * (i + 1), WIDTH_BOOM, arrBox.get(j))) {
                    boomRight = 1;
                }
                if (checkCollisionBoomVsBox(x, y - (i * WIDTH_BOOM), WIDTH_BOOM, WIDTH_BOOM * (i + 1), arrBox.get(j))) {
                    boomUp = 1;
                }
                if (checkCollisionBoomVsBox(x, y, WIDTH_BOOM, WIDTH_BOOM * (i + 1), arrBox.get(j))) {
                    boomDown = 1;
                }
            }
            if (boomLeft == 0) {
                setImageBoom(People.LEFT, i + 1);
            }
            if (boomRight == 0) {
                setImageBoom(People.RIGHT, i + 1);
            }
            if (boomUp == 0) {
                setImageBoom(People.UP, i + 1);
            }
            if (boomDown == 0) {
                setImageBoom(People.DOWN, i + 1);
            }
        }
    }

    public boolean isCollisionBoomVsBox(Box box) {
        if (box.getType() == Box.TYPE_BOX_1) {
            return false;
        }
        Rectangle recBox = box.getRec();
        if (recLeft().intersects(recBox) || recRight().intersects(recBox)
                || recUp().intersects(recBox) || recDown().intersects(recBox)) {
            return true;
        }
        return false;
    }

    public boolean isCollisionBoomVsEnemy(Enemy enemy) {
        Rectangle recEnemy = enemy.getRec();
        if (recLeft().intersects(recEnemy) || recRight().intersects(recEnemy)
                || recUp().intersects(recEnemy) || recDown().intersects(recEnemy)) {
            return true;
        }
        return false;
    }

    public boolean isCollisionBoomVsItem(Item item) {
        Rectangle recItem = item.getRecItem();
        if (recLeft().intersects(recItem) || recRight().intersects(recItem)
                || recUp().intersects(recItem) || recDown().intersects(recItem)) {
            return true;
        }
        return false;
    }

    public boolean isCollisionBoomVsBomber(Bomber bomber) {
        Rectangle recBomber = bomber.getRec();
        if (recLeft().intersects(recBomber) || recRight().intersects(recBomber)
                || recUp().intersects(recBomber) || recDown().intersects(recBomber)) {
            return true;
        }
        return false;
    }

    public boolean isCollisionBoomVsBomb(Bomb bomb) {
        Rectangle recBomb = bomb.getRec();
        if (recLeft().intersects(recBomb) || recRight().intersects(recBomb)
                || recUp().intersects(recBomb) || recDown().intersects(recBomb)) {
            return true;
        }
        return false;
    }

    public Rectangle recLeft() {return new Rectangle(x + WIDTH_BOOM - imageLeft.getWidth(null),
            y, imageLeft.getWidth(null), imageLeft.getHeight(null));}

    public Rectangle recRight() {
        return new Rectangle(x, y,
                imageRight.getWidth(null), imageRight.getHeight(null));
    }

    public Rectangle recUp() {
        return new Rectangle(x, y + WIDTH_BOOM - imageUp.getHeight(null),
                imageUp.getWidth(null), imageUp.getHeight(null));
    }

    public Rectangle recDown() {
        return new Rectangle(x, y,
                imageDown.getWidth(null), imageDown.getHeight(null));
    }

    public void deadBoom() {
        this.time--;
    }

    public int getTime() {
        return time;
    }

}

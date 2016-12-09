package com.t3h.bomb.model;

import com.t3h.bomb.manager.ImageLoader;

import java.awt.*;

/**
 * Created by HaiTran on 7/25/2016.
 */
public class Item {
    public static final int ITEM_BOMB = 0;
    public static final int ITEM_BOMB_SIZE = 1;
    public static final int ITEM_SHOE = 2;

    private int x;
    private int y;
    private Image image;
    private int type;

    public Item(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
        image=ImageLoader.IMG_ITEM[type];
    }

    public void drawItem(Graphics2D g2D) {
        switch (getType()) {
            case ITEM_BOMB:
                image = ImageLoader.IMG_ITEM[ITEM_BOMB];
                break;
            case ITEM_BOMB_SIZE:
                image = ImageLoader.IMG_ITEM[ITEM_BOMB_SIZE];
                break;
            case ITEM_SHOE:
                image = ImageLoader.IMG_ITEM[ITEM_SHOE];
                break;
        }
        g2D.drawImage(image, x, y, image.getWidth(null), image.getHeight(null), null);
    }

    public int getType() {
        return type;
    }


    public Rectangle getRecItem() {
        return new Rectangle(x, y, image.getWidth(null),image.getHeight(null));
    }

    public boolean checkImpactItemVsBomber(Bomber bomber) {
        Rectangle recBomber=new Rectangle(bomber.getX(),bomber.getY(),bomber.WIDTH,bomber.WIDTH-20);
        return getRecItem().intersects(recBomber);
    }

}

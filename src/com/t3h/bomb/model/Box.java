package com.t3h.bomb.model;

import com.t3h.bomb.manager.ImageLoader;

import java.awt.*;

/**
 * Created by HaiTran on 7/25/2016.
 */
public class Box {
    private int x;
    private int y;
    private int type;
    private Image image;
    public static final int SIZE = 45;
    public static final int SIZE_HEIGHT_SHADOW_1 = 22;
    public static final int SIZE_HEIGHT_SHADOW_2 = 7;
    public static final int TYPE_BOX_1 = 1;
    public static final int TYPE_BOX_2 = 2;
    public static final int TYPE_SHADOW_1 = 3;
    public static final int TYPE_SHADOW_2 = 4;

    public Box(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void drawBox(Graphics2D g2D) {
        image = null;
        switch (getType()) {
            case TYPE_BOX_1:
                image = ImageLoader.IMG_BOX_1;
                break;
            case TYPE_BOX_2:
                image = ImageLoader.IMG_BOX_2;
                break;
            case TYPE_SHADOW_1:
                g2D.drawImage(ImageLoader.IMG_SHADOW_1, x, y - SIZE_HEIGHT_SHADOW_1, null);
                break;
            case TYPE_SHADOW_2:
                g2D.drawImage(ImageLoader.IMG_SHADOW_2, x, y - SIZE_HEIGHT_SHADOW_2, null);
                break;
            default:
        }
        if (image != null)
            g2D.drawImage(image, x, y, null);
    }

    /**
     * Xử lý để cho people dễ dàng chui qua giữa 2 ô box
     * @param people
     * @return
     */

    public int checkCollisionBoxVsPeople(People people) {
        if (getType() == TYPE_BOX_1 || getType() == TYPE_BOX_2) {
            Rectangle recBox = new Rectangle(x, y, SIZE, SIZE);
            Rectangle recPeople = people.getRec();
            if (recBox.intersects(recPeople)) {
                Rectangle rec = recBox.intersection(recPeople);
                if ((people.getOrient() == People.UP || people.getOrient() == People.DOWN)) {
                    if (people.getX() == rec.getX()) {
                        return (int) rec.getWidth();
                    } else {
                        return (int) -rec.getWidth();
                    }
                } else{
                    if (people.getY() == rec.getY()) {
                        return (int) rec.getHeight();
                    } else {
                        return (int) -rec.getHeight();
                    }
                }
            }
        }
        return 0;
    }

    public Rectangle getRec() {return new Rectangle(x, y, SIZE, SIZE);}
    public int getType() {
        return type;
    }

}

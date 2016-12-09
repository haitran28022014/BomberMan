package com.t3h.bomb.manager;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Created by HaiTran on 7/25/2016.
 */
public class ImageLoader {
    public static final Image IMG_BACKGROUND_PLAY = new ImageIcon(getURL("background_Play.png")).getImage();
    public static final Image IMG_BACKGROUND_INFO = new ImageIcon(getURL("background_Info.png")).getImage();
    public static final Image IMG_BACKGROUND_OPTION = new ImageIcon(getURL("background_option.png")).getImage();

    public static final Image IMG_HEART=new ImageIcon(getURL("heart_1.png")).getImage();

    public static final ImageIcon IMG_BACKGROUND_MENU = new ImageIcon(getURL("background_Menu.png"));
    public static final ImageIcon IMG_BUTTON_PLAY = new ImageIcon(getURL("Play.png"));
    public static final ImageIcon IMG_BUTTON_OPTION = new ImageIcon(getURL("Option.png"));
    public static final ImageIcon IMG_BUTTON_SCORE = new ImageIcon(getURL("HighScore.png"));
    public static final ImageIcon IMG_BUTTON_EXIT = new ImageIcon(getURL("Exit.png"));
    public static final ImageIcon IMG_BUTTON_PLAY_2 = new ImageIcon(getURL("Play2.png"));
    public static final ImageIcon IMG_BUTTON_OPTION_2 = new ImageIcon(getURL("Option2.png"));
    public static final ImageIcon IMG_BUTTON_SCORE_2 = new ImageIcon(getURL("HighScore2.png"));
    public static final ImageIcon IMG_BUTTON_EXIT_2 = new ImageIcon(getURL("Exit2.png"));
    public static final ImageIcon IMG_BUTTON_OK_1=new ImageIcon(getURL("Ok_1.png"));

    public static final Image IMG_BOX_1 = new ImageIcon(getURL("box1.png")).getImage();
    public static final Image IMG_BOX_2 = new ImageIcon(getURL("box2.png")).getImage();

    public static final Image IMG_SHADOW_1 = new ImageIcon(getURL("shadow1.png")).getImage();
    public static final Image IMG_SHADOW_2 = new ImageIcon(getURL("shadow2.png")).getImage();

    public static final Image[] IMG_BOMBER = {
            new ImageIcon(getURL("bomber_left.png")).getImage(),
            new ImageIcon(getURL("bomber_right.png")).getImage(),
            new ImageIcon(getURL("bomber_up.png")).getImage(),
            new ImageIcon(getURL("bomber_down.png")).getImage(),
    };

    public static final Image[] IMG_ENEMY = {
            new ImageIcon(getURL("monster_left.png")).getImage(),
            new ImageIcon(getURL("monster_right.png")).getImage(),
            new ImageIcon(getURL("monster_up.png")).getImage(),
            new ImageIcon(getURL("monster_down.png")).getImage()
    };

    public static final Image IMG_BOMB = new ImageIcon(getURL("bomb.png")).getImage();

    public static final Image[] IMG_BOOM_1 = {
            new ImageIcon(getURL("boom_left_1.png")).getImage(),
            new ImageIcon(getURL("boom_right_1.png")).getImage(),
            new ImageIcon(getURL("boom_up_1.png")).getImage(),
            new ImageIcon(getURL("boom_down_1.png")).getImage()
    };

    public static final Image[] IMG_BOOM_2 = {
            new ImageIcon(getURL("boom_left_2.png")).getImage(),
            new ImageIcon(getURL("boom_right_2.png")).getImage(),
            new ImageIcon(getURL("boom_up_2.png")).getImage(),
            new ImageIcon(getURL("boom_down_2.png")).getImage()
    };

    public static final Image[] IMG_ITEM = {
            new ImageIcon(getURL("item_bomb.png")).getImage(),
            new ImageIcon(getURL("item_bomb_size.png")).getImage(),
            new ImageIcon(getURL("item_shoe.png")).getImage(),
    };

    public static final Image IMG_BOMBER_DEAD = new ImageIcon(getURL("bomber_dead.png")).getImage();
    public static final Image IMG_GHOST = new ImageIcon(getURL("ghost.png")).getImage();


    public ImageLoader() {

    }

    public static URL getURL(String fileName) {
        return ImageLoader.class.getResource("/res/images/" + fileName);
    }
}

package com.t3h.bomb.manager;

import com.t3h.bomb.gui.Gui;
import com.t3h.bomb.model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by HaiTran on 7/25/2016.
 */
public class GameManager extends JPanel {
    public static final int WIDTH_GAME_PLAY = 675;
    public static final int TIME_DEAD = 1000;
    private Bomber bomber;
    private DrawMap drawMap;
    private ArrayList<Bomb> arrBomb;
    private ArrayList<Boom> arrBoom;
    private HighScoreManager highScoreManager;
    private int timeDead;
    private int type;

    public GameManager() {
        initializeContainer();
        initializeLoadMap();
    }

    private void initializeContainer() {
        bomber = new Bomber(350, 535, Bomber.DELAY_BOMBER, Bomber.QUANTITY_BOMB_ONE, Bomber.SIZE_BOMB);
        drawMap = new DrawMap();
        arrBomb = new ArrayList<>();
        arrBoom = new ArrayList<>();
        highScoreManager = new HighScoreManager();
        this.timeDead = TIME_DEAD;
    }

    private void initializeLoadMap() {
        drawMap.initializeDrawMap("/map_1/box.txt", "/map_1/enemy.txt", "/map_1/item.txt", "/map_1/shadow.txt");
    }

    public void drawBackground(Graphics2D g2D) {
        g2D.drawImage(ImageLoader.IMG_BACKGROUND_PLAY, 0, 0, WIDTH_GAME_PLAY, Gui.H_FRAME, null);
        g2D.drawImage(ImageLoader.IMG_BACKGROUND_INFO, WIDTH_GAME_PLAY, 0, Gui.W_FRAME - Gui.H_FRAME, Gui.H_FRAME, null);
    }

    public void drawInfo(Graphics2D g2d) {
        g2d.setFont(new Font("Serif", Font.BOLD, 30));
        g2d.setColor(Color.GREEN);
        g2d.drawString("Bomber Man", 710, 100);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Serif", Font.BOLD, 23));
        g2d.drawString("Heart:", 700, 140);
        g2d.drawString("Score:" + bomber.getScore(), 700, 180);
        Image imageHeart = ImageLoader.IMG_HEART;
        switch (bomber.getHeart()) {
            case 3:
                g2d.drawImage(imageHeart, 770, 120, null);
                g2d.drawImage(imageHeart, 800, 120, null);
                g2d.drawImage(imageHeart, 830, 120, null);
                break;
            case 2:
                g2d.drawImage(imageHeart, 770, 120, null);
                g2d.drawImage(imageHeart, 800, 120, null);
                break;
            case 1:
                g2d.drawImage(imageHeart, 770, 120, null);
                break;
        }
    }

    public void drawBomber(Graphics2D g2D) {
        bomber.draw(g2D);
    }

    public void drawItem(Graphics2D g2D) {
        for (int i = 0; i < drawMap.getArrItem().size(); i++) {
            drawMap.getArrItem().get(i).drawItem(g2D);
        }
    }

    public void drawBox(Graphics2D g2D) {
        for (int i = 0; i < drawMap.getArrBox().size(); i++) {
            drawMap.getArrBox().get(i).drawBox(g2D);
        }
    }

    public void drawShadow(Graphics2D g2D) {
        for (int i = 0; i < drawMap.getArrShadow().size(); i++) {
            drawMap.getArrShadow().get(i).drawBox(g2D);
        }
    }

    public void drawEnemy(Graphics2D g2D) {
        for (int i = 0; i < drawMap.getArrEnemy().size(); i++) {
            drawMap.getArrEnemy().get(i).draw(g2D);
        }
    }

    public void drawBomb(Graphics2D g2D) {
        for (int i = 0; i < arrBomb.size(); i++) {
            arrBomb.get(i).draw(g2D);
        }

        for (int i = 0; i < arrBoom.size(); i++) {////
            arrBoom.get(i).draw(g2D);
        }
    }


    public void moveBomber(int count) {
        bomber.move(count);
        bomber.checkCollisionVsPeople(drawMap.getArrBox(), arrBomb);
    }

    public void changeOrientBomber(int orient) {
        bomber.setOrient(orient);
    }

    public void moveEnemy(int count) {
        for (Enemy enemy : drawMap.getArrEnemy()) {
            enemy.move(count);
            enemy.checkCollisionVsPeople(drawMap.getArrBox(), arrBomb);
            enemy.randomOrient();
        }
    }

    /**
     * Kiểm tra thắng thua
     */
    public void checkWinAndLose() {
        if (bomber.getHeart() == 0) {// Lose
            type = 1;
            SoundManager.getInstance().getAudio(SoundManager.LOSE).play();
            highScoreManager.writeFile(bomber);
        }
        if (drawMap.getArrEnemy().size() == 0) {//Win
            type = 2;
            SoundManager.getInstance().getAudio(SoundManager.WIN).play();
            highScoreManager.writeFile(bomber);
        }

    }

    public void drawWinAndLose(Graphics2D g2D) {
        g2D.setFont(new Font("serif", Font.BOLD, 60));
        g2D.setColor(Color.RED);
        if (type == 1) {
            g2D.drawString("You Lose", 250, 300);
        } else if (type == 2) {
            g2D.drawString("You Win", 250, 300);
        }
    }

    /**
     * Tạo bomb khi ấn nút cách
     */

    public void createBomb() {
        int x = bomber.getX() + Bomber.WIDTH / 2;
        int y = bomber.getY() + Bomber.HEIGHT / 2;
        for (int i = 0; i < arrBomb.size(); i++) {
            if (arrBomb.get(i).checkBombVzBomb(x, y)) {
                return;
            }
        }
        if (arrBomb.size() >= bomber.getQuantityBomb()) {
            return;
        }
        SoundManager.getInstance().getAudio(SoundManager.BOMB).play();
        Bomb bomb = new Bomb(x, y, bomber.getSizeBomb(), Bomb.BOMB_DIE);
        arrBomb.add(bomb);
    }

    /**
     * Xét lại trạng thái cho bomber khi ra khỏi quả bomb
     */
    public void setRunBomber() {
        if (arrBomb.size() > 0) {
            if (arrBomb.get(arrBomb.size() - 1).checkImpactBombVsPeople(bomber) == false) {
                bomber.setSetRunPeople(Bomber.DISALLOW_RUN);
            }
        }
    }

    /**
     * Kiểm tra thời gian chết của bomb và nổ
     */
    public void checkDieBombAndBoom() {
        for (int i = 0; i < arrBomb.size(); i++) {
            arrBomb.get(i).diminishedTime();
            if (arrBomb.get(i).getTime() == 200) {
                int x = arrBomb.get(i).getX();
                int y = arrBomb.get(i).getY();
                int size = arrBomb.get(i).getSize();
                SoundManager.getInstance().getAudio(SoundManager.BONG_BANG).play();
                arrBoom.add(new Boom(x, y, size, Boom.BOOM_DIE, drawMap.getArrBox()));
                arrBomb.remove(i);
            }
        }


        for (int j = 0; j < arrBoom.size(); j++) {
            arrBoom.get(j).deadBoom();
            if (arrBoom.get(j).getTime() == 0) {
                arrBoom.remove(j);
            }
        }
    }

    /**
     * Kiểm tra va chạm giữa nổ với box,item,enemy,bomb
     */
    public void checkCollisionBoomVsAll() {
        for (int i = 0; i < arrBoom.size(); i++) {
            for (int j = 0; j < drawMap.getArrBox().size(); j++) {
                if (arrBoom.get(i).isCollisionBoomVsBox(drawMap.getArrBox().get(j))) {
                    drawMap.getArrBox().remove(j);
                    drawMap.getArrShadow().remove(j);
                }
            }

            for (int k = 0; k < arrBomb.size(); k++) {
                if (arrBoom.get(i).isCollisionBoomVsBomb(arrBomb.get(k))) {
                    Boom boom = new Boom(arrBomb.get(k).getX(), arrBomb.get(k).getY()
                            , arrBomb.get(k).getSize(), Boom.BOOM_DIE, drawMap.getArrBox());
                    arrBoom.add(boom);
                    arrBomb.remove(k);
                }
            }

            for (int e = 0; e < drawMap.getArrEnemy().size(); e++) {
                if (arrBoom.get(i).isCollisionBoomVsEnemy(drawMap.getArrEnemy().get(e))) {
                    bomber.setScore(bomber.getScore() + 100);
                    drawMap.getArrEnemy().remove(e);
                    SoundManager.getInstance().getAudio(SoundManager.MONSTER_DIE).play();
                }
            }
        }
    }

    public void setNewLocationOfBomber() {
        bomber.setNewBomber(350, 535);
    }

    public void checkBomberDead() {
        for (int i = 0; i < arrBoom.size(); i++) {
            if (arrBoom.get(i).isCollisionBoomVsBomber(bomber) && bomber.getStatus() == Bomber.BOMBER_ALIVE) {
                bomber.setImage(ImageLoader.IMG_BOMBER_DEAD);
                bomber.setHeart(bomber.getHeart() - 1);
                bomber.setStatus(Bomber.BOMBER_DEAD);
                SoundManager.instance.getAudio(SoundManager.BOMBER_DIE).play();
            }
        }

        for (int j = 0; j < drawMap.getArrEnemy().size(); j++) {
            if (bomber.isImpactBomberVsEnemy(drawMap.getArrEnemy().get(j)) && bomber.getStatus() == Bomber.BOMBER_ALIVE) {
                bomber.setImage(ImageLoader.IMG_GHOST);
                bomber.setHeart(bomber.getHeart() - 1);
                bomber.setStatus(Bomber.BOMBER_DEAD);
                SoundManager.instance.getAudio(SoundManager.BOMBER_DIE).play();
            }
        }
    }


    public void initializeBomber() {
        if (bomber.getStatus() == Bomber.BOMBER_DEAD) {
            timeDead--;
            if (timeDead == 0) {
                setNewLocationOfBomber();
                timeDead = TIME_DEAD;
            }
        }
    }

    public void checkCollisionItemVsBomber() {
        for (int i = 0; i < drawMap.getArrItem().size(); i++) {
            if (drawMap.getArrItem().get(i).checkImpactItemVsBomber(bomber)) {
                SoundManager.instance.getAudio(SoundManager.ITEM).play();
                switch (drawMap.getArrItem().get(i).getType()) {
                    case Item.ITEM_BOMB:
                        bomber.setQuantityBomb(bomber.getQuantityBomb() + 1);
                        drawMap.getArrItem().remove(i);
                        break;
                    case Item.ITEM_BOMB_SIZE:
                        bomber.setSizeBomb(bomber.getSizeBomb() + 1);
                        drawMap.getArrItem().remove(i);
                        break;
                    case Item.ITEM_SHOE:
                        bomber.setDelay(bomber.getDelay() - 1);
                        drawMap.getArrItem().remove(i);
                        break;
                    default:
                }
            }
        }
    }


    public Bomber getBomber() {
        return bomber;
    }

}

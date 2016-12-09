package com.t3h.bomb.manager;

import com.t3h.bomb.model.Box;
import com.t3h.bomb.model.Enemy;
import com.t3h.bomb.model.Item;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by HaiTran on 7/25/2016.
 */
public class DrawMap {
    private ArrayList<Box> arrBox;
    private ArrayList<Box> arrShadow;
    private ArrayList<Enemy> arrEnemy;
    private ArrayList<Item> arrItem;

    public DrawMap() {
        initializeContainer();
    }

    private void initializeContainer() {
        arrBox = new ArrayList<>();
        arrShadow = new ArrayList<>();
        arrEnemy = new ArrayList<>();
        arrItem = new ArrayList<>();
    }

    public void initializeDrawMap(String fileBox, String fileEnemy, String fileItem, String fileShadow) {
        loadMapBox(fileBox);
        loadMapShadow(fileShadow);
        loadMapEnemy(fileEnemy);
        loadMapItem(fileItem);
    }

    public void loadMapBox(String fileBox) {
        File fileB = new File(getURL(fileBox).getPath());
        try {
            RandomAccessFile raf = new RandomAccessFile(fileB, "rw");
            String result;
            int j = 0;
            while ((result = raf.readLine()) != null) {
                for (int i = 0; i < result.length(); i++) {
                    int x = i * Box.SIZE;
                    int y = j * Box.SIZE;
                    int type = result.charAt(i) - '0';
                    if (type == 1 || type == 2) {
                        arrBox.add(new Box(x, y, type));
                    }
                }
                j++;
            }
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMapShadow(String fileShadow) {
        File fileS = new File(getURL(fileShadow).getPath());
        try {
            RandomAccessFile raf = new RandomAccessFile(fileS, "rw");
            String result;
            int j = 0;
            while ((result = raf.readLine()) != null) {
                for (int i = 0; i < result.length(); i++) {
                    int x = i * Box.SIZE;
                    int y = j * Box.SIZE;
                    int type = result.charAt(i) - '0';
                    if (type == 3 || type == 4) {
                        arrShadow.add(new Box(x, y, type));
                    }
                }
                j++;
            }
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void loadMapItem(String fileItem) {
        File fileI = new File(getURL(fileItem).getPath());
        try {
            RandomAccessFile raf = new RandomAccessFile(fileI, "rw");
            String result;
            while ((result = raf.readLine()) != null) {
                String[] data = result.split(":");
                int x = Integer.parseInt(data[0]);
                int y = Integer.parseInt(data[1]);
                int type = Integer.parseInt(data[2]);
                arrItem.add(new Item(x, y, type));
            }
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadMapEnemy(String fileEnemy) {
        File fileE = new File(getURL(fileEnemy).getPath());
        try {
            RandomAccessFile raf = new RandomAccessFile(fileE, "rw");
            String result;
            while ((result = raf.readLine()) != null) {
                String[] data = result.split(":");
                int x = Integer.parseInt(data[0]);
                int y = Integer.parseInt(data[1]);
                int type = Integer.parseInt(data[2]);
                int orient = Integer.parseInt(data[3]);
                arrEnemy.add(new Enemy(x, y, type, orient, 10));
            }
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static URL getURL(String fileName) {
        return DrawMap.class.getResource("/res/maps/" + fileName);
    }

    public ArrayList<Box> getArrBox() {
        return arrBox;
    }

    public ArrayList<Enemy> getArrEnemy() {
        return arrEnemy;
    }

    public ArrayList<Box> getArrShadow() {
        return arrShadow;
    }

    public ArrayList<Item> getArrItem() {
        return arrItem;
    }
}

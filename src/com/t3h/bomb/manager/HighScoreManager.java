package com.t3h.bomb.manager;

import com.t3h.bomb.model.Bomber;
import com.t3h.bomb.score.HighScore;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Comparator;

public class HighScoreManager {
    private ArrayList<HighScore> arrScore;
    private RandomAccessFile raf;
    private File fileName;
    private Comparator<HighScore> scoreComparator;

    public HighScoreManager() {
        arrScore = new ArrayList<>();
        fileName = new File(getClass().getResource("/scores/Score.txt").getPath());
        scoreComparator = new Comparator<HighScore>() {
            @Override
            public int compare(HighScore o1, HighScore o2) {
                if (o1.getScore() > o2.getScore())
                    return -1;
                return 0;
            }
        };
    }

    public void writeFile(Bomber bomber) {
        int score = bomber.getScore();
        try {
            raf = new RandomAccessFile(fileName, "rw");
            String line, result = null;
            while ((line = raf.readLine()) != null) {
                result = line;
                String[] ss = line.split("_");
                arrScore.add(new HighScore(ss[0], Integer.parseInt(ss[1])));
            }
            String[] data = result.split("_");
            if (score > Integer.parseInt(data[1])) {
                String name = JOptionPane.showInputDialog(null, "Hãy nhập tên của bạn!");
                if (name.isEmpty() == true) {
                    return;
                }
                arrScore.add(new HighScore(name, score));
                arrScore.sort(scoreComparator);
                raf.seek(0);
                int length;
                if (arrScore.size() <= 10) {
                    length = arrScore.size();
                } else {
                    length = 10;
                }
                for (int i = 0; i < length; i++) {
                    int score1 = arrScore.get(i).getScore();
                    String name1 = arrScore.get(i).getName();
                    raf.writeBytes(name1);
                    raf.writeBytes("_");
                    raf.writeBytes(score1 + "");
                    raf.writeBytes("\r\n");
                }
                System.exit(0);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void readFile() {
        try {
            raf = new RandomAccessFile(fileName, "rw");
            raf.seek(0);
            String line;
            while ((line = raf.readLine()) != null) {
                byte[] data;
                data = line.getBytes("ISO-8859-1");
                line = new String(data, "UTF-8");
                String[] ss = line.split("_");
                arrScore.add(new HighScore(ss[0], Integer.parseInt(ss[1])));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawScore(Graphics2D graphics2D) {
        for (int i = 0; i < arrScore.size(); i++) {
            graphics2D.drawString(i + 1 + ")", 120, 130 + (i * 40));
            graphics2D.drawString(arrScore.get(i).getName(), 230, 130 + (i * 40));
            graphics2D.drawString(arrScore.get(i).getScore() + "", 700, 130 + (i * 40));
        }
    }
}

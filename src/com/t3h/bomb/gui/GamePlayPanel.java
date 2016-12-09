package com.t3h.bomb.gui;

import com.t3h.bomb.interfaces.OnClickListener;
import com.t3h.bomb.manager.GameManager;
import com.t3h.bomb.manager.ImageLoader;
import com.t3h.bomb.model.Bomber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.BitSet;

/**
 * Created by HaiTran on 7/25/2016.
 */
public class GamePlayPanel extends BaseContainer implements Runnable, ActionListener {
    public static final int WIDTH_BACK=700;
    public static final int HEIGHT_BACK =450;
    private OnClickListener onClickListener;
    private GameManager gameManager;
    private BitSet bitSet;
    private JButton btnBack;
    private ImageIcon image = ImageLoader.IMG_BUTTON_OK_1;
    private KeyAdapter keyAdapter;
    private int count = 1;
    private boolean isRunning = true;

    public GamePlayPanel() {
        super();
        initializeListener();
        startGame();
    }

    @Override
    public void initializeContainer() {
        gameManager = new GameManager();
        btnBack = new JButton();
    }

    @Override
    public void initializeComponent() {
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        requestFocus();
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        btnBack.setIcon(image);
        btnBack.setBounds(WIDTH_BACK, HEIGHT_BACK, image.getIconWidth(), image.getIconHeight());
        btnBack.setOpaque(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setBorderPainted(false);
        add(btnBack);
        gameManager.drawBackground(g2D);
        gameManager.drawItem(g2D);
        gameManager.drawBomb(g2D);
        gameManager.drawBox(g2D);
        gameManager.drawEnemy(g2D);
        gameManager.drawBomber(g2D);
        gameManager.drawShadow(g2D);
        gameManager.drawInfo(g2D);
        gameManager.drawWinAndLose(g2D);
    }

    public void startGame() {
        Thread thread = new Thread(this);
        thread.start();
    }

    private void initializeListener() {
        bitSet = new BitSet(256);
        keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                bitSet.set(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                bitSet.clear(e.getKeyCode());
            }
        };
        setFocusable(true);
        addKeyListener(keyAdapter);

        btnBack.addActionListener(this);
    }

    private void handleChangeBombOrient() {
        if (bitSet.get(KeyEvent.VK_LEFT)) {
            gameManager.changeOrientBomber(Bomber.LEFT);
            gameManager.moveBomber(count);
        } else if (bitSet.get(KeyEvent.VK_RIGHT)) {
            gameManager.changeOrientBomber(Bomber.RIGHT);
            gameManager.moveBomber(count);
        } else if (bitSet.get(KeyEvent.VK_UP)) {
            gameManager.changeOrientBomber(Bomber.UP);
            gameManager.moveBomber(count);
        } else if (bitSet.get(KeyEvent.VK_DOWN)) {
            gameManager.changeOrientBomber(Bomber.DOWN);
            gameManager.moveBomber(count);
        }
        if (bitSet.get(KeyEvent.VK_SPACE)) {
            gameManager.createBomb();
            gameManager.getBomber().setSetRunPeople(Bomber.ALLOW_RUN);
        }
    }

    @Override
    public void run() {
        while (isRunning) {
            count++;
            handleChangeBombOrient();
            gameManager.moveEnemy(count);
            gameManager.setRunBomber();
            gameManager.checkDieBombAndBoom();
            gameManager.checkCollisionBoomVsAll();
            gameManager.checkCollisionItemVsBomber();
            gameManager.checkBomberDead();
            gameManager.initializeBomber();
            gameManager.checkWinAndLose();
            repaint();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        onClickListener.onClickBackGame();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}

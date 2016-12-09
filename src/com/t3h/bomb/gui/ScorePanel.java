package com.t3h.bomb.gui;

import com.t3h.bomb.interfaces.OnClickListener;
import com.t3h.bomb.manager.HighScoreManager;
import com.t3h.bomb.manager.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by HaiTran on 7/27/2016.
 */
public class ScorePanel extends BaseContainer implements ActionListener {
    private OnClickListener onClickListener;
    private JButton btnBack;
    private HighScoreManager highScores;

    public ScorePanel() {
        super();
        initializeListener();
        highScores.readFile();
    }

    @Override
    public void initializeContainer() {
        btnBack = new JButton();
        highScores = new HighScoreManager();
    }

    @Override
    public void initializeComponent() {
        setLayout(null);
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        graphics2D.setColor(Color.YELLOW);
        graphics2D.setFont(new Font("serif", Font.BOLD, 40));
        graphics2D.drawString("HIGH SCORE", 300, 70);
        graphics2D.setColor(Color.WHITE);
        graphics2D.setStroke(new BasicStroke(2));
        graphics2D.drawLine(100, 100, 100, 520);
        graphics2D.drawLine(100, 100, 800, 100);
        graphics2D.drawLine(800, 100, 800, 520);
        graphics2D.drawLine(800, 520, 100, 520);
        graphics2D.setFont(new Font("serif", Font.BOLD, 30));
        highScores.drawScore(graphics2D);
        btnBack.setBounds(350, 520, 200, 80);
        btnBack.setIcon(ImageLoader.IMG_BUTTON_OK_1);
        btnBack.setOpaque(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setBorderPainted(false);
        add(btnBack);
    }

    private void initializeListener() {
        btnBack.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        onClickListener.onClickBackScore();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}

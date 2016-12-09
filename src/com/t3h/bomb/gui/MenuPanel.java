package com.t3h.bomb.gui;

import com.t3h.bomb.interfaces.OnClickListener;
import com.t3h.bomb.manager.ImageLoader;
import com.t3h.bomb.manager.SoundManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by HaiTran on 7/27/2016.
 */
public class MenuPanel extends BaseContainer implements ActionListener {
    private OnClickListener onClickListener;
    public static final int WIDTH_BUTTON = 350;
    public static final String PLAY_PANEL="Play";
    public static final String OPTION_PANEL="Option";
    public static final String SCORE_PANEL="Score";
    public static final String EXIT_PANEL="Exit";
    private JButton btnPlay;
    private JButton btnOption;
    private JButton btnScore;
    private JButton btnExit;
    private JLabel lbBackground;

    public MenuPanel() {
        super();
        initializeBackground();
        initializeListener();
    }

    private void initializeBackground() {
        lbBackground = new JLabel();
        lbBackground.setBounds(0, 0, Gui.W_FRAME, Gui.H_FRAME);
        lbBackground.setIcon(ImageLoader.IMG_BACKGROUND_MENU);
        add(lbBackground);
    }

    private JButton setButton(int x, int y, ImageIcon image) {
        JButton button = new JButton();
        button.setBounds(x, y, image.getIconWidth(), image.getIconHeight());
        button.setIcon(image);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        return button;
    }

    @Override
    public void initializeContainer() {}

    @Override
    public void initializeComponent() {
        setLayout(null);
        btnPlay = setButton(WIDTH_BUTTON, 150, ImageLoader.IMG_BUTTON_PLAY);
        btnOption = setButton(WIDTH_BUTTON, 250, ImageLoader.IMG_BUTTON_OPTION);
        btnScore = setButton(WIDTH_BUTTON, 350, ImageLoader.IMG_BUTTON_SCORE);
        btnExit = setButton(WIDTH_BUTTON, 450, ImageLoader.IMG_BUTTON_EXIT);
        add( btnPlay);
        add( btnOption);
        add( btnScore);
        add( btnExit);
    }

    private void initializeListener() {
        btnPlay.addMouseListener(mouseAdapter);
        btnOption.addMouseListener(mouseAdapter);
        btnScore.addMouseListener(mouseAdapter);
        btnExit.addMouseListener(mouseAdapter);
        btnPlay.addActionListener( this);
        btnPlay.setActionCommand("PLAY");
        btnOption.addActionListener(this);
        btnOption.setActionCommand("OPTION");
        btnScore.addActionListener(this);
        btnScore.setActionCommand("SCORE");
        btnExit.addActionListener(this);
        btnExit.setActionCommand("EXIT");
    }

    private MouseAdapter mouseAdapter = new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            super.mouseEntered(e);
            if (e.getSource().equals(btnPlay)) {
                btnPlay.setIcon(ImageLoader.IMG_BUTTON_PLAY_2);
            }
            if (e.getSource().equals(btnOption)) {
                btnOption.setIcon(ImageLoader.IMG_BUTTON_OPTION_2);
            }
            if (e.getSource().equals(btnScore)) {
                btnScore.setIcon(ImageLoader.IMG_BUTTON_SCORE_2);
            }
            if (e.getSource().equals(btnExit)) {
                btnExit.setIcon(ImageLoader.IMG_BUTTON_EXIT_2);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            super.mouseExited(e);
            if (e.getSource().equals(btnPlay)) {
                btnPlay.setIcon(ImageLoader.IMG_BUTTON_PLAY);
            }
            if (e.getSource().equals(btnOption)) {
                btnOption.setIcon(ImageLoader.IMG_BUTTON_OPTION);
            }
            if (e.getSource().equals(btnScore)) {
                btnScore.setIcon(ImageLoader.IMG_BUTTON_SCORE);
            }
            if (e.getSource().equals(btnExit)) {
                btnExit.setIcon(ImageLoader.IMG_BUTTON_EXIT);
            }
        }
    };

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "PLAY":
                SoundManager.getInstance().getAudio(SoundManager.MENU).stop();
                SoundManager.getInstance().getAudio(SoundManager.PLAY_GAME).loop();
                onClickListener.onClick(PLAY_PANEL);
                break;
            case "OPTION":
                onClickListener.onClick(OPTION_PANEL);
                break;
            case "SCORE":
                onClickListener.onClick(SCORE_PANEL);
                break;
            case "EXIT":
                onClickListener.onClick(EXIT_PANEL);
                break;
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }


}

package com.t3h.bomb.gui;

import com.t3h.bomb.interfaces.OnClickListener;
import com.t3h.bomb.manager.SoundManager;

import javax.swing.*;
import java.awt.*;

/**
 * Created by HaiTran on 7/27/2016.
 */
public class ManagerPanel extends BaseContainer implements OnClickListener {
    private GamePlayPanel gamePlayPanel;
    private MenuPanel menuPanel;
    private OptionPanel optionPanel;
    private ScorePanel scorePanel;
    private CardLayout cardLayout;

    public ManagerPanel() {
        super();
    }

    @Override
    public void initializeContainer() {
        cardLayout = new CardLayout();
        menuPanel = new MenuPanel();
        gamePlayPanel=new GamePlayPanel();
        optionPanel=new OptionPanel();
        scorePanel = new ScorePanel();
    }

    @Override
    public void initializeComponent() {
        setLayout(cardLayout);
        add(menuPanel);
        SoundManager.getInstance().getAudio(SoundManager.MENU).loop();
        gamePlayPanel.setOnClickListener(this);
        menuPanel.setOnClickListener(this);
        optionPanel.setOnClickListener(this);
        scorePanel.setOnClickListener(this);
    }

    @Override
    public void onClick(String name) {
        switch (name) {
            case MenuPanel.PLAY_PANEL:
                remove(menuPanel);
                if (gamePlayPanel == null) {
                    gamePlayPanel = new GamePlayPanel();
                    add(gamePlayPanel);
                } else {
                    add(gamePlayPanel);
                }
                break;
            case MenuPanel.OPTION_PANEL:
                remove(menuPanel);
                if (optionPanel == null) {
                    optionPanel = new OptionPanel();
                } else {
                    add(optionPanel);
                }
                break;
            case MenuPanel.SCORE_PANEL:
                remove(menuPanel);
                if (scorePanel == null) {
                    scorePanel = new ScorePanel();
                } else {
                    add(scorePanel);
                }
                break;
            case MenuPanel.EXIT_PANEL:
                int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát chương trình", "Exit", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
                break;
        }
        revalidate();
        repaint();
    }

    @Override
    public void onClickBackScore() {
        remove(scorePanel);
        backMenu();
    }

    @Override
    public void onClickBackOption() {
        remove(optionPanel);
        backMenu();
    }

    @Override
    public void onClickBackGame() {
        remove(gamePlayPanel);
        SoundManager.getInstance().getAudio(SoundManager.PLAY_GAME).stop();
        SoundManager.getInstance().getAudio(SoundManager.MENU).loop();
        backMenu();
    }

    public void backMenu() {
        if (menuPanel == null) {
            menuPanel = new MenuPanel();
        } else {
            add(menuPanel);
        }
        revalidate();
        repaint();
    }

}

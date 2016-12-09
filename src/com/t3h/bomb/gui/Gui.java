package com.t3h.bomb.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by HaiTran on 7/25/2016.
 */
public class Gui extends JFrame {
    public static final int W_FRAME = 910;
    public static final int H_FRAME = 660;
    private ManagerPanel managerPanel;
    private GamePlayPanel gamePlayPanel = new GamePlayPanel();
    private CardLayout cardLayout;

    public Gui() {
        initializeGui();
        initializeComponent();
    }

    private void initializeGui() {
        cardLayout = new CardLayout();
        managerPanel = new ManagerPanel();
    }

    private void initializeComponent() {
        setSize(W_FRAME, H_FRAME);
        setTitle("GAME BOMB");
        setLayout(cardLayout);
        add(managerPanel);
        //add(gamePlayPanel);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

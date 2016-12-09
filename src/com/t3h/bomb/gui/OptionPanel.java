package com.t3h.bomb.gui;

import com.t3h.bomb.interfaces.OnClickListener;
import com.t3h.bomb.manager.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by HaiTran on 7/27/2016.
 */
public class OptionPanel extends BaseContainer implements ActionListener{
    private OnClickListener onClickListener;
    private JButton btnOk;

    public OptionPanel(){
        super();
        initializeListener();
    }

    @Override
    public void initializeContainer() {
        btnOk = new JButton();
    }

    @Override
    public void initializeComponent() {
        setLayout(null);
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D=(Graphics2D)g;
        graphics2D.drawImage(ImageLoader.IMG_BACKGROUND_OPTION,85,20,null);
        btnOk.setBounds(350, 520, 200, 80);
        btnOk.setIcon(ImageLoader.IMG_BUTTON_OK_1);
        btnOk.setOpaque(false);
        btnOk.setContentAreaFilled(false);
        btnOk.setBorderPainted(false);
        add(btnOk);
    }
    private void initializeListener() {
        btnOk.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        onClickListener.onClickBackOption();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}

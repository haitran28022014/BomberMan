package com.t3h.bomb.gui;

import javax.swing.*;

/**
 * Created by HaiTran on 7/25/2016.
 */
public abstract class BaseContainer extends JPanel {
    public BaseContainer() {
        initializeContainer();
        initializeComponent();
    }

    public abstract void initializeContainer();

    public abstract void initializeComponent();
}

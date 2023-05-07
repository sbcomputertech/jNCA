package com.calliefox.jnca.ui;

import javax.swing.*;

public class ManagerWindow extends JFrame {
    public ManagerWindow() {
        setSize(200, 400);
        setTitle("jNCA Manager");
        ManagerPanel panel = new ManagerPanel();
        add(panel);
        setVisible(true);
    }
}

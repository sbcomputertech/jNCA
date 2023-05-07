package com.calliefox.jnca.ui;

import javax.swing.*;

public class ManagerWindow extends JFrame {
    public ManagerWindow(JncaApplication app) {
        setSize(600, 300);
        setTitle("jNCA Manager");
        ManagerPanel panel = new ManagerPanel(app);
        add(panel);
        setVisible(true);
    }
}

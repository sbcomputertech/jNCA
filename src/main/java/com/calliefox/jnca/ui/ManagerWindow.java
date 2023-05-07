package com.calliefox.jnca.ui;

import javax.swing.*;
import java.awt.*;

public class ManagerWindow extends JFrame {
    private ManagerPanel panel;
    public ManagerWindow() {
        setSize(200, 400);
        setTitle("jNCA Manager");
        panel = new ManagerPanel();
        add(panel);
        setVisible(true);
    }
}

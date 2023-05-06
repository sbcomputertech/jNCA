package com.calliefox.jnca.ui;

import com.calliefox.jnca.SimTimerTask;

import javax.swing.*;

public class JncaApplication extends JFrame {
    public final CellGridPanel cellGridPanel;
    public final java.util.Timer simTimer;
    public JncaApplication() {
        super("jNCA Window");
        setSize(1000, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        cellGridPanel = new CellGridPanel();
        add(cellGridPanel);

        setVisible(true);
        simTimer = new java.util.Timer();
        simTimer.scheduleAtFixedRate(new SimTimerTask(cellGridPanel), 0, 500);
    }
}

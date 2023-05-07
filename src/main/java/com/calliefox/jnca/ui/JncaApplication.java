package com.calliefox.jnca.ui;

import com.calliefox.jnca.SimTimerTask;
import com.calliefox.jnca.Utils;
import com.calliefox.jnca.paralell.CellIterWorker;

import javax.swing.*;
import java.awt.*;

public class JncaApplication extends JFrame {
    public final CellGridManager cellGridManager;
    public final java.util.Timer simTimer;
    public JncaApplication() {
        setTitle("jNCA Window");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cellGridManager = new CellGridManager();
        simTimer = new java.util.Timer();
        simTimer.scheduleAtFixedRate(new SimTimerTask(cellGridManager, this), 0, 500);

        cellGridManager.cells[50][50].value = 1;
        add(new GridPanel());
        setVisible(true);
    }

    public class GridPanel extends JComponent {
        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            int size = 5;
            new CellIterWorker(cellGridManager, (x, y, p) -> {
                float v = p.cells[x][y].value;
                Color c = Utils.lerpColor(Color.BLACK, Color.MAGENTA, v);
                g2d.setColor(c);
                g2d.fillRect(x * size, y * size, size, size);
            }).execute();
        }
    }
}

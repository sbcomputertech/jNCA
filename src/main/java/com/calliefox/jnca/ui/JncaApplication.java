package com.calliefox.jnca.ui;

import com.calliefox.jnca.SimTimerTask;
import com.calliefox.jnca.Utils;
import com.calliefox.jnca.paralell.CellIterWorker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class JncaApplication extends JFrame {
    public final CellGridManager cellGridManager;
    public final java.util.Timer simTimer;
    public JncaApplication() {
        setTitle("jNCA Window");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        cellGridManager = new CellGridManager();
        simTimer = new java.util.Timer();
        simTimer.scheduleAtFixedRate(new SimTimerTask(cellGridManager, this), 0, 50);

        int i = cellGridManager.size / 2;
        cellGridManager.cells[i][i].value = 1;

        setLayout(new GridLayout(1, 1));
        GridPanel panel = new GridPanel();
        add(panel);
        setVisible(true);
    }

    public class GridPanel extends JComponent implements MouseListener {
        public GridPanel() {
            super();
            addMouseListener(this);
            setBounds(0, 0, 1000, 1000);
        }
        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            int sizeX = 4;
            int sizeY = 4;
            new CellIterWorker(cellGridManager, (x, y, p) -> {
                float v = p.cells[x][y].value;
                Color c = Utils.lerpColor(Color.BLACK, ManagerPanel.colour.getColor(), v);
                g2d.setColor(c);
                g2d.fillRect(x * sizeX, y * sizeY, sizeX, sizeY);
                lastX = x * sizeX;
                lastY = y * sizeY;
            }).execute();
        }

        public int lastX;
        public int lastY;
        public int x;
        public int y;

        @Override
        public void mouseClicked(MouseEvent e) {
            x = (int) Utils.clamp(Utils.remap(0, lastX, 0, cellGridManager.size, e.getX()), 0, cellGridManager.size - 1);
            y = (int) Utils.clamp(Utils.remap(0, lastY, 0, cellGridManager.size, e.getY()), 0, cellGridManager.size - 1);
            Utils.log("Click: " + x + ", " + y);
            cellGridManager.cells[x][y].value = 1 - cellGridManager.cells[x][y].value;
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}

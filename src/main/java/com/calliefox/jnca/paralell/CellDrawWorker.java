package com.calliefox.jnca.paralell;

import com.calliefox.jnca.ui.Cell;
import com.calliefox.jnca.ui.CellGridPanel;

import javax.swing.*;

public class CellDrawWorker extends SwingWorker<Void, Void> {
    private final Cell c;

    public CellDrawWorker(int x, int y, CellGridPanel gp) {
        this.c = gp.cells[x][y];
    }

    @Override
    protected Void doInBackground() {
        c.tick();
        return null;
    }
}

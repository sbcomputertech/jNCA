package com.calliefox.jnca.paralell;

import com.calliefox.jnca.TriFunction;
import com.calliefox.jnca.ui.CellGridPanel;

import javax.swing.*;
import java.util.function.Function;

public class CellIterWorker extends SwingWorker<Void, Void> {
    private final CellGridPanel panel;
    private final TriFunction<Integer, Integer, CellGridPanel> func;
    private final int size;

    public CellIterWorker(CellGridPanel panel, TriFunction<Integer, Integer, CellGridPanel> func) {
        this.panel = panel;
        this.func = func;
        this.size = panel.size;
    }

    @Override
    protected Void doInBackground() {
        for(int x = 0; x < size; x++) {
            for(int y = 0; y < size; y++) {
                func.apply(x, y, panel);
            }
        }
        return null;
    }
}

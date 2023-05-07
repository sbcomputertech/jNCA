package com.calliefox.jnca.paralell;

import com.calliefox.jnca.TriFunction;
import com.calliefox.jnca.ui.CellGridManager;

public class CellIterWorker {
    private final CellGridManager panel;
    private final TriFunction<Integer, Integer, CellGridManager> func;
    private final int size;

    public CellIterWorker(CellGridManager panel, TriFunction<Integer, Integer, CellGridManager> func) {
        this.panel = panel;
        this.func = func;
        this.size = panel.size;
    }

    public void execute() {
        for(int x = 0; x < size; x++) {
            for(int y = 0; y < size; y++) {
                func.apply(x, y, panel);
            }
        }
    }
}

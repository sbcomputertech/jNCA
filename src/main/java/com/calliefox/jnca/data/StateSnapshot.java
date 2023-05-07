package com.calliefox.jnca.data;

import com.calliefox.jnca.paralell.CellIterWorker;
import com.calliefox.jnca.ui.CellGridPanel;

public class StateSnapshot {
    public float[][] values;
    public static StateSnapshot get(CellGridPanel panel) {
        StateSnapshot state = new StateSnapshot();
        state.values = new float[panel.size][panel.size];
        new CellIterWorker(panel, (x, y, p) -> state.values[x][y] = p.cells[x][y].value).run();
        return state;
    }

    public void apply(CellGridPanel panel) {
        new CellIterWorker(panel, (x, y, p) -> p.cells[x][y].value = values[x][y]).execute();
    }
}

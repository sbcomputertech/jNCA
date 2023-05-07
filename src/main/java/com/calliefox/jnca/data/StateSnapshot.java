package com.calliefox.jnca.data;

import com.calliefox.jnca.paralell.CellIterWorker;
import com.calliefox.jnca.ui.CellGridManager;

public class StateSnapshot {
    public float[][] values;
    public static StateSnapshot get(CellGridManager panel) {
        StateSnapshot state = new StateSnapshot();
        state.values = new float[panel.size][panel.size];
        new CellIterWorker(panel, (x, y, p) -> state.values[x][y] = p.cells[x][y].value).execute();
        return state;
    }

    public void apply(CellGridManager panel) {
        new CellIterWorker(panel, (x, y, p) -> p.cells[x][y].value = values[x][y]).execute();
    }
}

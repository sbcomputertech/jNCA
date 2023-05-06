package com.calliefox.jnca.data;

import com.calliefox.jnca.ui.Cell;
import com.calliefox.jnca.ui.CellGridPanel;

public class StateSnapshot {
    public float[][] values;
    public static StateSnapshot get(CellGridPanel panel) {
        StateSnapshot state = new StateSnapshot();
        state.values = new float[panel.size][panel.size];
        for(int x = 0; x < panel.size; x++) {
            for(int y = 0; y < panel.size; y++) {
                Cell c = panel.cells[x][y];
                state.values[x][y] = c.value;
            }
        }
        return state;
    }

    public void apply(CellGridPanel panel) {
        for(int x = 0; x < panel.size; x++) {
            for(int y = 0; y < panel.size; y++) {
                panel.cells[x][y].value = values[x][y];
            }
        }
    }
}

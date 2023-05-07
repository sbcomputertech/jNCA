package com.calliefox.jnca.ui;

import com.calliefox.jnca.SimulationMode;
import com.calliefox.jnca.Simulator;
import com.calliefox.jnca.data.StateSnapshot;
import com.calliefox.jnca.paralell.CellIterWorker;

public class CellGridManager {
    public final int size = 200;
    public final Cell[][] cells;
    public CellGridManager() {
        cells = new Cell[size][size];
        for(int x = 0; x < size; x++) {
            for(int y = 0; y < size; y++) {
                cells[x][y] = new Cell();
            }
        }
    }

    public void simulate(SimulationMode mode) {
        StateSnapshot state = StateSnapshot.get(this);
        Simulator.simulateWithWorker(mode, state, this);
        state.apply(this);

        new CellIterWorker(this, (x, y, p) -> p.cells[x][y].tick()).execute();
    }
}

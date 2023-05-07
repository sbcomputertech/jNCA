package com.calliefox.jnca.ui;

import com.calliefox.jnca.SimulationMode;
import com.calliefox.jnca.Simulator;
import com.calliefox.jnca.data.StateSnapshot;
import com.calliefox.jnca.paralell.CellDrawWorker;
import com.calliefox.jnca.paralell.CellIterWorker;

import javax.swing.*;
import java.awt.*;

public class CellGridPanel extends JPanel {
    public final int size = 100;
    public Cell[][] cells;
    public CellGridPanel() {
        setLayout(new GridLayout(size, size));
        cells = new Cell[size][size];
        for(int x = 0; x < size; x++) {
            for(int y = 0; y < size; y++) {
                cells[x][y] = new Cell();
                add(cells[x][y]);
            }
        }
    }

    public void simulate(SimulationMode mode) {
        StateSnapshot state = StateSnapshot.get(this);
        Simulator.simulateWithWorker(mode, state, this);
        state.apply(this);

        new CellIterWorker(this, (x, y, p) -> new CellDrawWorker(x, y, p).execute()).execute();
    }
}

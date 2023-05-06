package com.calliefox.jnca;

import com.calliefox.jnca.data.StateSnapshot;
import com.calliefox.jnca.ui.CellGridPanel;

import javax.swing.*;

public class SimulationWorker extends SwingWorker<StateSnapshot, StateSnapshot> {
    private final SimulationMode mode;
    private final StateSnapshot state;
    private final CellGridPanel panel;
    public SimulationWorker(SimulationMode mode, StateSnapshot state, CellGridPanel panel) {
        this.mode = mode;
        this.state = state;
        this.panel = panel;
    }
    @Override
    protected StateSnapshot doInBackground() {
        StateSnapshot newState = Simulator.simulate(mode, state);
        newState.apply(panel);
        return newState;
    }
}

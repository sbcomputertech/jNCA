package com.calliefox.jnca.paralell;

import com.calliefox.jnca.SimulationMode;
import com.calliefox.jnca.Simulator;
import com.calliefox.jnca.data.StateSnapshot;
import com.calliefox.jnca.ui.CellGridManager;

public class SimulationWorker {
    private final SimulationMode mode;
    private final StateSnapshot state;
    private final CellGridManager panel;
    public SimulationWorker(SimulationMode mode, StateSnapshot state, CellGridManager panel) {
        this.mode = mode;
        this.state = state;
        this.panel = panel;
    }
    public void execute() {
        StateSnapshot newState = Simulator.simulate(mode, state);
        newState.apply(panel);
    }
}

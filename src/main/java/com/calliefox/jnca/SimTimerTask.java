package com.calliefox.jnca;

import com.calliefox.jnca.data.ProgramState;
import com.calliefox.jnca.ui.CellGridPanel;

import java.util.TimerTask;

public class SimTimerTask extends TimerTask {
    private final CellGridPanel cellGridPanel;
    public SimTimerTask(CellGridPanel panel) {
        super();
        cellGridPanel = panel;
    }
    @Override
    public void run() {
        // Utils.log("simTimer fired!");
        if(!ProgramState.running) return;
        cellGridPanel.simulate(ProgramState.mode);
    }
}

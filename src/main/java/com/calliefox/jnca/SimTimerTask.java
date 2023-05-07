package com.calliefox.jnca;

import com.calliefox.jnca.data.ProgramState;
import com.calliefox.jnca.ui.CellGridManager;
import com.calliefox.jnca.ui.JncaApplication;

import java.util.TimerTask;

public class SimTimerTask extends TimerTask {
    private final CellGridManager cellGridManager;
    private final JncaApplication application;
    public SimTimerTask(CellGridManager panel, JncaApplication application) {
        super();
        cellGridManager = panel;
        this.application = application;
    }
    @Override
    public void run() {
        // Utils.log("simTimer fired!");
        if(!ProgramState.running) return;
        cellGridManager.simulate(ProgramState.mode);
        application.repaint();
    }
}

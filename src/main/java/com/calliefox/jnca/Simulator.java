package com.calliefox.jnca;

import com.calliefox.jnca.data.StateSnapshot;
import com.calliefox.jnca.ui.Cell;
import com.calliefox.jnca.ui.CellGridPanel;

public class Simulator {
    public static void simulateWithWorker(SimulationMode mode, StateSnapshot state, CellGridPanel panel) {
        SimulationWorker worker = new SimulationWorker(mode, state, panel);
        worker.execute();
    }
    public static StateSnapshot simulate(SimulationMode mode, StateSnapshot state) {
        switch (mode) {
            case None -> {
                return sinulateNone(state);
            }
            case NADT -> {
                return simulateNADT(state);
            }
            case Conway -> {
                return simulateConway(state);
            }
            default -> throw new IllegalStateException("Unexpected value: " + mode);
        }
    }
    // just draw with cells ig
    private static StateSnapshot sinulateNone(StateSnapshot state) {
        Cell.useDT = false;
        return state;
    }
    // Neighbor Average and Decay Timer
    private static StateSnapshot simulateNADT(StateSnapshot state) {
        Cell.useDT = true;
        for(int x = 0; x < state.values.length; x++) {
            for(int y = 0; y < state.values[x].length; y++) {
                float v = state.values[x][y];

                //      N
                //      |
                // W ---+--- E
                //      |
                //      S

                // north neighbor
                float nN;
                if(x - 1 < 0) {
                    // Utils.log("North neigbor went out of bounds!");
                    nN = v;
                } else {
                    nN = state.values[x - 1][y];
                }

                // south neighbor
                float nS;
                if(x + 1 >= state.values.length) {
                    // Utils.log("South neighbor went out of bounds!");
                    nS = v;
                } else {
                    nS = state.values[x + 1][y];
                }

                // west neighbor
                float nW;
                if(y - 1 < 0) {
                    // Utils.log("West neighbor went out of bounds!");
                    nW = v;
                } else {
                    nW = state.values[x][y - 1];
                }

                // east neighbor
                float nE;
                if(y + 1 >= state.values[x].length) {
                    // Utils.log("East neighbor went out of bounds!");
                    nE = v;
                } else {
                    nE = state.values[x][y + 1];
                }

                float nAverage = (nN + nS + nW + nE) / 4;
                state.values[x][y] = Utils.clamp(v + nAverage, 0, 1);
            }
        }
        return state;
    }
    // Conway's game of life
    private static StateSnapshot simulateConway(StateSnapshot state) {
        Cell.useDT = false;
        for(int x = 0; x < state.values.length; x++) {
            for(int y = 0; y < state.values[x].length; y++) {
                //  NW  N  NE
                //    \ | /
                // W ---+--- E
                //    / | \
                //  SW  S  SE

                float o = state.values[x][y];
                float n = x - 1 >= 0 ? state.values[x - 1][y] : -1;
                float s = x + 1 < state.values.length ? state.values[x + 1][y] : -1;
                float w = y - 1 >= 0 ? state.values[x][y - 1] : -1;
                float e = y + 1 < state.values[x].length ? state.values[x][y + 1] : -1;
                float nw = x - 1 >= 0 && y - 1 >= 0 ? state.values[x - 1][y - 1] : -1;
                float ne = x - 1 >= 0 && y + 1 < state.values[x].length ? state.values[x - 1][y + 1] : -1;
                float sw = x + 1 < state.values.length && y - 1 >= 0 ? state.values[x + 1][y - 1] : -1;
                float se = x + 1 < state.values.length && y + 1 < state.values[x].length ? state.values[x + 1][y + 1] : -1;

                float[] all = new float[] { n, s, w, e, nw, ne, sw, se };
                int aliveCount = Utils.oneCount(all);

                if(o == 1) {
                    if (aliveCount < 2 || aliveCount > 3) {
                        o = 0;
                    }
                } else {
                    if(aliveCount == 3) {
                        o = 1;
                    }
                }

                state.values[x][y] = o;
            }
        }
        return state;
    }
}

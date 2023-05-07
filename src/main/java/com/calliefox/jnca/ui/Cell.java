package com.calliefox.jnca.ui;

public class Cell {
    public static boolean useDT;
    public float value;
    public float decayTime;
    public boolean decaying;
    public float unDecayTime;
    public Cell() {
    }

    public void tick() {
        if(useDT) decay();
    }
    private void decay() {
        if(!decaying && value >= 1) {
            decaying = true;
            unDecayTime = 0;
            decayTime = 0;
        }
        if(decaying) {
            decayTime ++;
        }
        if(decaying && decayTime >= 5) {
            value = 0;
        }
        if(decaying && value <= 0) {
            unDecayTime ++;
        }
        if(unDecayTime >= 7) {
            decaying = false;
        }
    }
}

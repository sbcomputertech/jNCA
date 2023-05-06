package com.calliefox.jnca.ui;

import com.calliefox.jnca.Utils;

import javax.swing.*;
import java.awt.*;

public class Cell extends JButton {
    public static boolean useDT;
    public float value;
    public float decayTime;
    public boolean decaying;
    public float unDecayTime;
    public Cell() {
        setBorderPainted(false);
        setFocusPainted(false);
        addActionListener(e -> value = 1 - value);
    }

    public void tick() {
        setBackground(Utils.lerpColor(Color.BLACK, Color.MAGENTA, value));
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

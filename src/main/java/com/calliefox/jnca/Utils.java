package com.calliefox.jnca;

import java.awt.*;

public class Utils {
    public static Color lerpColor(Color cA, Color cB, float t) {
        float r = cA.getRed() + t * (cB.getRed() - cA.getRed());
        float g = cA.getGreen() + t * (cB.getGreen() - cA.getGreen());
        float b = cA.getBlue() + t * (cB.getBlue() - cA.getBlue());
        return new Color((int)r, (int)g, (int)b);
    }

    public static float clamp(float val, float min, float max) {
        if(val > max) return max;
        if(val < min) return min;
        return val;
    }

    public static void log(String txt) {
        System.out.println("[log] " + txt);
    }

    public static int oneCount(float[] input) {
        int count = 0;
        for(float v : input) {
            if(v == 1) count += 1;
        }
        return count;
    }
}

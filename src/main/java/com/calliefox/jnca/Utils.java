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
        return Math.max(val, min);
    }

    public static float lerp(float a, float b, float t) {
        return a + t * (b - a);
    }
    public static float invLerp(float a, float b, float v) {
        return (v - a) / (b - a);
    }

    public static float remap(float minI, float maxI, float minO, float maxO, float v) {
        float t = invLerp(minI, maxI, v);
        return lerp(minO, maxO, t);
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

package com.calliefox.jnca;

import com.calliefox.jnca.ui.JncaApplication;
import com.calliefox.jnca.ui.ManagerWindow;

public class Main {
    public static void main(String[] args) {
        System.out.println("jNCA v1.0.0!");
        new ManagerWindow(new JncaApplication());
    }
}

package com.calliefox.jnca.ui;

import com.calliefox.jnca.SimulationMode;
import com.calliefox.jnca.Utils;
import com.calliefox.jnca.data.ProgramState;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.*;

public class ManagerPanel extends JPanel {
    public static JColorChooser colour;
    public ManagerPanel(JncaApplication app) {
        setLayout(new FlowLayout());

        JButton pause = new JButton("Start");
        pause.addActionListener(e -> {
            ProgramState.running = !ProgramState.running;
            Utils.log("Toggled running: " + ProgramState.running);
            pause.setText(ProgramState.running ? "Pause" : "Resume");
        });
        add(pause);

        JButton step = new JButton("Step");
        step.addActionListener(e -> {
            app.cellGridManager.simulate(ProgramState.mode);
            app.repaint();
        });
        add(step);

        JComboBox<String> mode = new JComboBox<>();
        for(SimulationMode val : SimulationMode.values()) {
            mode.addItem(val.name());
        }
        mode.addActionListener(e -> {
            String newMode = (String) mode.getSelectedItem();
            Utils.log("Switched mode to: " + newMode);
            if(newMode == null) newMode = "None";
            ProgramState.mode = Enum.valueOf(SimulationMode.class, newMode);
        });
        add(mode);

        colour = new JColorChooser(Color.magenta);
        for(AbstractColorChooserPanel accp : colour.getChooserPanels()) {
            if(!accp.getDisplayName().equals("RGB")) {
                colour.removeChooserPanel(accp);
            }
        }
        colour.setPreviewPanel(new JPanel());
        add(colour);
    }
}

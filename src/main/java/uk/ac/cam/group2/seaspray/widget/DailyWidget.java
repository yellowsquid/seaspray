package uk.ac.cam.group2.seaspray.widget;

import java.awt.*;
import java.io.*;
import java.text.*;
import javax.swing.*;
import uk.ac.cam.group2.seaspray.data.*;

public class DailyWidget extends JPanel {
    public DailyWidget(DailyData d) {
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);

        // Global constraints
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 1.0;

        // Day of week
        c.weighty = 0.2;
        c.gridy = 0;
        c.fill = GridBagConstraints.VERTICAL;
        c.ipadx = 16;
        String label = String.format("%ta", d.getDate());
        JLabel title = new JLabel(label);

        // TODO: styling
        Font labelFont = title.getFont();
        title.setFont(new Font(labelFont.getName(), Font.BOLD, 18));
        add(title, c);

        c.weighty = 1.0;
        c.gridy = 1;
        c.ipadx = 0;
        c.fill = GridBagConstraints.BOTH;
        add(new HourlyWidget(d.getHours().get(3)), c);

        c.weighty = 1.0;
        c.gridy = 2;
        c.ipadx = 0;
        c.fill = GridBagConstraints.BOTH;
        add(new HourlyWidget(d.getHours().get(5)), c);
    }
}

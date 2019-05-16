package uk.ac.cam.group2.seaspray.widget;

import java.awt.*;

import java.util.List;

import javax.swing.*;

import uk.ac.cam.group2.seaspray.data.HourlyData;

/**
 * Conditions for next 24 hours.
 * FIXME: I do nothing.
 */
public class HourlyPanel extends JPanel {
    public HourlyPanel(List<HourlyData> hourlyDatas) {
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;

        for (HourlyData data : hourlyDatas) {
            add(new HourlyWidget(data), c);
            c.gridy++;
        }
    }
}

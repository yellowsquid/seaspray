package uk.ac.cam.group2.seaspray.widget;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import javax.swing.*;
import uk.ac.cam.group2.seaspray.data.TideData;

public class TidePanel extends JPanel {
    public TidePanel(List<TideData> data) {
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        GridBagConstraints c = new GridBagConstraints();

        // Global constraints
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.fill = GridBagConstraints.NONE;

        if (data.size() < 4) {
            JLabel message = new JLabel("Data not available");
            message.setHorizontalAlignment(JLabel.CENTER);
            add(message, c);
        } else {
            for (int i = 0; i < 4; i++) {
                TideData dt = data.get(i);
                JLabel label = new JLabel(dt.isHigh() ? "High" : "Low");
                JLabel entry = new JLabel(dt.getTimeString());

                c.gridy = 0;
                c.weighty = 0.5;
                c.anchor = GridBagConstraints.SOUTH;
                add(label, c);

                c.gridy = 1;
                c.weighty = 1;
                c.anchor = GridBagConstraints.NORTH;
                add(entry, c);

                c.gridx++;
            }
        }
    }
}

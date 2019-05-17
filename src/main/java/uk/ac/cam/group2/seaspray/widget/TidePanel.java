package uk.ac.cam.group2.seaspray.widget;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.*;
import javax.swing.*;
import uk.ac.cam.group2.seaspray.data.TideData;

public class TidePanel extends JPanel {
    public TidePanel(LinkedList<TideData> data) {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Global constraints
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.fill = GridBagConstraints.VERTICAL;
        c.anchor = GridBagConstraints.CENTER;

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
                add(label, c);

                c.gridy = 1;
                c.weighty = 1;
                add(entry, c);

                c.gridx++;
            }
        }
    }
}

package uk.ac.cam.group2.seaspray.widget;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import uk.ac.cam.group2.seaspray.data.TideData;

public class TidePanel extends JPanel {
    private final List<JLabel> widgets;

    public TidePanel() {
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        widgets = new ArrayList<>();
    }

    public void setData(List<TideData> data) {
        removeAll();

        // Global constraints
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.fill = GridBagConstraints.NONE;

        if (data.size() == 0) {
            JLabel message = new JLabel("Data not available");
            message.setHorizontalAlignment(JLabel.CENTER);
            add(message, c);
            return;
        }

        while (widgets.size() < 2 * data.size()) {
            widgets.add(new JLabel());
        }

        for (int i = 0; i < data.size(); i++) {
            TideData dt = data.get(i);
            JLabel label = widgets.get(2 * i);
            JLabel entry = widgets.get(2 * i + 1);
            label.setText(dt.isHigh() ? "High" : "Low");
            entry.setText(dt.getTimeString());

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

package uk.ac.cam.group2.seaspray.widget;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import uk.ac.cam.group2.seaspray.data.HourlyData;

/** Conditions for next 24 hours. */
public class HourlyPanel extends JPanel {
    private final List<HourlyWidget> widgets;

    public HourlyPanel() {
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        widgets = new ArrayList<>();
    }

    public void setData(List<HourlyData> hourlyDatas) {
        removeAll();

        if (hourlyDatas.size() == 0) {
            return;
        }

        while (widgets.size() < hourlyDatas.size()) {
            widgets.add(new HourlyWidget());
        }

        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;

        for (int i = 0; i < hourlyDatas.size(); i++) {
            HourlyData data = hourlyDatas.get(i);
            HourlyWidget widget = widgets.get(i);
            widget.setData(data);
            add(widget, c);
            c.gridy++;
        }
    }
}

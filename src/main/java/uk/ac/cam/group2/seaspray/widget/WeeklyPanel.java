package uk.ac.cam.group2.seaspray.widget;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import uk.ac.cam.group2.seaspray.data.DailyData;

/** Displays weather for the next week. */
public class WeeklyPanel extends JPanel {
    private final List<DailyWidget> widgets;

    public WeeklyPanel() {
        setLayout(new GridBagLayout());
        widgets = new ArrayList<DailyWidget>();
    }

    public void setData(List<DailyData> dailyDatas) {
        removeAll();

        if (dailyDatas.size() == 0) {
            return;
        }

        while (widgets.size() < dailyDatas.size()) {
            widgets.add(new DailyWidget());
        }

        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;

        for (int i = 1; i < dailyDatas.size(); i++) {
            DailyData data = dailyDatas.get(i);
            DailyWidget widget = widgets.get(i);
            widget.setData(data);
            add(widget, c);
            c.gridy++;
        }
    }
}

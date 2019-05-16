package uk.ac.cam.group2.seaspray.widget;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.*;

import uk.ac.cam.group2.seaspray.data.DailyData;
import uk.ac.cam.group2.seaspray.data.HourlyData;

/**
 * Displays weather for the next week.
 */
public class WeeklyPanel extends JPanel {

    public WeeklyPanel(List<DailyData> dailyDatas) {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;

        for (int i = 1; i < dailyDatas.size(); i++) {
            DailyData data = dailyDatas.get(i);
            add(new DailyWidget(data), c);
            c.gridy++;
        }
    }
}

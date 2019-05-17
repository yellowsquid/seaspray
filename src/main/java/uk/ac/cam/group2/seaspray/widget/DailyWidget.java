package uk.ac.cam.group2.seaspray.widget;

import java.awt.*;
import java.io.*;
import java.text.*;
import javax.swing.*;
import uk.ac.cam.group2.seaspray.data.*;

public class DailyWidget extends JPanel {
    public static String dayOfWeek(DailyData d) {
        String day;
        DateFormat format2 = new SimpleDateFormat("EE");
        return format2.format(d.getDate());
    }

    public DailyWidget(DailyData d) {
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1.0;
        c.weighty = 0.2;
        c.gridy = 0;
        c.fill = GridBagConstraints.VERTICAL;
        c.anchor = GridBagConstraints.WEST;
        c.ipadx = 16;
        JLabel title = new JLabel(dayOfWeek(d));
        Font labelFont = title.getFont();
        title.setFont(new Font(labelFont.getName(), Font.BOLD, 18));
        add(title, c);

        c.weighty = 1.0;
        c.gridy = 1;
        c.ipadx = 0;
        c.fill = GridBagConstraints.BOTH;
        add(new HourlyWidget(d.getHours().get(3)), c);

        c.gridy = 2;
        add(new HourlyWidget(d.getHours().get(5)), c);
    }
}

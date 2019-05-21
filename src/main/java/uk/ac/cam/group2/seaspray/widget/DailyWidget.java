package uk.ac.cam.group2.seaspray.widget;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import uk.ac.cam.group2.seaspray.data.DailyData;

public class DailyWidget extends JPanel {
    private final JLabel dayWidget;
    private final HourlyWidget morningWidget;
    private final HourlyWidget eveningWidget;

    public DailyWidget() {
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
        dayWidget = new JLabel();

        // TODO: styling
        Font labelFont = dayWidget.getFont();
        dayWidget.setFont(new Font(labelFont.getName(), Font.BOLD, 18));
        add(dayWidget, c);

        // Morning
        c.weighty = 1.0;
        c.gridy = 1;
        c.ipadx = 0;
        c.fill = GridBagConstraints.BOTH;
        morningWidget = new HourlyWidget();
        add(morningWidget, c);

        // Evening
        c.weighty = 1.0;
        c.gridy = 2;
        c.ipadx = 0;
        c.fill = GridBagConstraints.BOTH;
        eveningWidget = new HourlyWidget();
        add(eveningWidget, c);
    }

    public void setData(DailyData data) {
        // Day of week
        String label = String.format("%ta", data.getDate());
        dayWidget.setText(label);

        // Morning
        morningWidget.setData(data.getHours().get(3));

        // Evening
        eveningWidget.setData(data.getHours().get(5));
    }
}

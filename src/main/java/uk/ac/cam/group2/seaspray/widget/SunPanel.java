package uk.ac.cam.group2.seaspray.widget;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.io.UncheckedIOException;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SunPanel extends JPanel {
    public SunPanel(String sunRise, String sunSet) {
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        GridBagConstraints c = new GridBagConstraints();

        // Global constraints
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.fill = GridBagConstraints.NONE;

        IconWidget riseIcon = null;
        IconWidget setIcon = null;

        try {
            riseIcon = new IconWidget("src/main/resources/sunrise.png");
            riseIcon.setAlignment(IconWidget.EAST);
            riseIcon.setPreferredSize(new Dimension(100, 100));
            setIcon = new IconWidget("src/main/resources/sunset.png");
            riseIcon.setAlignment(IconWidget.EAST);
            setIcon.setPreferredSize(new Dimension(100, 100));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to load sunrise/set icons.", e);
        }

        JLabel upTime = new JLabel(sunRise);
        JLabel downTime = new JLabel(sunSet);

        add(riseIcon, c);
        c.gridx++;
        add(upTime, c);
        c.gridx++;
        add(setIcon, c);
        c.gridx++;
        add(downTime, c);
    }
}

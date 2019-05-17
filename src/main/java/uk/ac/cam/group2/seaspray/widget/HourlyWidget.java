package uk.ac.cam.group2.seaspray.widget;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.io.UncheckedIOException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import uk.ac.cam.group2.seaspray.data.HourlyData;

/** Displays time, wind, temperature, and wave height and direction. */
public class HourlyWidget extends JPanel {
    public HourlyWidget(HourlyData h) {
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);

        // Global constraints
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;

        // Time
        c.gridx = 0;
        c.weightx = 0.5;
        c.fill = GridBagConstraints.VERTICAL;
        c.anchor = GridBagConstraints.EAST;
        String clockTime = (int) (h.getTime() / 100) + ":00";

        if (clockTime.length() == 4) {
            clockTime = "0" + clockTime;
        }

        JLabel time = new JLabel(clockTime);
        add(time, c);

        // Wind widget
        c.gridx = 1;
        c.weightx = 1;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;

        try {
            WindWidget wind = new WindWidget(h.getWind());
            add(wind, c);
        } catch (IOException e) {
            throw new UncheckedIOException("Cannot load wind icon.", e);
        }

        // Temperature
        c.gridx = 2;
        c.weightx = 0.5;
        c.fill = GridBagConstraints.VERTICAL;
        c.anchor = GridBagConstraints.CENTER;
        String tempLabel = String.format("% 3d\u00B0C", h.getTempC());
        JLabel temp = new JLabel(tempLabel);
        add(temp, c);

        // Wave height
        c.gridx = 3;
        c.weightx = 0.5;
        c.fill = GridBagConstraints.VERTICAL;
        c.anchor = GridBagConstraints.EAST;
        String waveLabel = String.format("% 4.1f ft", h.getSigHeight() * 3.28);
        JLabel wave = new JLabel(waveLabel);
        add(wave, c);

        // Wave direction
        c.gridx = 4;
        c.weightx = 0.5;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;

        try {
            double waveDeg = h.getSwellDeg();
            WaveDirectionWidget waveDir = new WaveDirectionWidget(waveDeg);
            waveDir.setPreferredSize(new Dimension(32, 32));
            add(waveDir, c);
        } catch (IOException e) {
            throw new UncheckedIOException("Cannot find wave dir icon.", e);
        }
    }
}

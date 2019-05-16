package uk.ac.cam.group2.seaspray.widget;

import javax.swing.*;

import uk.ac.cam.group2.seaspray.data.Condition;
import uk.ac.cam.group2.seaspray.data.CurrentData;
import uk.ac.cam.group2.seaspray.data.TideData;

import java.awt.*;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.LinkedList;

/**
 * Current conditions
 * FIXME: I do nothing.
 */
// should have: WindObject,
public class CurrentPanel extends JPanel {
    public CurrentPanel(CurrentData currentData) {
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        GridBagConstraints c = new GridBagConstraints();

        // Global constraints
        c.ipadx = 16;
        c.ipady = 16;

        // Current Conditions
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.125;
        c.weighty = 0.125;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.NONE;
        int code = currentData.getWeatherCode();
        String path = Condition.RAIN_MEDIUM.getPath();

        for (Condition condition : Condition.values()) {
            if (condition.hasCode(code)) {
                path = condition.getPath();
                break;
            }
        }

        try {
            IconWidget cond = new IconWidget(path);
            // FIXME: static size
            cond.setPreferredSize(new Dimension(50, 50));
            add(cond, c);
        } catch (IOException e) {
            throw new UncheckedIOException("Cannot load conditions icon: " + code, e);
        }

        // Temperature
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0.125;
        c.weighty = 0.125;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.VERTICAL;
        JLabel tempC = new JLabel(String.valueOf(currentData.getTempC() + "\u00B0" + "C"));
        // TODO: Adjust font size
        add(tempC, c);

        // Wind
        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 1;
        c.weightx = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;
        add(new WindWidget(currentData.getWind()),c);
        // TODO: Adjust font size

        // Wave height
        c.gridx = 2;
        c.gridy = 2;
        c.weightx = 0.125;
        c.weighty = 0.125;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.VERTICAL;
        JLabel wave = new JLabel(currentData.getSigWaveHeightFt() + " ft");
        // TODO: Adjust font size
        add(wave,c);

        // Waviness Icon
        // FIXME: shows wrong iconset
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 1;
        c.weighty = 0.125;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.NONE;

        try {
            double waveDegs = currentData.getWaveDir();
            WaveDirectionWidget waveDir = new WaveDirectionWidget(waveDegs);
            // FIXME: static size
            waveDir.setPreferredSize(new Dimension(50, 50));
            add(waveDir, c);
        } catch (IOException e) {
            throw new UncheckedIOException("Cannot load wave direction icon.", e);
        }

        // Tides
        // FIXME: currently static placeholder text
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 0;
        c.weighty = 0.125;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;
        // FIXME: missing tide data
        TidePanel tideText = new TidePanel(currentData.getTides());
        //JLabel tideText = new JLabel("Tides stuff here");
        add(tideText, c);

        // Sunrise and sunset
        // FIXME: currently static placeholder text
        c.gridx = 0;
        c.gridy = 4;
        c.weightx = 0;
        c.weighty = 0.125;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;
        JLabel sunset = new JLabel("Sunset stuff here");
        add(sunset, c);
    }
}

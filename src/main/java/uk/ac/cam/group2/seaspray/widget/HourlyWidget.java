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
    private static final String TIME_FORMAT = "%tH:%tM";
    private static final String TEMP_FORMAT = "% 3d\u00B0C";
    private static final String WAVE_HEIGHT_FORMAT = "% 4.1f ft";

    private final JLabel timeWidget;
    private final WindWidget windWidget;
    private final JLabel tempWidget;
    private final JLabel waveHeightWidget;
    private final WaveDirectionWidget waveDirWidget;



    public HourlyWidget() {
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
        timeWidget = new JLabel();
        add(timeWidget, c);

        // Wind widget
        c.gridx = 1;
        c.weightx = 1;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;

        try {
            windWidget = new WindWidget();
            add(windWidget, c);
        } catch (IOException e) {
            throw new UncheckedIOException("Cannot load wind icon.", e);
        }

        // Temperature
        c.gridx = 2;
        c.weightx = 0.5;
        c.fill = GridBagConstraints.VERTICAL;
        c.anchor = GridBagConstraints.CENTER;
        tempWidget = new JLabel();
        add(tempWidget, c);

        // Wave height
        c.gridx = 3;
        c.weightx = 0.5;
        c.fill = GridBagConstraints.VERTICAL;
        c.anchor = GridBagConstraints.EAST;
        waveHeightWidget = new JLabel();
        add(waveHeightWidget, c);

        // Wave direction
        c.gridx = 4;
        c.weightx = 0.5;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;

        try {
            waveDirWidget = new WaveDirectionWidget();
            waveDirWidget.setPreferredSize(new Dimension(32, 32));
            add(waveDirWidget, c);
        } catch (IOException e) {
            throw new UncheckedIOException("Cannot find wave dir icon.", e);
        }
    }

    public void setData(HourlyData data) {
        // Time
        String clockTime = String.format(TIME_FORMAT, data.getTime(), data.getTime());
        timeWidget.setText(clockTime);

        // Wind widget
        windWidget.setData(data.getWind());

        // Temperature
        String tempLabel = String.format(TEMP_FORMAT, data.getTempC());
        tempWidget.setText(tempLabel);

        // Wave height
        String waveLabel = String.format(WAVE_HEIGHT_FORMAT, data.getSigHeight() * 3.28);
        waveHeightWidget.setText(waveLabel);

        // Wave direction
        double waveDeg = data.getSwellDeg();
        waveDirWidget.setBearing(waveDeg);
    }
}

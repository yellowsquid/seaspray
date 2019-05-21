package uk.ac.cam.group2.seaspray.widget;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.io.UncheckedIOException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import uk.ac.cam.group2.seaspray.data.Condition;
import uk.ac.cam.group2.seaspray.data.CurrentData;

/** Current conditions FIXME: I do nothing. */
// should have: WindObject,
public class CurrentPanel extends JPanel {
    private static final String TIME_FORMAT = "%tH:%tM";
    private static final String TEMP_FORMAT = "% 3d\u00B0C";
    private static final String WAVE_HEIGHT_FORMAT = "% 4.1f ft";

    private final IconWidget conditionsWidget;
    private final JLabel tempWidget;
    private final WindWidget windWidget;
    private final JLabel waveHeightWidget;
    private final WaveDirectionWidget waveDirWidget;
    private final SunPanel sunPanel;
    private final TidePanel tidePanel;

    public CurrentPanel() {
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
        conditionsWidget = new IconWidget();
        // FIXME: static size
        conditionsWidget.setPreferredSize(new Dimension(50, 50));
        conditionsWidget.setBackground(Color.WHITE);
        add(conditionsWidget, c);

        // Temperature
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0.125;
        c.weighty = 0.125;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.VERTICAL;
        tempWidget = new JLabel();
        // TODO: Adjust font size
        add(tempWidget, c);

        // Wind
        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 1;
        c.weightx = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;

        try {
            windWidget = new WindWidget();
            add(windWidget, c);
        } catch (IOException e) {
            throw new UncheckedIOException("Cannot find wind icon.", e);
        }
        // TODO: Adjust font size

        // Wave height
        c.gridx = 2;
        c.gridy = 2;
        c.weightx = 0.125;
        c.weighty = 0.125;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.VERTICAL;
        waveHeightWidget = new JLabel();
        // TODO: Adjust font size
        add(waveHeightWidget, c);

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
            waveDirWidget = new WaveDirectionWidget();
            // FIXME: static size
            waveDirWidget.setPreferredSize(new Dimension(50, 50));
            add(waveDirWidget, c);
        } catch (IOException e) {
            throw new UncheckedIOException("Cannot load wave direction icon.", e);
        }

        // Tides
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 0;
        c.weighty = 0.125;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;
        tidePanel = new TidePanel();
        add(tidePanel, c);

        // Sunrise and sunset
        c.gridx = 0;
        c.gridy = 4;
        c.weightx = 0;
        c.weighty = 0.125;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;
        sunPanel = new SunPanel();
        add(sunPanel, c);
    }

    public void setData(CurrentData currentData) {
        // Current Conditions
        int code = currentData.getWeatherCode();
        String path = Condition.RAIN_MEDIUM.getPath();

        for (Condition condition : Condition.values()) {
            if (condition.hasCode(code)) {
                path = condition.getPath();
                break;
            }
        }

        try {
            conditionsWidget.setPath(path);
        } catch (IOException e) {
            throw new UncheckedIOException("Cannot load conditions icon: " + code, e);
        }

        // Temperature
        String tempLabel = String.format(TEMP_FORMAT, currentData.getTempC());
        tempWidget.setText(tempLabel);

        // Wind
        windWidget.setData(currentData.getWind());

        // Wave height
        String waveLabel = String.format(WAVE_HEIGHT_FORMAT, currentData.getSigWaveHeight() * 3.28);
        waveHeightWidget.setText(waveLabel);

        // Waviness Icon
        double waveDegs = currentData.getWaveDir();
        waveDirWidget.setBearing(waveDegs);

        // Tides
        tidePanel.setData(currentData.getTides());

        // Sunrise and sunset
        sunPanel.setData(currentData.getSunRise(), currentData.getSunSet());
    }
}

package uk.ac.cam.group2.seaspray.widget;

import javax.swing.*;

import uk.ac.cam.group2.seaspray.data.CurrentData;

import java.awt.*;

/**
 * Current conditions
 * FIXME: I do nothing.
 */
// should have: WindObject,
public class CurrentPanel extends JPanel {
    public CurrentPanel(CurrentData currentData) {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0;
        c.gridx = 0;
        c.weightx = 1;
        c.weighty = 1;

        JLabel tempC = new JLabel(String.valueOf(currentData.getTempC() + "\u00B0" + "C"));
        add(tempC,c);

        c.gridy = 1;
        c.gridx = 1;
        c.weighty = 3;
        c.weightx = 3;
        // TODO: Currently gives error
        add(new WindWidget(currentData.getWind()),c);

        JLabel wave = new JLabel(currentData.getSigWaveHeightFt()+"ft");
        c.gridy = 2;
        c.gridx = 2;
        c.weightx = 1;
        c.weighty = 1;
        add(wave,c);

        // TODO: Wave direction?


    }
}

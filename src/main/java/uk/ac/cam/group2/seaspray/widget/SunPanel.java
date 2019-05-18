package uk.ac.cam.group2.seaspray.widget;

import uk.ac.cam.group2.seaspray.data.TideData;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

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

        IconWidget RiseIm = null;
        IconWidget SetIm = null;
        try {
            RiseIm = new IconWidget("src/main/resources/sunrise.png");
            SetIm = new IconWidget("src/main/resources/sunset.png");
        } catch (IOException e){
            e.printStackTrace();
        }
        JLabel upTime = new JLabel(sunRise);
        JLabel downTime = new JLabel(sunSet);

        add(RiseIm,c);
        c.gridx++;
        add(upTime,c);
        c.gridx++;
        add(SetIm,c);
        c.gridx++;
        add(downTime,c);

    }
}

package uk.ac.cam.group2.seaspray.widget;

import javax.imageio.ImageIO;
import javax.swing.*;

import uk.ac.cam.group2.seaspray.data.HourlyData;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Displays time, wind, temperature, and wave height and direction.
 * FIXME: I do nothing
 */
public class HourlyWidget extends JPanel {
    public HourlyWidget(HourlyData h) {
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);

        // Global constraints
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        c.gridy = 0;

        // Time
        c.gridx = 0;
        c.weightx = 0.5;
        c.anchor = GridBagConstraints.EAST;
        String clockTime = (int)(h.getTime()/100) + ":00";

        if (clockTime.length() == 4) {
            clockTime = "0" + clockTime;
        }

        JLabel time = new JLabel(clockTime);
        add(time, c);

        // Wind widget
        c.gridx = 1;
        c.weightx = 1;
        c.anchor = GridBagConstraints.CENTER;
        WindWidget wind = new WindWidget(h.getWind());
        add(wind, c);

        // Temperature
        c.gridx = 2;
        c.weightx = 0.5;
        c.anchor = GridBagConstraints.CENTER;
        JLabel temp = new JLabel(h.getTempC()  + "\u00B0" + "C");
        add(temp, c);

        // Wave height
        c.gridx = 3;
        c.weightx = 0.5;
        c.anchor = GridBagConstraints.EAST;
        JLabel wave = new JLabel((int)(h.getSigHeight()*3.28) + " ft");
        add(wave, c);

        // Wave direction
        c.gridx = 4;
        c.weightx = 0.5;
        c.anchor = GridBagConstraints.WEST;
        add(new JLabel("dir"), c);
        // FIXME: DRAW WAVE DIRECTION ICON

        /*
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("resources/waveArrow.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // SET TO ACTUAL ANGLE
        img = ImageManipulate.rotateWave(img,h.swellDeg);

        // img = ImageManipulate.rotate(img,0);
        Image dimg = img.getScaledInstance(60, 60,
                Image.SCALE_SMOOTH);
        JLabel picLabel = new JLabel(new ImageIcon(dimg));
        add(picLabel,c);
        */
    }
}

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
        String clockTime = (int)(h.getTime()/100) + ":00";
        JLabel time = new JLabel(clockTime);
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 0.2;
        c.gridy = 0;
        c.gridx = 0;
        add(time,c);


        c.gridx = 1;
        add(new WindWidget(h.getWind()),c);

        JLabel temp = new JLabel(h.getTempC()  + "\u00B0" + "C");
        c.gridx = 2;
        add(temp,c);

        JLabel wave = new JLabel((int)(h.getSigHeight()*3.28)+" ft");
        c.gridx = 3;
        add(wave,c);

        c.gridx = 4;

        /*

        \\TODO: DRAW WAVE DIRECTION ICON

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("resources/waveArrow.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // SET TO ACTUAL ANGLE
        img = ImageManipulate.rotateWave(img,h.swellDeg);

        //img = ImageManipulate.rotate(img,0);
        Image dimg = img.getScaledInstance(60, 60,
                Image.SCALE_SMOOTH);
        JLabel picLabel = new JLabel(new ImageIcon(dimg));
        add(picLabel,c);*/
    }
}

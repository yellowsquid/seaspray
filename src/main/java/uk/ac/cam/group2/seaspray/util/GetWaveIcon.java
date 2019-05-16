package uk.ac.cam.group2.seaspray.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class GetWaveIcon {
    public static JLabel getIcon(double angle){
        int dir = (int)((angle+22.5)/45)%8;
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("resources/waveAngles/wave"+dir+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JLabel(new ImageIcon(img));
    }
}

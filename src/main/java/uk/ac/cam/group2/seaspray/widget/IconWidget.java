package uk.ac.cam.group2.seaspray.widget;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.JPanel;

public class IconWidget extends JPanel {
    private final Image image;

    public IconWidget(String path) throws IOException {
        this(ImageIO.read(new File(path)));
    }

    public IconWidget(Image image) {
        this.image = image;
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        //g.setColor(getBackground());
        // g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }
}

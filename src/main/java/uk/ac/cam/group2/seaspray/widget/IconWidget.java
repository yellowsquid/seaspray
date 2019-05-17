package uk.ac.cam.group2.seaspray.widget;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class IconWidget extends JPanel {
    // Bit flags for alignment
    public static final int NONE = 0;
    public static final int NORTH = 1;
    public static final int SOUTH = 2;
    public static final int EAST = 4;
    public static final int WEST = 8;
    private final Image image;
    private int alignment;
    private double rotation;

    public IconWidget(String path) throws IOException {
        this(ImageIO.read(new File(path)));
    }

    public IconWidget(Image image) {
        this.image = image;
        this.alignment = NONE;
        setBackground(Color.WHITE);
    }

    public void setAlignment(int alignment) {
        this.alignment = alignment;
    }

    public void setBearing(double bearing) {
        setRotation(Math.toRadians(bearing));
    }

    public void setRotation(double radians) {
        this.rotation = radians;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics.create();
        int min = Math.min(getWidth(), getHeight());
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int xTop = (getWidth() - min) / 2;
        int yTop = (getHeight() - min) / 2;

        if ((alignment & NORTH) == NORTH && (alignment & SOUTH) == 0) {
            xTop = 0;
        } else if ((alignment & NORTH) == 0 && (alignment & SOUTH) == SOUTH) {
            xTop *= 2;
        }

        if ((alignment & EAST) == EAST && (alignment & WEST) == 0) {
            yTop = 0;
        } else if ((alignment & EAST) == 0 && (alignment & WEST) == WEST) {
            yTop *= 2;
        }

        g.rotate(rotation, centerX, centerY);
        g.drawImage(image, xTop, yTop, min, min, null);
        g.dispose();
    }
}

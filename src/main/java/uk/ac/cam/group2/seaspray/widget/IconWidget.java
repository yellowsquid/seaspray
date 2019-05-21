package uk.ac.cam.group2.seaspray.widget;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class IconWidget extends JPanel {
    private static final Map<String, Image> CACHE = new HashMap<>();
    // Bit flags for alignment
    public static final int NONE = 0;
    public static final int NORTH = 1;
    public static final int SOUTH = 2;
    public static final int EAST = 4;
    public static final int WEST = 8;
    private Image image;
    private int alignment;
    private double rotation;

    public IconWidget() {}

    public IconWidget(String path) throws IOException {
        this(ImageIO.read(new File(path)));
    }

    public IconWidget(Image image) {
        this.image = image;
        this.alignment = NONE;
        setBackground(Color.WHITE);
    }

    private static Image readImage(String path) throws IOException {
        if (CACHE.containsKey(path)) {
            return CACHE.get(path);
        }

        Image image = ImageIO.read(new File(path));
        CACHE.put(path, image);
        return image;
    }

    public void setPath(String path) throws IOException {
        this.image = readImage(path);
        repaint();
    }

    public void setAlignment(int alignment) {
        this.alignment = alignment;
        repaint();
    }

    public void setBearing(double bearing) {
        setRotation(Math.toRadians(180 + bearing));
    }

    public void setRotation(double radians) {
        this.rotation = radians;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        if (image == null) {
            return;
        }

        graphics.setColor(getBackground());
        graphics.fillRect(0, 0, getWidth(), getHeight());
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

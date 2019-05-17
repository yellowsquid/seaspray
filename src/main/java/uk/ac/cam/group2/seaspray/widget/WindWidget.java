package uk.ac.cam.group2.seaspray.widget;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import uk.ac.cam.group2.seaspray.data.WindData;

/** Displays current wind speed and direction FIXME: I do nothing */
public class WindWidget extends JPanel {
    private static final int STICK_WIDTH = 5;
    private static final double SUB_ANGLE = Math.toRadians(20);
    private final WindData windData;

    public WindWidget(WindData windData) {
        this.windData = windData;
    }

    @Override
    public Dimension getPreferredSize() {
        int fontSize = getFont().getSize();
        return new Dimension(5 * fontSize, 5 * fontSize);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics.create();
        int width = getWidth();
        int height = getHeight();

        // min of width and heigth
        int min = (width < height) ? width : height;

        // diameter and radii of circles
        int outerDiam = min / 2;
        int innerDiam = outerDiam - 2 * STICK_WIDTH;

        int outerRadius = outerDiam / 2;
        int innerRadius = innerDiam / 2;

        int centerX = width / 2;
        int centerY = height / 2;

        // draw stick part
        int angle = windData.getDirectionDeg();
        double rads = Math.toRadians(angle);
        int stickLength = (min - outerDiam) / 2;
        int stickTop = centerY - outerRadius - stickLength;

        g.setColor(Color.BLACK);
        g.fillArc(centerX - outerRadius, centerY - outerRadius, outerDiam, outerDiam, 0, 360);
        g.setColor(Color.WHITE);
        g.fillArc(centerX - innerRadius, centerY - innerRadius, innerDiam, innerDiam, 0, 360);

        g.rotate(rads, centerX, centerY);
        g.setColor(Color.BLACK);
        g.fillRect(centerX - STICK_WIDTH / 2, stickTop, STICK_WIDTH, stickLength);
        g.rotate(SUB_ANGLE, centerX - STICK_WIDTH / 2, stickTop);
        g.fillRect(centerX - STICK_WIDTH / 2, stickTop, STICK_WIDTH, stickLength / 2);
        g.rotate(-SUB_ANGLE, centerX - STICK_WIDTH / 2, stickTop);
        g.rotate(-SUB_ANGLE, centerX + STICK_WIDTH / 2, stickTop);
        g.fillRect(centerX - STICK_WIDTH / 2, stickTop, STICK_WIDTH, stickLength / 2);
        g.rotate(SUB_ANGLE, centerX + STICK_WIDTH / 2, stickTop);
        g.rotate(-rads, centerX, centerY);

        int charWidth = getFont().getSize() * 3 / 5;
        int charHeight = getFont().getSize() / 2;
        int windSpeed = windData.getWindSpeedKph();
        int windXOffset = centerX - ((Math.abs(windSpeed) < 10 ? charWidth / 2 : charWidth));
        int windYOffset = centerY + charHeight / 2;
        g.drawString("" + windData.getWindSpeedKph(), windXOffset, windYOffset);
        g.dispose();
    }
}

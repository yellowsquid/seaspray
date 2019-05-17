package uk.ac.cam.group2.seaspray.widget;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;
import uk.ac.cam.group2.seaspray.data.WindData;

/** Displays current wind speed and direction FIXME: I do nothing */
public class WindWidget extends IconWidget {
    private final WindData windData;

    public WindWidget(WindData windData) throws IOException {
        super("src/main/resources/windarrows/WindN.png");
        this.windData = windData;
        setBearing(windData.getDirectionDeg());
    }

    @Override
    public Dimension getPreferredSize() {
        int fontSize = getFont().getSize();
        return new Dimension(5 * fontSize, 5 * fontSize);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.BLACK);
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int charWidth = getFont().getSize() * 3 / 5;
        int charHeight = getFont().getSize() / 2;
        int windSpeed = windData.getWindSpeedKph();
        int windXOffset = centerX - ((Math.abs(windSpeed) < 10 ? charWidth / 2 : charWidth));
        int windYOffset = centerY + charHeight / 2;
        graphics.drawString("" + windData.getWindSpeedKph(), windXOffset, windYOffset);
    }
}

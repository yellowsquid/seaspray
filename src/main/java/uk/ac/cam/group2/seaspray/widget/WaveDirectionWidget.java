package uk.ac.cam.group2.seaspray.widget;

import java.io.IOException;

public class WaveDirectionWidget extends IconWidget {
    public WaveDirectionWidget(double angle) throws IOException {
        super("src/main/resources/waveAngles/wave0.png");
        this.setBearing(angle);
    }
}

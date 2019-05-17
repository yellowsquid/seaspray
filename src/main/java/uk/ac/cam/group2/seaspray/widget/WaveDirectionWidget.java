package uk.ac.cam.group2.seaspray.widget;

import java.io.IOException;

public class WaveDirectionWidget extends IconWidget {
    public WaveDirectionWidget(double angle) throws IOException {
        super("src/main/resources/waveAngles/wave" + getDir(angle) + ".png");
    }

    private static int getDir(double angle) {
        return ((int) ((angle + 22.5) / 45)) % 8;
    }
}

package uk.ac.cam.group2.seaspray.data;

public class WindData {
    private final int windSpeedKph;
    private final int directionDeg;

    public int getWindSpeedKph() {
        return windSpeedKph;
    }

    public int getDirectionDeg() {
        return directionDeg;
    }

    public WindData(int speed, int dir) {
        windSpeedKph = speed;
        directionDeg = dir;
    }
}

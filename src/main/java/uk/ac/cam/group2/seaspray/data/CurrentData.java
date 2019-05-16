package uk.ac.cam.group2.seaspray.data;

import java.util.Date;

public class CurrentData {
    private WindData wind;
    private double TempC;
    private double sigWaveHeightFt;
    private String sunRise; // TODO: Change to date or ensure parsed as time string
    private String sunSet;
    private String desc;

    // TODO: Build current data
    public CurrentData(DailyData d){
        sunRise = d.getSunrise();
        sunSet = d.getSunset();
    }
}

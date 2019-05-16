package uk.ac.cam.group2.seaspray.data;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentData {
    private WindData wind;
    private double TempC;
    private double sigWaveHeightFt;
    private String sunRise; // TODO: Change to date or ensure parsed as time string
    private String sunSet;
    private int weatherCode;
    private int waveDir;

    // TODO: Build current data
    public CurrentData(DailyData d){
        sunRise = d.getSunrise();
        sunSet = d.getSunset();

        // get current data from most recent hour element
        // TODO: Interpolate? May require next day if near midnight
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH");
        int time = Integer.valueOf(formatter.format(now))*100;
        int i = 0;
        HourlyData justGone = d.getHours().get(i);
        while (i < d.getHours().size()-1 && justGone.getTime()< time-300){
            justGone = d.getHours().get(++i);
        }

        wind = justGone.getWind();
        TempC = justGone.getTempC();
        sigWaveHeightFt = justGone.getSigHeight();
        weatherCode = justGone.getWeatherCode();
        waveDir = justGone.getSwellDeg();
    }

    public WindData getWind() {
        return wind;
    }

    public double getTempC() {
        return TempC;
    }

    public double getWaveDir(){
        return waveDir;
    }

    public double getSigWaveHeightFt() {
        return sigWaveHeightFt;
    }

    public String getSunRise() {
        return sunRise;
    }

    public String getSunSet() {
        return sunSet;
    }

    public int getWeatherCode() {
        return weatherCode;
    }
}

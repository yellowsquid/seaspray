package uk.ac.cam.group2.seaspray.data;

import java.util.Calendar;
import java.util.List;

public class CurrentData {
    private final WindData wind;
    private final int tempC;
    private final double sigWaveHeight;
    private final String sunRise; // TODO: Change to date or ensure parsed as time string
    private final String sunSet;
    private final int weatherCode;
    private final int waveDir;
    private final List<TideData> tides;

    // TODO: Build current data
    public CurrentData(DailyData d, List<TideData> tides) {
        this.tides = tides;
        sunRise = d.getSunrise();
        sunSet = d.getSunset();

        // get current data from most recent hour element
        // TODO: Interpolate? May require next day if near midnight
        Calendar target = Calendar.getInstance();
        target.add(Calendar.HOUR_OF_DAY, -3);
        HourlyData justGone = null;

        for (HourlyData hourlyData : d.getHours()) {
            if (hourlyData.getTime().after(target)) {
                justGone = hourlyData;
                break;
            }
        }

        if (justGone == null) {
            throw new AssertionError("Didn't get recent enough data.");
        }

        wind = justGone.getWind();
        tempC = justGone.getTempC();
        sigWaveHeight = justGone.getSigHeight();
        weatherCode = justGone.getWeatherCode();
        waveDir = justGone.getSwellDeg();
    }

    public WindData getWind() {
        return wind;
    }

    public int getTempC() {
        return tempC;
    }

    public double getWaveDir() {
        return waveDir;
    }

    public double getSigWaveHeight() {
        return sigWaveHeight;
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

    public List<TideData> getTides() {
        return List.copyOf(tides);
    }
}

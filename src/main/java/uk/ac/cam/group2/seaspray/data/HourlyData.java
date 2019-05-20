package uk.ac.cam.group2.seaspray.data;

import java.util.Calendar;
import org.json.JSONObject;

public class HourlyData {
    private final Calendar time;
    private final int tempC;
    private final WindData wind;
    private final int weatherCode;

    private final double sigHeight; // significant wave height
    private final int swellDeg;

    public HourlyData(JSONObject o, Calendar date) {
        this(
                date,
                o.getDouble("time"),
                o.getInt("tempC"),
                new WindData(o.getInt("windspeedKmph"), o.getInt("winddirDegree")),
                o.getInt("weatherCode"),
                o.getDouble("sigHeight_m"),
                o.getInt("swellDir"));
    }

    public HourlyData(
            Calendar date,
            double time,
            int tempC,
            WindData windData,
            int weatherCode,
            double sigHeight,
            int swellDir) {
        this.time = (Calendar) date.clone();
        this.time.set(Calendar.HOUR_OF_DAY, (int) (time / 100));
        this.time.set(Calendar.MINUTE, (int) (time % 60));
        this.tempC = tempC;
        this.wind = windData;
        this.weatherCode = weatherCode;
        this.sigHeight = sigHeight;
        this.swellDeg = swellDir;
    }

    public Calendar getTime() {
        return time;
    }

    public int getTempC() {
        return tempC;
    }

    public WindData getWind() {
        return wind;
    }

    public int getWeatherCode() {
        return weatherCode;
    }

    public double getSigHeight() {
        return sigHeight;
    }

    public int getSwellDeg() {
        return swellDeg;
    }
}

package uk.ac.cam.group2.seaspray.data;

import org.json.JSONObject;

public class HourlyData {
    private final double time;
    private final int tempC;
    private final WindData wind;
    private final int weatherCode;

    private final double sigHeight; // significant wave height
    //double swellHeight;
    private final int swellDeg;

    public double getTime() {
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

    public HourlyData(JSONObject o){
        this(o.getDouble("time"),
             o.getInt("tempC"),
             new WindData(o.getInt("windspeedKmph"), o.getInt("winddirDegree")),
             o.getInt("weatherCode"),
             o.getDouble("sigHeight_m"),
             o.getInt("swellDir"));
    }

    public HourlyData(double time,
                      int tempC,
                      WindData windData,
                      int weatherCode,
                      double sigHeight,
                      int swellDir) {
        this.time = time;
        this.tempC = tempC;
        this.wind = windData;
        this.weatherCode = weatherCode;
        this.sigHeight = sigHeight;
        this.swellDeg = swellDir;
    }
}

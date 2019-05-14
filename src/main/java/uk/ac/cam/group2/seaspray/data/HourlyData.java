package uk.ac.cam.group2.seaspray.data;

import org.json.JSONObject;

public class HourlyData {
    private final double time;
    private final int tempC;
    private final WindData wind;
    private final String desc; // Needed? How are we displaying conditions?

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

    public String getDesc() {
        return desc;
    }

    public double getSigHeight() {
        return sigHeight;
    }

    public int getSwellDeg() {
        return swellDeg;
    }

    public HourlyData(JSONObject o){
        time = o.getDouble("time");
        tempC = o.getInt("tempC");
        int speed = o.getInt("windspeedKmph");
        int dir = o.getInt("winddirDegree");
        wind = new WindData(speed,dir);
        // weather url also stored in array in weatherDesc object
        desc = o.getJSONArray("weatherDesc").getJSONObject(0).getString("value");
        sigHeight = o.getDouble("sigHeight_m");
        swellDeg = o.getInt("swellDir");
    }
}

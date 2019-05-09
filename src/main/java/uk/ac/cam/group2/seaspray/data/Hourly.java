package uk.ac.cam.group2.seaspray.data;

import org.json.JSONObject;

public class Hourly {
    double time;
    int tempC;
    int windSpeedKph;
    int directionDeg;
    String compass;
    String desc;

    // may not use all

    double precip;
    int humid;
    int visibility;
    int windChillC;
    int windGustKph;
    int feelsLikeC;
    double sigHeight; // significant wave height
    double swellHeight;
    int swellDeg;
    String swellCompass;
    int waterTempC;

    public Hourly(JSONObject o){
        time = o.getDouble("time");
        tempC = o.getInt("tempC");
        windSpeedKph = o.getInt("windspeedKmph");
        directionDeg = o.getInt("winddirDegree");
        compass = o.getString("winddir16Point");
        // weather url also stored in array here
        desc = o.getJSONArray("weatherDesc").getJSONObject(0).getString("value");
        sigHeight = o.getDouble("sigHeight_m");
        // can get GUST SPEED, humidity, visibility, precipitation, feelsLike temp, swell, water temp
    }
}

package uk.ac.cam.group2.seaspray.data;

import org.json.JSONObject;

public class HourlyData {
    double time;
    int tempC;
    WindData wind;
    String desc; // Needed? How are we displaying conditions?

    double sigHeight; // significant wave height
    //double swellHeight;
    int swellDeg;

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

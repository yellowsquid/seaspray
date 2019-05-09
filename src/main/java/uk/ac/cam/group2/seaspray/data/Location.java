package uk.ac.cam.group2.seaspray.data;

import org.json.*;

public class Location {
    String name;
    double lat;
    double lon;
    double tempK;
    String country;
    String generalDesc;
    String specificDesc;

    public Location (JSONObject o){
        name = o.getString("name");
        lat = o.getJSONObject("coord").getDouble("lat");
        lon = o.getJSONObject("coord").getDouble("lon");
        tempK = o.getJSONObject("main").getDouble("temp");
        country = o.getJSONObject("sys").getString("country");
        JSONArray w = o.getJSONArray("weather");
        if (w.length() > 0) {
            generalDesc = w.getJSONObject(0).getString("main");
            specificDesc = w.getJSONObject(0).getString("description");
        }
    }
    public String toString(){
        return name + " (" + country + ")";
    }
}

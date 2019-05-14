package uk.ac.cam.group2.seaspray.data;

import org.json.*;

public class Location {
    String name;
    double lat;
    double lon;
    String country;

    // TODO: worldtides.info

    public Location (JSONObject o){
        name = o.getString("name");
        lat = o.getJSONObject("coord").getDouble("lat");
        lon = o.getJSONObject("coord").getDouble("lon");
        country = o.getJSONObject("sys").getString("country");
        JSONArray w = o.getJSONArray("weather");
    }
    public String toString(){
        return name + " (" + country + ")";
    }
}

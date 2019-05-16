package uk.ac.cam.group2.seaspray.data;

import org.json.*;

public class Location {
    private String name;
    private double lat;
    private double lon;
    private String country;

    // TODO: worldtides.info


    public String getName() {
        return name;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getCountry() {
        return country;
    }

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

package uk.ac.cam.group2.seaspray.data;

import org.json.*;

public class Location {
    public final String name;
    public final double lat;
    public final double lon;
    public final String country;

    // TODO: worldtides.info

    public Location(JSONObject o) {
        this(
                o.getString("name"),
                o.getJSONObject("coord").getDouble("lat"),
                o.getJSONObject("coord").getDouble("lon"),
                o.getJSONObject("sys").getString("country"));
    }

    public Location(String name, double lat, double lon, String country) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.country = country;
    }

    @Override
    public String toString() {
        return name + " (" + country + ")";
    }

    @Override
    public int hashCode(){
        return (name+country).hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() != Location.class) {
            return false;
        }

        Location l = (Location) o;
        return name.equals(l.name) && country.equals(l.country);
    }
}

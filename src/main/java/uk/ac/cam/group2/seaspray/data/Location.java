package uk.ac.cam.group2.seaspray.data;

import org.json.*;

public class Location {
    public final String name;
    public final double lat;
    public final double lon;
    public final String country;

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

    // Display in list as "Location (Country Code)"
    @Override
    public String toString() {
        return name + " (" + country + ")";
    }

    // needed to remove duplicates with stream.distinct()
    @Override
    public int hashCode() {
        return (name + country).hashCode();
    }

    // needed to remove duplicates with stream.distinct()
    @Override
    public boolean equals(Object o) {
        if (o.getClass() != Location.class) {
            return false;
        }
        Location l = (Location) o;
        return name.equals(l.name) && country.equals(l.country);
    }
}

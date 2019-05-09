package uk.ac.cam.group2.seaspray.data;

import org.json.*;
import java.util.*;

public class GetData {
    public static List<Daily> localWeather() throws OSNotSupported {
        String os = System.getProperty("os.name");
        if (os.contains("Windows") || os.contains("Linux")){
            // Not implemented as OS specific, default to cambridge
            // Implementing for Android / iOS would require additional packages to be included
            // This was not done as they would not be used for demonstrations only being done on Laptop
            double lon = 0.123;
            double lat = 52.205;
            return getWeather(lat,lon);
        } else {
            // Other devices not supported
            throw new OSNotSupported(os);
        }
    }

    public static List<Location> getPlaces(String where){
        String response = JsonReader.jsonGetRequest("http://api.openweathermap.org/data/2.5/find?q="+where+"&type=like&APPID=f4c78c6cc6d74b4862713c5f05505174");
        JSONObject o = new JSONObject(response);
        JSONArray ar = o.getJSONArray("list");
        List<Location> places = new LinkedList<>();
        for (int i = 0; i < ar.length(); i++) {
            places.add(new Location(ar.getJSONObject(i)));
        }
        return removeDuplicates(places);
    }

    public static List<Location> removeDuplicates(List<Location> original){
        TreeSet<Location> noDups = new TreeSet<>(Comparator.comparing(Location::toString));
        noDups.addAll(original);
        return new LinkedList<>(noDups);
    }

    public static List<Daily> getWeather(double lat, double lon){
        String url = "http://api.worldweatheronline.com/premium/v1/marine.ashx?key=7ec22fe9f839499e8c7122127193004&format=json&q=";
        url += lat+","+lon;
        String s = JsonReader.jsonGetRequest(url);
        List<Daily> data = new LinkedList<>();
        JSONArray days = new JSONObject(s).getJSONObject("data").getJSONArray("weather");
        for (int i = 0; i < days.length(); i++){
            data.add(new Daily(days.getJSONObject(i)));
        }
        return data;
    }
}
package uk.ac.cam.group2.seaspray.data;

import org.json.*;
import java.util.*;

public class GetData {

    private static final String tideKey = "10f60b65-d8ff-452c-b680-d9dd8c98bbaa";


    // Example: https://www.worldtides.info/api?extremes&lat=33.768321&lon=-118.195617&key=10f60b65-d8ff-452c-b680-d9dd8c98bbaa

    // unsure what to return yet
    public static double[] tideTimes(double lat, double lon){
        String url = "http://www.worldtides.info/api?extremes&lat="+lat+"&lon="+lon+"&key="+tideKey;
        String response = JsonReader.jsonGetRequest(url);
        JSONArray ar = new JSONObject(response).getJSONArray("extremes");


        // NOTE: Returns error if data not available within a degree of location requested


        return null;
    }

    public static double[] localWeather() {
        // Not implemented as mobile device specific
        // Would require additional packages not used in practice
        // Returns lat, lon
        return new double[] {52.205,0.123};
    }

    public static List<Location> getPlaces(String where){
        String response = JsonReader.jsonGetRequest("http://api.openweathermap.org/data/2.5/find?q="+where+"&type=like&APPID=f4c78c6cc6d74b4862713c5f05505174");
        if (response == null){
            return new LinkedList<>();
        }
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

    public static List<DailyData> getWeather(double lat, double lon){
        String url = "http://api.worldweatheronline.com/premium/v1/marine.ashx?key=7ec22fe9f839499e8c7122127193004&format=json&q=";
        url += lat+","+lon;
        String s = JsonReader.jsonGetRequest(url);
        return JsonReader.readData(new JSONObject(s));
    }
}
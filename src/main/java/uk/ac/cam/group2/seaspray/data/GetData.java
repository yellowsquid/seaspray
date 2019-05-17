package uk.ac.cam.group2.seaspray.data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.json.JSONArray;
import org.json.JSONObject;

public class GetData {
    private static final String TIDE_KEY = "10f60b65-d8ff-452c-b680-d9dd8c98bbaa";
    private static final String MARINE_KEY = "7ec22fe9f839499e8c7122127193004";
    private static final String SEARCH_KEY = "f4c78c6cc6d74b4862713c5f05505174";
    private static final String TIDE_BASE = "http://www.worldtides.info/api";
    private static final String MARINE_BASE =
            "http://api.worldweatheronline.com/premium/v1/marine.ashx";
    private static final String SEARCH_BASE = "http://api.openweathermap.org/data/2.5/find";

    public static Stream<JSONObject> arrayToStream(JSONArray array) {
        return IntStream.range(0, array.length()).mapToObj(i -> array.getJSONObject(i));
    }

    public static List<TideData> tideTimes(Location location) {
        String url =
                TIDE_BASE
                        + "?extremes"
                        + "&lat="
                        + location.lat
                        + "&lon="
                        + location.lon
                        + "&key="
                        + TIDE_KEY;
        String response = JsonReader.jsonGetRequest(url);

        if (response == null) {
            return List.of();
        }

        JSONObject output = new JSONObject(response);

        if (output.getInt("status") == 400) {
            return List.of();
        }

        JSONArray ar = new JSONObject(response).getJSONArray("extremes");

        return arrayToStream(ar)
                .map(o -> new TideData(o.getString("date"), o.getString("type")))
                .collect(Collectors.toList());
    }

    public static Location getCurrentLocation() {
        // Not implemented as mobile device specific
        // Would require additional packages not used in practice
        return new Location("Cambridge", 52.205, 0.123, "GB");
    }

    public static List<Location> getPlaces(String where) {
        String response =
                JsonReader.jsonGetRequest(
                        SEARCH_BASE + "?q=" + where + "&type=like" + "&APPID=" + SEARCH_KEY);

        if (response == null) {
            return List.of();
        }

        JSONObject o = new JSONObject(response);
        JSONArray ar = o.getJSONArray("list");
        return arrayToStream(ar)
                .map(Location::new)
                .filter(GetData::dataExists)
                .distinct()
                .collect(Collectors.toList());
    }

    public static List<DailyData> getWeather(Location l) {
        String url = MARINE_BASE + "?key=" + MARINE_KEY + "&format=json&q=" + l.lat + "," + l.lon;
        String s = JsonReader.jsonGetRequest(url);
        return JsonReader.readData(new JSONObject(s));
    }

    private static boolean dataExists(Location l) {
        String url = MARINE_BASE + "?key=" + MARINE_KEY + "&format=json&q=" + l.lat + "," + l.lon;
        JSONObject s = new JSONObject(JsonReader.jsonGetRequest(url)).getJSONObject("data");
        return s.has("weather");
    }
}

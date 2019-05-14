package uk.ac.cam.group2.seaspray.data;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import org.json.*;

public class JsonReader {

    public static List<DailyData> readData(JSONObject o){
        JSONArray days = o.getJSONObject("data").getJSONArray("weather");
        LinkedList<DailyData> response = new LinkedList<>();
       // DailyData[] response = new DailyData[days.length()];
        for (int i = 0; i < days.length(); i++){
            response.add(new DailyData(days.getJSONObject(i)));
        }
        return response;
    }

    private static String streamToString(InputStream inputStream) {
        return new Scanner(inputStream, StandardCharsets.UTF_8).useDelimiter("\\Z").next();
    }

    public static String jsonGetRequest(String urlQueryString) {
        String json = null;
        try {
            URL url = new URL(urlQueryString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("charset", "utf-8");
            connection.connect();
            InputStream inStream = connection.getInputStream();
            json = streamToString(inStream); // input stream to string
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return json;
    }
}

package uk.ac.cam.group2.seaspray.data;

import org.json.*;


public class DailyData {
    private final String date; // TODO: Replace with Date object
    private final String sunrise;
    private final String sunset;
    private final HourlyData[] hours;

    public String getDate() {
        return date;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public HourlyData[] getHours() {
        return hours;
    }

    public DailyData(JSONObject day){
        date = day.getString("date");
        JSONObject astro = day.getJSONArray("astronomy").getJSONObject(0);
        sunrise = astro.getString("sunrise");
        sunset = astro.getString("sunset");

        JSONArray hourData = day.getJSONArray("hourly");
        hours = new HourlyData[hourData.length()];
        for (int i = 0; i < hourData.length(); i++){
            hours[i] = new HourlyData(hourData.getJSONObject(i));
        }
    }
}
package uk.ac.cam.group2.seaspray.data;

import org.json.*;


public class DailyData {
    String date;
    String sunrise;
    String sunset;
    HourlyData[] hours;

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
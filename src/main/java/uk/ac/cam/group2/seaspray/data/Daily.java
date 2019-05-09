package uk.ac.cam.group2.seaspray.data;

import org.json.*;


public class Daily {
    String date;
    int maxTempC;
    int minTempC;
    String sunrise;
    String sunset;
    Hourly[] hours;

    public Daily(JSONObject day){
        date = day.getString("date");
        maxTempC = day.getInt("maxtempC");
        minTempC = day.getInt("mintempC");
        JSONObject astro = day.getJSONArray("astronomy").getJSONObject(0);
        sunrise = astro.getString("sunrise");
        sunset = astro.getString("sunset");

        JSONArray hourData = day.getJSONArray("hourly");
        hours = new Hourly[hourData.length()];
        for (int i = 0; i < hourData.length(); i++){
            hours[i] = new Hourly(hourData.getJSONObject(i));
        }
    }
}
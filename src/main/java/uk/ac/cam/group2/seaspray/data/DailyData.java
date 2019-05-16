package uk.ac.cam.group2.seaspray.data;

import org.json.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DailyData {
    private final Date date; // TODO: Replace with Date object
    private final String sunrise;
    private final String sunset;
    private final HourlyData[] hours;

    public Date getDate() {
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
        String format = "YYYY-MM-dd";
        SimpleDateFormat form = new SimpleDateFormat(format);
        try {
            date = form.parse(day.getString("date"));
        } catch (ParseException e){
            throw new RuntimeException(e);
        }
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
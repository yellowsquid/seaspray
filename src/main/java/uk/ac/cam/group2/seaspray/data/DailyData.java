package uk.ac.cam.group2.seaspray.data;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import java.util.stream.IntStream;
import java.util.stream.Collectors;

public class DailyData {
    private final Date date;
    private final String sunrise;
    private final String sunset;
    private final List<HourlyData> hours;

    public Date getDate() {
        return date;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public List<HourlyData> getHours() {
        return List.copyOf(hours);
    }

    public DailyData(JSONObject day) throws ParseException {
        this(new SimpleDateFormat("yyyy-MM-dd").parse(day.getString("date")),
             day.getJSONArray("astronomy").getJSONObject(0).getString("sunrise"),
             day.getJSONArray("astronomy").getJSONObject(0).getString("sunset"),
             IntStream.range(0, day.getJSONArray("hourly").length())
                 .mapToObj(i -> day.getJSONArray("hourly").getJSONObject(i))
                 .map(o -> new HourlyData(o))
                 .collect(Collectors.toList()));
    }

    public DailyData(Date date, String sunrise, String sunset, List<HourlyData> hourlyData) {
        this.date = date;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.hours = hourlyData;
    }
}

package uk.ac.cam.group2.seaspray.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import org.json.JSONObject;

public class DailyData {
    private final Calendar date;
    private final String sunrise; // String since printed as text, never processed
    private final String sunset;
    private final List<HourlyData> hours;

    public Calendar getDate() {
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
        this.date = Calendar.getInstance();
        this.date.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(day.getString("date")));
        this.sunrise = day.getJSONArray("astronomy").getJSONObject(0).getString("sunrise");
        this.sunset = day.getJSONArray("astronomy").getJSONObject(0).getString("sunset");
        this.hours =
                GetData.arrayToStream(day.getJSONArray("hourly"))
                        .map(o -> new HourlyData(o, this.date))
                        .collect(Collectors.toList());
    }
}

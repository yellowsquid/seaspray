package uk.ac.cam.group2.seaspray.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TideData {
    private final Date time;
    private final boolean high;

    public Date getTime() {
        return time;
    }

    public boolean isHigh() {
        return high;
    }

    public String getTimeString() {
        String format = "HH:mm";
        SimpleDateFormat form = new SimpleDateFormat(format);
        return form.format(time);
    }

    public TideData(String date, String type) {
        high = type.equals("High") ? true : false;
        String format = "YYYY-MM-dd'T'HH:mm+SSSS";
        SimpleDateFormat form = new SimpleDateFormat(format);
        try {
            time = form.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        TideData d = new TideData("2019-05-16T09:24+0000", "High");

        System.out.println(d.getTimeString());
    }
}

package uk.ac.cam.group2.seaspray.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TideData {
    private final Date time;
    private final boolean high;

    public TideData(String date, String type){
        high = type.equals("High") ? true : false;
        String format = "YYYY-MM-dd'T'HH:mm+XXXX";
        SimpleDateFormat form = new SimpleDateFormat(format);
        try {
            time = form.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
}

package uk.ac.cam.group2.seaspray.widget;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import uk.ac.cam.group2.seaspray.data.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.*;
import java.util.Date;

public class DailyWidget extends JPanel {
    public static String dayOfWeek(DailyData d){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String day;
        try{
            Date date = format.parse(d.getDate());
            DateFormat format2 = new SimpleDateFormat("EE");
            day = format2.format(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return day;
    }

    public DailyWidget (DailyData d){
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weighty = 0.2;
        c.weightx = 1.0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        setBorder(new EmptyBorder(2,10,2,2));
        JLabel title = new JLabel(dayOfWeek(d));
        Font labelFont = title.getFont();
        title.setFont(new Font(labelFont.getName(), Font.BOLD, 18));
        title.setBorder(BorderFactory.createMatteBorder(1,-1,-1,-1,Color.BLACK));
        add(title,c);

        c.weighty = 1.0;
        c.gridy = 1;
        add(new HourlyWidget(d.getHours()[3]),c);

        c.gridy = 2;
        add(new HourlyWidget(d.getHours()[5]),c);
    }
}

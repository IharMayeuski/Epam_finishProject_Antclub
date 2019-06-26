package by.epam.club.tool;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class CreateDate {
    public String takeDate(Date moment){
        DateFormat dateFormat = SimpleDateFormat.getDateTimeInstance(SimpleDateFormat.SHORT, SimpleDateFormat.SHORT);
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Minsk"));
        System.out.println(dateFormat.format(moment));
        return dateFormat.format(moment);
    }
}

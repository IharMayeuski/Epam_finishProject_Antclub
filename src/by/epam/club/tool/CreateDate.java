package by.epam.club.tool;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * The class is for substitution Date format of the date to String format this date
 *
 * @author Maeuski Igor
 * @version 1.0
 */

public class CreateDate {
    /*
    @return date in the String format
     */
    public String takeDate(Date moment){
        // FIXME: 7/15/2019
//        DateFormat dateFormat = SimpleDateFormat.getDateTimeInstance(SimpleDateFormat.SHORT, SimpleDateFormat.SHORT);
//        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Minsk"));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        return dateFormat.format(moment);
    }
}

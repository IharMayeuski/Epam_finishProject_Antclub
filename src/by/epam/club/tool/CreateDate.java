package by.epam.club.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    public String takeDate(Date moment) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm/dd.MM.yyyy");
        return dateFormat.format(moment);
    }
}

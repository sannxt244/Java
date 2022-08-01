package Utils;

import java.sql.Date;
import java.time.LocalDate;

public class DateTime {

    public static Date addMonth(Date oldDate, int month) {
        // Take a date
        LocalDate date = LocalDate.parse(oldDate.toString());
        // Add 2 months to the date
        LocalDate newDate = date.plusMonths(month);
        return Date.valueOf(newDate);
    }
}

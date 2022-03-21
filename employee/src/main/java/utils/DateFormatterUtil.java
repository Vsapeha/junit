package utils;

import lombok.SneakyThrows;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class DateFormatterUtil {

    @SneakyThrows
    public static Date parseDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        return format.parse(date);
    }

    public static String formatDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        return format.format(date);
    }

    @SneakyThrows
    public static Map<String, String> formatDateForCalendar(String date) {
        Map<String, String> result = new HashMap<String, String>();
        Date parseDate = parseDate(date);
        result.put("day", new SimpleDateFormat("d").format(parseDate));
        result.put("month", new SimpleDateFormat("MMM").format(parseDate));
        result.put("year", new SimpleDateFormat("yyyy").format(parseDate));
        return result;
    }

}
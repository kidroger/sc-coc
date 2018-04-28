package me.shufork.common.utils;

import org.joda.time.DateTime;
import org.joda.time.ReadableDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

public final class CocDateTimeUtil {

    /**
     * ISO8601 without '-'
     */
    public static final String COC_DATE_TIME_FORMAT="yyyyMMdd'T'HHmmss.SSS'Z'";

    public static DateTime parse(String source){
        return DateTimeFormat.forPattern(COC_DATE_TIME_FORMAT).parseDateTime(source);
    }
    public static String format(ReadableDateTime joda){
        return format(joda, DateTimeFormat.forPattern(COC_DATE_TIME_FORMAT));
    }

    public static String format(Date date){
        return format(new DateTime(date), DateTimeFormat.forPattern(COC_DATE_TIME_FORMAT));
    }
    public static String format(ReadableDateTime joda,DateTimeFormatter formatter){
        return formatter.print(joda);
    }
}

package com.gyp.jedis.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Description:
 *
 * @author GYP
 * @version 1.0
 */
public class TimeFormatUtil {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String format(Date date) {
        return format(date, DATE_TIME_FORMATTER);
    }

    public static String format(Date date, DateTimeFormatter dateTimeFormatter) {
        return date2LocalDateTime(date)
                .format(dateTimeFormatter);
    }


    private static LocalDateTime date2LocalDateTime(Date date) {
        Instant instant = date.toInstant();
        return instant.atZone(Inner.ZONE_ID).toLocalDateTime();
    }


    private static class Inner {
        private static final ZoneId ZONE_ID = ZoneId.systemDefault();
    }

    public static void main(String[] args) {
        TimeFormatUtil.format(new Date());
    }
}

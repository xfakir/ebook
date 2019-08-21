package com.ncu.ebook.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @ClassName : DateUtils
 * @Description :
 * @Author : xfakir
 * @Date : 2019-03-11 13:40
 * @Version : 1.0
 */
public class DateUtils {
    private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static LocalDateTime nowWithLocalDateTime() {
        return LocalDateTime.now();
    }

    public static Date nowWithDate() {
        return new Date();
    }

    public static String nowWithString() {
        return nowWithStringByFormat(DATE_TIME_FORMATTER);
    }

    public static String nowWithStringByFormat(DateTimeFormatter dateTimeFormatter) {
        return LocalDateTime.now().format(dateTimeFormatter);
    }

    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Long asTimestamp(Date date) {
        return date.getTime();
    }
}

package com.ncu.ebook.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName : DateUtils
 * @Description :
 * @Author : xfakir
 * @Date : 2019-03-11 13:40
 * @Version : 1.0
 */
public class DateUtils {
    private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String now() {
        return now(DATE_TIME_FORMATTER);
    }

    public static String now(DateTimeFormatter dateTimeFormatter) {
        return LocalDateTime.now().format(dateTimeFormatter);
    }
}

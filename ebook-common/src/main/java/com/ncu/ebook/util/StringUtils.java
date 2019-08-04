package com.ncu.ebook.util;


import com.ncu.ebook.constant.Charsets;

/**
 * @ClassName : StringUtils
 * @Description :
 * @Author : xfakir
 * @Date : 2019-02-22 22:34
 * @Version : 1.0
 */
public class StringUtils {
    public static byte[] toUtf8Bytes(String data) {
        return data.getBytes(Charsets.UTF_8);
    }

    public static Boolean isEmptyOrNull(String data) {
        return null == data || "".equals(data);
    }
}

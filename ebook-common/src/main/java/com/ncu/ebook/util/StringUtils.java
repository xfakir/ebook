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

    public static Boolean equalsIgnoreCase(String var1, String var2) {

        return var1.equalsIgnoreCase(var2);
    }

    public static String join(final Object[] array, final String separator) {
        if (array == null) {
            return null;
        }
        return join(array, separator, 0, array.length);
    }

    public static String join(final Object[] array, String separator, final int startIndex, final int endIndex) {
        if (array == null) {
            return null;
        }
        if (separator == null) {
            separator = "";
        }

        // endIndex - startIndex > 0:   Len = NofStrings *(len(firstString) + len(separator))
        //           (Assuming that all Strings are roughly equally long)
        final int noOfItems = endIndex - startIndex;
        if (noOfItems <= 0) {
            return "";
        }

        final StringBuilder buf = new StringBuilder(noOfItems * 16);

        for (int i = startIndex; i < endIndex; i++) {
            if (i > startIndex) {
                buf.append(separator);
            }
            if (array[i] != null) {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }
}

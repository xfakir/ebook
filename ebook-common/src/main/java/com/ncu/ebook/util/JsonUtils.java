package com.ncu.ebook.util;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * @ClassName : JsonUtils
 * @Description :
 * @Author : xfakir
 * @Date : 2019-02-22 20:01
 * @Version : 1.0
 */
public class JsonUtils {
    public static String toJSONString(Object object) {
        return JSON.toJSONString(object);
    }

    public static <T> T toObject(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    public static <T> List<T> toArray(String json, Class<T> clazz) {
        return JSON.parseArray(json, clazz);
    }

}

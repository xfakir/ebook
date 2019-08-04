package com.ncu.ebook.util;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

/**
 * @ClassName : BeanCopierUtils
 * @Description :
 * @Author : xfakir
 * @Date : 2019-02-21 10:49
 * @Version : 1.0
 */
public class BeanCopierUtils {

    public static void copy(Object sourceObject, Object targetObject, Converter converter) {
        BeanCopier.create(sourceObject.getClass(),targetObject.getClass(),converter != null)
                .copy(sourceObject, targetObject, converter);
    }

}

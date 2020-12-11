package com.gyp.jedis.util;

import org.springframework.beans.BeanUtils;

/**
 * Description: Converter Util
 *
 * @author GYP
 * @version 1.0
 */
public class ConverterUtil {

    public static <T, F> F convert(T source, F target) {

        BeanUtils.copyProperties(source, target);

        return target;
    }

}

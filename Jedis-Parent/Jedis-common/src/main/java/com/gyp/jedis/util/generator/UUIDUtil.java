package com.gyp.jedis.util.generator;

import java.util.UUID;

/**
 * Description:
 *
 * @author GYP
 * @version 1.0
 */
public class UUIDUtil {

    public static String getUUID32() {
        return getUUID().replace("-", "").toLowerCase();
    }

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }
}

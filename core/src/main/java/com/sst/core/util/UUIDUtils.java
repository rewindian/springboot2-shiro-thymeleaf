package com.sst.core.util;

import java.util.UUID;

/**
 * @Author: Ian
 * @Date: 2019/4/11
 */
public final class UUIDUtils {

    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}

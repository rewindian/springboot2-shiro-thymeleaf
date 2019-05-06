package com.sst.core.util;

import lombok.experimental.UtilityClass;

import java.util.UUID;

/**
 * 密码工具类
 */
@UtilityClass
public class PasswordUtil {

    /**
     * 验证密码
     *
     * @param inputPassword 用户输入明文密码
     * @param storePassword 数据库存储的加密后密码
     * @param salt          盐值
     * @return
     */
    public static boolean validatePassword(String inputPassword, String storePassword, String salt) {
        return storePassword.equalsIgnoreCase(encodePassword(inputPassword, salt));
    }

    /**
     * 密码加密
     *
     * @param inputPassword 用户输入明文密码
     * @param salt 盐值
     * @return
     */
    public static String encodePassword(String inputPassword, String salt) {
        return EncryptUtils.md5(inputPassword + salt);
    }

    /**
     * 生成盐值
     *
     * @return
     */
    public static String generateSalt() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20);
    }
}

package com.sst.core.util;

import com.sst.core.exception.CustomException;
import org.springframework.util.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

public final class EncryptUtils {

    /**
     * EDS的加密解密代码
     */
    private static final byte[] DES_KEY = {21, 1, -110, 82, -32, -85, -128, -65};

    @SuppressWarnings("restriction")
    public static String encryptBasedDes(String data) {
        String encryptedData = null;
        try {
            // DES算法要求有一个可信任的随机数源
            SecureRandom sr = new SecureRandom();
            DESKeySpec deskey = new DESKeySpec(DES_KEY);
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(deskey);
            // 加密对象
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key, sr);
            // 加密，并把字节数组编码成字符串
            encryptedData = new sun.misc.BASE64Encoder().encode(cipher.doFinal(data.getBytes()));
        } catch (Exception e) {
            // log.error("加密错误，错误信息：", e);
            throw new CustomException("DES加密错误", e);
        }
        return encryptedData;
    }

    @SuppressWarnings("restriction")
    public static String decryptBasedDes(String cryptData) {
        String decryptedData = null;
        try {
            // DES算法要求有一个可信任的随机数源
            SecureRandom sr = new SecureRandom();
            DESKeySpec deskey = new DESKeySpec(DES_KEY);
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(deskey);
            // 解密对象
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key, sr);
            // 把字符串进行解码，解码为为字节数组，并解密
            decryptedData = new String(cipher.doFinal(new sun.misc.BASE64Decoder().decodeBuffer(cryptData)));
        } catch (Exception e) {
            throw new CustomException("DES解密错误", e);
        }
        return decryptedData;
    }

    public static String md5(String cryptData){
        return DigestUtils.md5DigestAsHex(cryptData.getBytes());
    }

    public static void main(String[] args) {
        String str = EncryptUtils.md5("123456"+"YzcmCZNvbXocrsz9dm8e");
        System.out.println(str);
//        String str2 = EncryptUtils.decryptBasedDes(str);
//        System.out.println(str2);

    }

}

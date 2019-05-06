package com.sst.core.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ian
 * @date 2018/3/29
 */
public final class StrUtils {

    private StrUtils() {
    }

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }


    public static String fullFillStringNum(int strLength, int num) {
        String nextCodeStr = String.valueOf(num);
        StringBuilder sb = new StringBuilder();
        if (nextCodeStr.length() < strLength) {
            for (int i = 0; i < strLength - nextCodeStr.length(); i++) {
                sb.append("0");
            }
            sb.append(nextCodeStr);
        }
        return sb.toString();
    }

    /**
     * 驼峰法转下划线
     *
     * @param line 源字符串
     * @return 转换后的字符串
     */
    public static String camel2Underline(String line) {
        return camel2Underline(line, true);
    }

    public static String camel2UnderlineLower(String line) {
        return camel2Underline(line, false);
    }

    public static String camel2Underline(String line, Boolean isUpperCase) {
        if (null == isUpperCase) {
            isUpperCase = true;
        }
        if (line == null || "".equals(line)) {
            return "";
        }
        line = String.valueOf(line.charAt(0)).toUpperCase().concat(line.substring(1));
        StringBuffer sb = new StringBuffer();
        Pattern pattern = Pattern.compile("[A-Z]([a-z\\d]+)?");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            String word = matcher.group();
            sb.append(isUpperCase ? word.toUpperCase() : word.toLowerCase());
            sb.append(matcher.end() == line.length() ? "" : "_");
        }
        return sb.toString();
    }

    /**
     * 下划线转驼峰法
     *
     * @param line       源字符串
     * @param smallCamel 大小驼峰,是否为小驼峰
     * @return 转换后的字符串
     */
    public static String underline2Camel(String line, boolean smallCamel) {
        if (line == null || "".equals(line)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        Pattern pattern = Pattern.compile("([A-Za-z\\d]+)(_)?");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            String word = matcher.group();
            sb.append(smallCamel && matcher.start() == 0 ? Character.toLowerCase(word.charAt(0)) : Character.toUpperCase(word.charAt(0)));
            int index = word.lastIndexOf('_');
            if (index > 0) {
                sb.append(word.substring(1, index).toLowerCase());
            } else {
                sb.append(word.substring(1).toLowerCase());
            }
        }
        return sb.toString();
    }


    public static String strForSql(String str) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        String[] strs = str.split(",");
        List<String> stringList = new ArrayList<>();
        for (String s : strs) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("'");
            stringBuffer.append(s);
            stringBuffer.append("'");
            stringList.add(stringBuffer.toString());
        }
        return StringUtils.collectionToDelimitedString(stringList, ",");
    }


    public static void main(String[] args) {
        List<String> list = Arrays.asList("好", "坏");
        System.err.println(JSONObject.toJSONString(list));
//        String str = getOrderOfFour(str);
//        System.out.print(getOrderOfFour("12"));
//        System.out.print(getOrderOfTwo("2",""));
    }

}

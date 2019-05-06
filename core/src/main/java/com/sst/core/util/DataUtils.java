package com.sst.core.util;

import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Ian
 * @Date: 2019/4/11
 */
public final class DataUtils {

    /**
     * 将List转为map , map的key是T中的prop属性的值
     * @param prop
     * @param list
     * @param <T>
     * @return
     */
    public static <T> Map<String, List<T>> listToMap(String prop, List<T> list) {
        if (StringUtils.isEmpty(prop)) {
            return new HashMap<>();
        }
        String getter = "get" + prop.substring(0, 1).toUpperCase() + prop.substring(1);
        Map<String, List<T>> resultMap = new HashMap<>();
        if (null != list && list.size() > 0) {
            list.forEach(item -> {
                String key = "";
                Class clazz = item.getClass();
                Method[] methods = clazz.getDeclaredMethods();
                for (Method method : methods) {
                    if (getter.equals(method.getName())) {
                        try {
                            key = method.invoke(item).toString();
                        } catch (Exception e) {
                            e.printStackTrace();
                            continue;
                        }
                        break;
                    }
                }

                if (resultMap.containsKey(key)) {
                    (resultMap.get(key)).add(item);
                } else {
                    List<T> tempList = new ArrayList<>();
                    tempList.add(item);
                    resultMap.put(key, tempList);
                }
            });
        }
        return resultMap;
    }
}

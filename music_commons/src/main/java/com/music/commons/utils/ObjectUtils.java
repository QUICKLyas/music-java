package com.music.commons.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public class ObjectUtils {
    /**
     * 对象转Map
     * @param object
     * @return
     */
    public static Map beanToMap(Object object){
        return JSONObject.parseObject(JSON.toJSONString(object),Map.class);
    }

    public static <T> T mapToBean(Map map,Class<T> beanClass) {
        return JSONObject.parseObject(JSON.toJSONString(map),beanClass);
    }
}

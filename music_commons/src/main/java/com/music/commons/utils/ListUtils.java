package com.music.commons.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListUtils {
    public static List<Integer> makeListKeyId(List<Map> objectList){
        List<Integer> resultList = new ArrayList<>();
        for (Map item : objectList){
            resultList.add(Integer.parseInt(item.get("id").toString()));
        }
        return resultList;
    }

}

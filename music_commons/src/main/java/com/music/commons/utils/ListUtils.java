package com.music.commons.utils;

import java.util.*;
import java.util.Map.Entry;

public class ListUtils {
    public static List<Integer> makeListKeyId(List<Map> objectList){
        List<Integer> resultList = new ArrayList<>();
        for (Map item : objectList){
            resultList.add(Integer.parseInt(item.get("id").toString()));
        }
        return resultList;
    }

    public static <T> List<T> catchValueFromHashMap(HashMap<String,T> map) {
        List<T> returnResult = new LinkedList<T>();
        Set<Map.Entry<String,T>> entrySet = map.entrySet();
        Iterator<Map.Entry<String,T>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            returnResult.add(iterator.next().getValue());
        }
        return returnResult;
    }

    public static List<Entry<String,Double>> sortHashMapByValue(HashMap<String,Double> map) {
        List<Entry<String,Double>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list,((o1, o2) -> (int) ((o2.getValue() - o1.getValue()) * 10000)));

        System.out.println(list);
        
        return list;
    }
}

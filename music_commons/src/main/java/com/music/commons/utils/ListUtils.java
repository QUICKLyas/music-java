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

    public static List<String> sortHashMapByValue(HashMap<String,Double> map, Integer tagSize ) {
        List<Entry<String,Double>> list = new ArrayList<>(map.entrySet());
        list.sort(((o1, o2) -> (int) ((o2.getValue() - o1.getValue()) * 10000)));
        List<String> tagString = new ArrayList<>(tagSize);
        for (int i = 0 ; i < tagSize ; i++) {
            tagString.add(i, list.get(i).getKey());
        }
        return tagString;
    }

    public static String list2String(List<String> list) {
        String str = "";
        for (String item : list) {
            str += item +  "|";
        }

        str = str.substring(0,str.length()-1) ;
        return str;
    }
    public static List<List<String>> createListTag(List<String> tags) {
        List<List<String>> listLs = new ArrayList<>();
        for (String tag : tags) {
            List<String> item = new ArrayList<>();
            item.add(tag);
            listLs.add(item);
        }
        return listLs;
    }
}

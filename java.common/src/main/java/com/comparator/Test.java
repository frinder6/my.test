package com.comparator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by frinder_liu on 2016/8/4.
 */
public class Test {

    public static void main(String[] args) throws ParseException {
        List<Map<String, Object>> maps = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("number", 5);
        map.put("date", format.parse("2015-01-01"));
        map.put("string", "abc");
        maps.add(map);
        map = new HashMap<>();
        map.put("id", 2);
        map.put("number", 8);
        map.put("date", format.parse("2016-01-01"));
        map.put("string", "efg");
        maps.add(map);
        map = new HashMap<>();
        map.put("id", 3);
        map.put("number", 3);
        map.put("date", format.parse("2016-01-01"));
        map.put("string", "hhh");
        maps.add(map);
        map = new HashMap<>();
        map.put("id", 4);
        map.put("number", 5);
        map.put("date", format.parse("2014-01-01"));
        map.put("string", "hhh");
        maps.add(map);

        print(maps, format);

        String[] columns = {"number", "date"};
        String[] reverses = {"number"};

        MapComparator.SINGLE.sort(maps, columns, reverses);

        print(maps, format);


    }

    private static void print(List<Map<String, Object>> maps, SimpleDateFormat format) {
        for (Map<String, Object> tmp : maps) {
            for (Map.Entry<String, Object> entry : tmp.entrySet()) {
                if ("date".equalsIgnoreCase(entry.getKey())) {
                    System.out.print(entry.getKey() + " : " + format.format(entry.getValue()) + "   ");
                } else {
                    System.out.print(entry.getKey() + " : " + entry.getValue() + "   ");
                }
            }
            System.out.println();
        }
    }

}

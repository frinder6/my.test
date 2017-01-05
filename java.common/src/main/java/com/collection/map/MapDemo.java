package com.collection.map;

import org.apache.commons.collections.map.LinkedMap;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by frinder6 on 2016/12/28.
 */
public class MapDemo {

    public static void main(String[] args) {
        Map<String, Object> hashMap = new HashMap() {{
            put("abc", "abc");
            put("xyz", "xyz");
            put("fgh", "fgh");
        }};

        Map<String, Object> treeMap = new TreeMap() {{
            put("abc", "abc");
            put("xyz", "xyz");
            put("fgh", "fgh");
        }};

        Map<String, Object> linkedMap = new LinkedMap() {{
            put("abc", "abc");
            put("xyz", "xyz");
            put("fgh", "fgh");
        }};

        System.out.println("hashMap : " + hashMap);
        System.out.println("treeMap : " + treeMap);
        System.out.println("linkedMap : " + linkedMap);

    }

}

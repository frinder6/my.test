package com.collection.map;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by frinder6 on 2016/12/29.
 */
public class HashMapDemo {

    /**
     * 如何删除 map中的元素
     * 避免 fast-fail机制
     *
     * @param args
     */
    public static void main(String[] args) {
        Map<String, Object> hashMap = new HashMap() {{
            put("abc", "abc");
            put("xyz", "xyz");
            put("fgh", "fgh");
        }};

        System.out.println("before remove : " + hashMap);

        /**
         * 直接在 Map.Entry遍历中删除，抛出异常：
         * java.util.ConcurrentModificationException
         */
//        int i = 0;
//        for (Map.Entry<String, Object> e : hashMap.entrySet()) {
//            if (i == 1) {
//                hashMap.remove(e.getKey());
//            }
//            ++i;
//        }

        /**
         * 通过获取 keySet遍历 key的方式删除
         * 可正常删除
         */
//        Set<String> keys = hashMap.keySet();
//        int i = 0;
//        for (String key : keys) {
//            if (i == 1) {
//                hashMap.remove(key);
//            }
//            ++i;
//        }

        /**
         * 通过 entrySet().iterator()遍历中删除，抛出异常：
         * java.util.ConcurrentModificationException
         */
//        Iterator<Map.Entry<String, Object>> iterator = hashMap.entrySet().iterator();
//        int i = 0;
//        while (iterator.hasNext()) {
//            if (i == 1) {
//                hashMap.remove(iterator.next().getKey());
//            }
//            ++i;
//        }


        /**
         * 使用复制 map方式实现
         */
        Map<String, Object> copyMap = new HashMap<>(hashMap);
        int i = 0;
        for (Map.Entry<String, Object> e : copyMap.entrySet()) {
            if (i == 1) {
                hashMap.remove(e.getKey());
            }
            ++i;
        }


        System.out.println("after remove : " + hashMap);
    }

}

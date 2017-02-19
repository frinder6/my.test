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

        Object value;

        /**
         * 将 key=abc的 v追加 ...
         * 注意：不满足条件的返回 v，不能返回 null，否则全部会替换
         */
        hashMap.replaceAll((String k, Object v) -> {
            if ("abc".equalsIgnoreCase(k)) {
                return v + "...";
            }
            return v;
        });

        /**
         * key 存在，则使用 oldV+newV替换 oldV；
         * key 不存在，则添加 key=123；
         */
        value = hashMap.merge("abc", "123", (Object oldV, Object newV) -> oldV + "" + newV);
        System.out.println(value);

        /**
         * computeIfPresent & computeIfAbsent 集合
         */
        value = hashMap.compute("abcr", (String k, Object v) -> k);
        System.out.println(value);

        /**
         * 如果 key=abc在 hm中存在，则替换其值为 “原值123”
         */
        value = hashMap.computeIfPresent("abca", (String k, Object v) -> v + "123");
        System.out.println(value);

        /**
         * 如果 key=abcd 在 hm中不存在，则将 key=abcd & value=abcd 添加到 hm中
         */
        value = hashMap.computeIfAbsent("abcd", (String key) -> key);
        System.out.println(value);

        /**
         * 遍历
         */
        hashMap.forEach((String k, Object v) -> System.out.println(k + "=" + v));


//        System.out.println("before remove : " + hashMap);

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
//        Map<String, Object> copyMap = new HashMap<>(hashMap);
//        int i = 0;
//        for (Map.Entry<String, Object> e : copyMap.entrySet()) {
//            if (i == 1) {
//                hashMap.remove(e.getKey());
//            }
//            ++i;
//        }
//        System.out.println("after remove : " + hashMap);
    }

}

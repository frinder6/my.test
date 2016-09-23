package com.comparator;

import java.util.*;

/**
 * Created by frinder_liu on 2016/8/4.
 */
public class MapComparator_copy {


    public static final MapComparator_copy SINGLE = new MapComparator_copy();

    /**
     * @return
     */
    public List<Comparator<Map<String, Object>>> getComparatorList() {
        List<Comparator<Map<String, Object>>> list = new ArrayList<>();
        list.add(compareDateASC);
        list.add(compareNumberASC);
        return list;
    }

    /**
     * 排序方法
     *
     * @param resourceList
     * @param comparatorList
     */
    public void sort(List<Map<String, Object>> resourceList, final List<Comparator<Map<String, Object>>> comparatorList) {
        Comparator<Map<String, Object>> comparator = new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                for (Comparator<Map<String, Object>> comparator : comparatorList) {
                    if (comparator.compare(o1, o2) > 0) {
                        return 1;
                    } else if (comparator.compare(o1, o2) < 0) {
                        return -1;
                    }
                }
                return 0;
            }
        };
        Collections.sort(resourceList, comparator);
    }


    /**
     * 数字排序
     */
    private Comparator<Map<String, Object>> compareNumberASC = new Comparator<Map<String, Object>>() {
        @Override
        public int compare(Map<String, Object> o1, Map<String, Object> o2) {
            int map1Number = Integer.parseInt(o1.get("number").toString());
            int map2Number = Integer.parseInt(o2.get("number").toString());
            return map1Number < map2Number ? 1 : -1;
        }
    };


    /**
     * 日期排序
     */
    private Comparator<Map<String, Object>> compareDateASC = new Comparator<Map<String, Object>>() {
        @Override
        public int compare(Map<String, Object> o1, Map<String, Object> o2) {
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//            Date map1Date = null, map2Date = null;
//            try {
//                map1Date = format.parse(o1.get("date").toString());
//                map2Date = format.parse(o2.get("date").toString());
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
            Date map1Date = (Date) o1.get("date");
            Date map2Date = (Date) o1.get("date");
            return map1Date.compareTo(map2Date) * -1;
        }
    };


    private Comparator<Map<String, Object>> compareStringASC = new Comparator<Map<String, Object>>() {
        @Override
        public int compare(Map<String, Object> o1, Map<String, Object> o2) {
            int map1Number = Integer.parseInt(o1.get("string").toString());
            int map2Number = Integer.parseInt(o2.get("string").toString());
            return map1Number < map2Number ? 1 : -1;
        }
    };


}

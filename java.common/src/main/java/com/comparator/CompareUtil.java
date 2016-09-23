package com.comparator;

import java.util.Comparator;
import java.util.Date;
import java.util.Map;

/**
 * Created by frinder_liu on 2016/8/4.
 */
public class CompareUtil {

    public static <T> Comparator createComparator(int sort, String... fields) {
        return new ImComparator(sort, fields);
    }

    public static class ImComparator implements Comparator {
        int sort = 1;
        String[] fields;

        public ImComparator(int sort, String... fields) {
            this.sort = sort == -1 ? -1 : 1;
            this.fields = fields;
        }

        @Override
        public int compare(Object o1, Object o2) {
            int result = 0;
            Map<String, Object> map1 = (Map<String, Object>) o1;
            Map<String, Object> map2 = (Map<String, Object>) o2;
            for (String field : fields) {
                Object value1 = map1.get(field);
                Object value2 = map2.get(field);
                if (value1 == null || value2 == null) {
                    continue;
                }
                if (value1 instanceof Integer) { //Integer整数序排序
                    int v1 = Integer.valueOf(value1.toString());
                    int v2 = Integer.valueOf(value2.toString());
                    if (v1 == v2) continue;
                    if (sort == 1) {
                        return v1 > v2 ? 1 : -1;
                    } else if (sort == -1) {
                        return v1 > v2 ? -1 : 1;
                    }
                } else if (value1 instanceof Date) { //Date类型排序
                    Date d1 = (Date) value1;
                    Date d2 = (Date) value2;
                    if (d1.compareTo(d2) == 0) continue;
                    if (sort == 1) {
                        return d1.compareTo(d2);
                    } else if (sort == -1) {
                        return d2.compareTo(d1);
                    }
                } else if (value1 instanceof Long) { //Long排序
                    long v1 = Long.valueOf(value1.toString());
                    long v2 = Long.valueOf(value2.toString());
                    if (v1 == v2) continue;
                    if (sort == 1) {
                        return v1 > v2 ? 1 : -1;
                    } else if (sort == -1) {
                        return v1 > v2 ? -1 : 1;
                    }
                } else if (value1 instanceof Double) { //Double排序
                    Double v1 = Double.valueOf(value1.toString());
                    Double v2 = Double.valueOf(value2.toString());
                    if (v1 == v2) continue;
                    if (sort == 1) {
                        return v1 > v2 ? 1 : -1;
                    } else if (sort == -1) {
                        return v1 > v2 ? -1 : 1;
                    }
                }

            }
            return result;
        }

    }
}
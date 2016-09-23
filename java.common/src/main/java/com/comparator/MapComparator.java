package com.comparator;

import com.google.common.base.Function;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.ComparatorChain;

import java.util.*;

/**
 * Created by frinder_liu on 2016/8/4.
 */
public class MapComparator {


    public static final MapComparator SINGLE = new MapComparator();

    /**
     * 排序，默认升序
     *
     * @param resourceList
     * @param columns      需要排序列
     * @param reverses     需要逆排序列
     */
    public void sort(List<Map<String, Object>> resourceList, String[] columns, String[] reverses) {
        ComparatorChain chain = new ComparatorChain();
        for (String col : columns) {
            if (null != reverses && reverses.length > 0) {
                List<String> reverseList = Arrays.asList(reverses);
                if (reverseList.contains(col)) {
                    chain.addComparator(new BeanComparator(col), true);
                } else {
                    chain.addComparator(new BeanComparator(col));
                }
            } else {
                chain.addComparator(new BeanComparator(col));
            }
        }
        Collections.sort(resourceList, chain);
    }


    /**
     * guava ordering & ComparisonChain 使用
     * @param resourceList
     */
    public void sort(List<Map<String, Object>> resourceList) {
        /**
         * ordering 使用
         */
        Ordering<Map<String, Object>> ordering = Ordering.natural().nullsFirst().onResultOf(
                new Function<Map<String, Object>, Comparable>() {
                    @Override
                    public Comparable apply(Map<String, Object> stringObjectMap) {
                        return (Comparable) stringObjectMap.get("number");
                    }
                }
        );

        /**
         * 多条件ordering使用
         */
        Ordering<Map<String, Object>> ordering2 = Ordering.natural().from(new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                Date v1 = (Date) o1.get("date");
                Date v2 = (Date) o2.get("date");
                return v2.compareTo(v1);
            }

        }).compound(new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                int v1 = Integer.parseInt(o1.get("number").toString());
                int v2 = Integer.parseInt(o2.get("number").toString());
                return v1 - v2;
            }
        });


        /**
         * 多条件使用：ComparisonChain
         */
        Ordering<Map<String, Object>> flexOrdering = new Ordering<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> left, Map<String, Object> right) {
                return ComparisonChain.start().
                        compare(left.get("date"), right.get("date"), Ordering.from(new Comparator<Object>() {
                            @Override
                            public int compare(Object o1, Object o2) {
                                return ((Date) o1).compareTo((Date) o2);
                            }
                        })).
                        compare((int) left.get("number"), (int) right.get("number"), Ordering.natural().nullsLast().reverse()).
                        compare((int) left.get("id"), (int) right.get("id")).
                        result();
            }
        };

        ordering.sortedCopy(resourceList);
        ordering2.sortedCopy(resourceList);
        flexOrdering.sortedCopy(resourceList);
    }


}

package com.collection.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by frinder6 on 2016/12/28.
 */
public class ListDemo {

    public static void main(String[] args) {


        List<String> arrayList = new ArrayList() {{
            add("abc");
            add("xyz");
            add("fgh");
        }};

        List<String> linkedList = new LinkedList() {{
            add("abc");
            add("xyz");
            add("fgh");
        }};

        System.out.println("arrayList:" + arrayList);
        System.out.println("linkedList:" + linkedList);


        List<String> subArrayList = new ArrayList() {{
            add("abc");
            add("sub");
        }};

//        arrayList.addAll(1,subArrayList);
//        System.out.println(arrayList);
//
//        arrayList.removeAll(subArrayList);
//        System.out.println(arrayList);

//        arrayList.retainAll(subArrayList);
//        System.out.println(arrayList);
//        arrayList.replaceAll((String s) -> {
//            if ("abc".equalsIgnoreCase(s)) {
//                s = "123";
//            }
//            return s;
//        });
//        System.out.println(arrayList);
//
//        arrayList.set(1,"hello");
//        System.out.println(arrayList);
//
//        arrayList.add(1, "world");
//        System.out.println(arrayList);

//        ListIterator<String> listIterator = arrayList.listIterator(1);
//        while (listIterator.hasNext()) {
//            System.out.print(listIterator.next() + " ");
//        }
//        System.out.println();

//        System.out.println();
//        while (listIterator.hasPrevious()) {
//            System.out.print(listIterator.previous() + " ");
//        }

//        String[] array = {"123", "456", "789", "111", "222"};
//        print(array);
//        arrayList.toArray(array);
//        print(array);

//        arrayList.forEach((String s) -> System.out.print(s + " "));
//        System.out.println();

        arrayList.removeIf((String s) -> s.equals("xyz"));
        System.out.println(arrayList);
    }


    public static void print(Object[] array) {
        for (Object obj : array) {
            System.out.print(obj + " ");
        }
        System.out.println();
    }

}

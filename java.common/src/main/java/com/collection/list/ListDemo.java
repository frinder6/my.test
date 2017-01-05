package com.collection.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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


    }

}

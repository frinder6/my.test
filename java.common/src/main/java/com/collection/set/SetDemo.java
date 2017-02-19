package com.collection.set;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by frinder6 on 2016/12/28.
 */
public class SetDemo {

    public static void main(String[] args) {
        Set<String> haseSet = new HashSet() {{
            add("abc");
            add("xyz");
            add("fgh");
        }};

        System.out.println(haseSet.contains("Abc"));

        Set<String> linkedHashSet = new LinkedHashSet() {{
            add("abc");
            add("xyz");
            add("fgh");
        }};

        Set<String> treeSet = new TreeSet() {{
            add("abc");
            add("xyz");
            add("fgh");
        }};

        System.out.println("hashset:" + haseSet);
        System.out.println("linkedHashSet:" + linkedHashSet);
        System.out.println("treeSet:" + treeSet);
    }

}

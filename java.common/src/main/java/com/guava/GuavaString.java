package com.guava;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by frinder_liu on 2016/8/13.
 */
public class GuavaString {


    public static void main(String[] args) {

        // 返回目标对象n次重复字符串
        System.out.println(Strings.repeat("abc", 5));
        // 返回2个源对象头相同的字符
        System.out.println(Strings.commonPrefix("12abc", "123"));
        // 返回2个源对象尾相同的字符
        System.out.println(Strings.commonSuffix("12abc", "123ac"));

        // 判断是否为 null | ''
        String strNullOrEmpty1 = "";
        System.out.println(Strings.isNullOrEmpty(strNullOrEmpty1));
        String strNullOrEmpty2 = null;
        System.out.println(Strings.isNullOrEmpty(strNullOrEmpty2));

        // 统计‘多个’字符在字符串中出现的次数
        String strCountResource = "helloworldabcdefghjklmn";
        int count = CharMatcher.anyOf("ol").countIn(strCountResource);
        System.out.println(count);

        // 将数组拼接为String
        String[] arrayResource = {"abc", "123", null, "xyz", "789"};
        System.out.println(Joiner.on("|").skipNulls().join(arrayResource));

        // 将Map交接为String
        Map<String, String> mapResource = new HashMap(){{
            put("abc", 123);
            put("hello", "world");
        }};
        System.out.println(Joiner.on(";").withKeyValueSeparator(":").join(mapResource));


        // 折字符串
        String splitStrResource = "abc ,123,xyz,789";
        System.out.println(Splitter.on(",").trimResults().omitEmptyStrings().split(splitStrResource));



    }

}

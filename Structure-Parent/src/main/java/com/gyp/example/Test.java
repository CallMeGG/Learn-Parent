package com.gyp.example;

import java.util.TreeMap;

/**
 * Title: Test
 * Description: TODO
 * date 2021/5/31 17:03
 *
 * @author GYP
 * @version 1.0
 */
public class Test {
    public static void main(String[] args) {
        String aaa="23123da";
        int hash = aaa.hashCode();
        int i = (hash ^ (hash >>> 16))& 0x7fffffff;
        System.out.println(i);

        TreeMap treeMap = new TreeMap();
        Object o = treeMap.get(0);
    }
}

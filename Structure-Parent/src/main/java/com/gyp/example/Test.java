package com.gyp.example;

import java.util.HashMap;

/**
 * Title: Test
 * Description: TODO
 * date 2021/5/31 17:03
 *
 * @author GYP
 * @version 1.0
 */
public class Test {

    static final int MAXIMUM_CAPACITY = 1 << 30;
    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 17; i++) {
            map.put(i,i);
        }

        HashMap<Integer, Integer> map2 = new HashMap<>(17);
        for (int i = 0; i < 17; i++) {
            map2.put(i,i);
        }

        System.out.println(tableSizeFor(32));

    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }


    public static int[] test(int[] a, int taget) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < a.length; i++) {
            int cha = taget - a[i];
            if (map.containsKey(cha)) {
                return new int[]{map.get(cha), a[i]};
            }
            map.put(a[i], a[i]);
        }
        throw new IllegalArgumentException();
    }
}

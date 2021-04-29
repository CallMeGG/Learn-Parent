package com.gyp.example;

/**
 * Title: SolutionZhiChuXianYiCi
 * Description: 只出现一次的数
 * date 2021/4/29 20:10
 *
 * @author GYP
 * @version 1.0
 */
public class SolutionZhiChuXianYiCi {

    /**
     * 题目：给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素
     *
     * 思路：
     * 异或运算有以下三个性质：
     * 任何数和 00 做异或运算，结果仍然是原来的数，即 a 异或=a。
     * 任何数和其自身做异或运算，结果是 00，即 a异或a=0。
     * 异或运算满足交换律和结合律，a异或b异或a=b异或a异或a=b异或(a异或a)=b异或0=b
     */
    public int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }

}

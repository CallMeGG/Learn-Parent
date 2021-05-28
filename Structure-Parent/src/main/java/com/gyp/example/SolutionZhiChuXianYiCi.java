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
     * <p>
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


    public static void main(String[] args) {
        int arr[] = {1, 3, 5, 7, 1, 3, 5, 9};

        int a = 3 ^ 5;//  0011 ^ 0101  = 0110
        System.out.println(a);//6   0110
        System.out.println(a >> 1);//0011
        System.out.println(a >> 2);//0001
        System.out.println(a >> 3);//0000

        int[] ints = singleNumber2(arr);
        System.out.println(ints[0]);
        System.out.println(ints[1]);
    }

    /**
     * 一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字
     * @param arr
     * @return
     */
    public static int[] singleNumber2(int[] arr) {

        if (arr.length < 2)
            return arr;

        int[] result = new int[2];  //要返回的结果
        int res = 0;  //第一次对所有元素进行亦或操作结果
        for (int i : arr) {
            res ^= i;
        }
        int bitIndex = 0;
        for (int i = 0; i < 32; i++) {  //找出亦或结果为1的位。
            if (res >> i == 1) {
                bitIndex = i;
                break;
            }
        }

        for (int ci : arr) { //根据bitIndex为1，将元素分为两组
            if (ci >> bitIndex == 1)
                result[0] = ci;   //对应位为1，亦或得到的结果
            else
                result[1] ^= ci;   //对应位为0，亦或得到的结果
        }
        return result;
    }

    //也可以考虑用map

}

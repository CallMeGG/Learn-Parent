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

    class Solution {
        public int singleNumber(int[] nums) {
            int single = 0;
            for (int num : nums) {
                single ^= num;
            }
            return single;
        }
    }

}

package com.gyp.example;

import java.util.Arrays;

/**
 * Title: SolutionDuoShuYuanSu
 * Description: 多数元素
 * date 2021/4/29 20:27
 *
 * @author GYP
 * @version 1.0
 */
public class SolutionDuoShuYuanSu {


    /**
     * 题目：给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素
     *
     * 思路：排完序一定在中间
     */
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

}

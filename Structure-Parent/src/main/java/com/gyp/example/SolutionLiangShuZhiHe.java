package com.gyp.example;

import java.util.HashMap;
import java.util.Map;

/**
 * Title: 两数之和
 * date 2021/4/27 19:53
 *
 * @author GYP
 * @version 1.0
 */
public class SolutionLiangShuZhiHe {

    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标
     * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素
     */
    public int[] towSum1(int[] nums, int taget) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int chazhi = taget - nums[i];
            if (map.containsKey(chazhi)) {
                return new int[]{map.get(chazhi), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("no result");
    }

}

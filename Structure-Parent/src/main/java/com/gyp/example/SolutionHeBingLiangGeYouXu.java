package com.gyp.example;

import java.util.Arrays;

/**
 * Title: SolutionHeBingLiangGeYouXu
 * Description: 合并两个有序数组
 * date 2021/4/29 20:34
 *
 * @author GYP
 * @version 1.0
 */
public class SolutionHeBingLiangGeYouXu {

    /**
     * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
     *
     * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素。
     *
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i != n; ++i) {
            nums1[m + i] = nums2[i];
        }
        Arrays.sort(nums1);
    }

}

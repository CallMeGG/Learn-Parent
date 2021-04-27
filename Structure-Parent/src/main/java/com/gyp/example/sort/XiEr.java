package com.gyp.example.sort;

import java.util.Arrays;

/**
 * Title: XiEr
 * Description: 希尔排序
 * date 2020/11/25 15:39
 *
 * @author GYP
 * @version 1.0
 */
public class XiEr implements IArraySort {

    /**
     * 首先，选择增量 gap = 10/2 ，缩小增量继续以 gap = gap/2 的方式
     * <p>
     * 初始增量为 gap = 10/2 = 5，整个数组分成了 5 组
     * <p>
     * 按颜色划分为【 8 , 3 】，【 9 , 5 】，【 1 , 4 】，【 7 , 6 】，【 2 , 0 】
     * <p>
     * 对这分开的 5 组分别使用上节所讲的插入排序
     * <p>
     * 结果可以发现，这五组中的相对小元素都被调到前面了
     * <p>
     * 缩小增量 gap = 5/2 = 2，整个数组分成了 2 组
     * <p>
     * 【 3 , 1 , 0 , 9 , 7  】，【 5 , 6 , 8 , 4 , 2  】
     * <p>
     * 对这分开的 2 组分别使用上节所讲的插入排序
     * <p>
     * 此时整个数组的有序性是很明显的
     * <p>
     * 再缩小增量 gap = 2/2 = 1，整个数组分成了 1 组
     * <p>
     * 【 0, 2 , 1 , 4 , 3 , 5 , 7 , 6 , 9 , 0  】
     * <p>
     * 此时，只需要对以上数列进行简单的微调，不需要大量的移动操作即可完成整个数组的排序
     */


    @Override
    public int[] sort(int[] sourceArray) {

        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        int gap = 1;
        while (gap < arr.length) {
            gap = gap * 3 + 1;
        }

        while (gap > 0) {

            for (int i = gap; i < arr.length; i++) {
                int temp = arr[i];
                int j = i - gap;

                //这里保证 arr[j]<
                while (j >= 0 && arr[j] > temp) {
                    arr[j + gap] = arr[j];
                    j -= gap;
                }
                arr[j + gap] = temp;
            }
            gap = (int) Math.floor(gap / 3);
        }

        return arr;
    }

}

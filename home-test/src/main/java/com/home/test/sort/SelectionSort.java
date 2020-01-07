package com.home.test.sort;

/**
 * 选择排序：每一趟从待排序的记录中选出最小的元素，顺序放在已排好序的序列最后，直到全部记录排序完毕
 *
 * @author malikai
 * @version 1.0
 * @date 2020-1-7 15:03
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 45, 65, 33, 12};
        System.out.println("交换之前：");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        // 做第i趟排序
        for (int i = 0; i < arr.length - 1; i++) {
            int k = i;
            // 选最小的记录
            for (int j = k + 1; j < arr.length; j++) {
                if (arr[j] < arr[k]) {
                    //记下目前找到的最小值所在的位置
                    k = j;
                }
            }
            //在内层循环结束，也就是找到本轮循环的最小的数以后，再进行交换
            if (i != k) {
                //交换a[i]和a[k]
                int temp = arr[i];
                arr[i] = arr[k];
                arr[k] = temp;
            }
        }
        System.out.println();
        System.out.println("交换后：");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}

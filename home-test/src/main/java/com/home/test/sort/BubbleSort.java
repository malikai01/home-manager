package com.home.test.sort;

/**
 * 冒泡排序
 *
 * @author malikai
 * @version 1.0
 * @date 2020-1-7 15:06
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 45, 65, 33, 12};
        System.out.println("交换之前：");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        bubbleWay(arr);
        System.out.println();
        System.out.println("交换后：");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    /**
     * 冒泡排序方式1 ： 相邻两个数对比取小的
     *
     * @param arr
     * @return void
     * @author malikai
     * @date 2020-1-7 15:14
     * @version 1.0.0
     **/
    private static void bubbleWay(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}

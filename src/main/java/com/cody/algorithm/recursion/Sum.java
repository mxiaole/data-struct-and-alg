package main.java.com.cody.algorithm.recursion;

/**
 * 使用递归求解1到100的和
 */

public class Sum {
    // 求解一个数组的和
    public static int sum(int[] array) {
        return sum(array, 0);
    }

    // 计算l....n这个区间所有数字的
    public static int sum(int[] array, int l) {
        if (l == array.length) {
            return 0;
        }

        return array[l] + sum(array, l + 1);
    }

    public static void main(String[] args) {
        int[] array = { 1, 3, 3, 5 };
        int res = sum(array);
        System.out.println(res);
    }
}
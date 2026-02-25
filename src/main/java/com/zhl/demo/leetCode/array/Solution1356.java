package com.zhl.demo.leetCode.array;

import java.util.Arrays;

/**
 * @author zhl
 * @version 1.0.0
 * @Description TODO
 * @createTime 2026年02月25日 10:50
 */
public class Solution1356 {
    // 1356. 根据数字二进制下 1 的数目排序
    /*给你一个整数数组 arr 。请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序。

    如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。

    请你返回排序后的数组。*/

    public int[] sortByBits(int[] arr) {
        // 创建包装类数组以便自定义排序
        Integer[] nums = Arrays.stream(arr).boxed().toArray(Integer[]::new);

        // 自定义排序规则
        Arrays.sort(nums, (a, b) -> {
            int bitCountA = countBits(a);
            int bitCountB = countBits(b);

            // 如果1的数目相同，按数值大小排序
            if (bitCountA == bitCountB) {
                return a - b;
            }
            // 否则按1的数目排序
            return bitCountA - bitCountB;
        });

        // 转换回基本类型数组
        return Arrays.stream(nums).mapToInt(Integer::intValue).toArray();
    }

    private int countBits(Integer num) {
        int count = 0;
        int temp = num;
        // 统计当前数字二进制表示中1的个数
        while (temp != 0) {
            // 检查最低位是否为1，使用位运算temp&1
            if ((temp & 1) == 1) {
                count++;
            }
            // 右移一位继续检查下一位
            temp = temp >> 1;
        }
        return count;
    }

    public static void main(String[] args) {
        Solution1356 s = new Solution1356();
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        int[] sortByBits = s.sortByBits(arr);
        System.out.println(Arrays.toString(sortByBits));
    }

}

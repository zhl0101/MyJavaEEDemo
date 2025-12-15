package com.zhl.demo.array;

/**
 * @Description: 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [3,2,3]
 * 输出：3
 * 示例 2：
 *
 * 输入：nums = [2,2,1,1,1,2,2]
 * 输出：2
 */
public class MultElement {
    public static int majorityElement(int[] nums) {
        int count = 0;
        //
        Integer candidate = null;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }
        return candidate;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,2,3,3,3,2,1,2,3,2,3,3,3,3};
        System.out.println(majorityElement(nums));

        int[] nums2 = new int[]{3,2,3};
        System.out.println(majorityElement(nums2));
    }
}

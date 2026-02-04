package com.zhl.demo.leetCode.slide;

import java.util.HashMap;
import java.util.Map;

// 给你一个整数数组 nums 和一个整数 k ，
// 判断数组中是否存在两个 不同的索引 i 和 j ，满足 nums[i] == nums[j] 且 abs(i - j) <= k 。
// 如果存在，返回 true ；否则，返回 false 。
public class ContainsNearbyDuplicate {
    public static boolean containsNearbyDuplicate(int[] nums, int k){
        // 滑动窗口
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j] && j - i <= k) {
                    return true;
                }
            }
        }
        return false;

    }

    public static boolean containsNearbyDuplicate2(int[] nums, int k){
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && i - map.get(nums[i]) <= k) {
                return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        System.out.println(containsNearbyDuplicate2(nums, 3));
        System.out.println(containsNearbyDuplicate2(nums, 2));
        int[] nums2 = {1,0,1,1};
        System.out.println(containsNearbyDuplicate(nums2, 1));
        int[] nums3 = {1,2,3,1,2,3};
        System.out.println(containsNearbyDuplicate(nums3, 2));

    }
}

package com.zhl.demo.leetCode.slide;

import lombok.extern.slf4j.Slf4j;
// 未解决
@Slf4j
public class FindLHS {
//    和谐数组是指一个数组里元素的最大值和最小值之间的差别 正好是 1 。
//
//    给你一个整数数组 nums ，请你在所有可能的 子序列 中找到最长的和谐子序列的长度。
//
//    数组的 子序列 是一个由数组派生出来的序列，它可以通过删除一些元素或不删除元素、且不改变其余元素的顺序而得到。
    public static int findLHS(int[] nums) {
        int count = 0;
        int[] newNums = new int[100];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] - nums[i] == 1 || nums[j] - nums[i] == -1) {
                    newNums[count] = nums[i];
                    count++;

                }
            }
        }
        log.info("新数组：{}", newNums);
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,2,2,5,2,3,7};
        System.out.println(findLHS(nums));
//        System.out.println(findLHS(new int[]{1,2,3,4}));
//        System.out.println(findLHS(new int[]{1,1,1,1}));
    }


}

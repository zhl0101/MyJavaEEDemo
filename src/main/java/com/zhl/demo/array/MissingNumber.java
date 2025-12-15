package com.zhl.demo.array;

public class MissingNumber {
    // 找出缺失的数字
    public static int findMissingNumber(int[] nums) {
        int n = nums.length;
        int sum = n * (n + 1) / 2;
        int actualSum = 0;
        for (int num : nums) {
            actualSum += num;
        }
        return sum - actualSum;
    }
// 1+2+3+4+5+6+7+8+9+10 = 11* 5 = 45  (n+1)*N/2;
    public static void main(String[] args) {
        int[] nums = {1, 2, 4, 6, 3, 7, 8,0};
        System.out.println(findMissingNumber(nums));
    }

}

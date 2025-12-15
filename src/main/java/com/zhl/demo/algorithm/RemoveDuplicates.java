package com.zhl.demo.algorithm;

/**
 * 删除有序数组中的重复项
 */
public class RemoveDuplicates {
    
    /**
     * 删除有序数组中的重复项（保留一个）
     * 使用双指针法
     * 
     * @param nums 有序数组
     * @return 删除重复项后的数组长度
     */
    public static int removeDuplicates(int[] nums) {
        // 边界条件检查
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        // 双指针法
        int slow = 0; // 慢指针，指向不重复元素的位置
        
        for (int fast = 1; fast < nums.length; fast++) {
            // 当快指针指向的元素与慢指针指向的元素不同时
            if (nums[fast] != nums[slow]) {
                slow++; // 慢指针前移
                nums[slow] = nums[fast]; // 将不重复的元素放到慢指针位置
            }
        }
        
        // 返回不重复元素的个数
        return slow + 1;
    }
    
    /**
     * 删除有序数组中的重复项（重复项最多保留k个）
     * 
     * @param nums 有序数组
     * @param k 重复项最多保留的个数
     * @return 删除重复项后的数组长度
     */
    public static int removeDuplicates(int[] nums, int k) {
        // 边界条件检查
        if (nums == null || nums.length <= k) {
            return nums == null ? 0 : nums.length;
        }
        
        // 前k个元素直接保留
        int slow = k;
        
        for (int fast = k; fast < nums.length; fast++) {
            // 检查当前元素是否与slow-k位置的元素相同
            // 如果不同，则说明可以保留
            if (nums[fast] != nums[slow - k]) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        
        return slow;
    }
    
    /**
     * 打印数组的前length个元素
     * 
     * @param nums 数组
     * @param length 长度
     */
    public static void printArray(int[] nums, int length) {
        System.out.print("[");
        for (int i = 0; i < length; i++) {
            System.out.print(nums[i]);
            if (i < length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
    
    public static void main(String[] args) {
        System.out.println("=== 删除有序数组中的重复项 ===");
        
        // 测试用例1：删除重复项（保留一个）
        System.out.println("\n--- 删除重复项（保留一个） ---");
        int[] nums1 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.print("原数组: ");
        printArray(nums1, nums1.length);
        
        int length1 = removeDuplicates(nums1);
        System.out.print("去重后: ");
        printArray(nums1, length1);
        System.out.println("新长度: " + length1);
        
        // 测试用例2：空数组
        int[] nums2 = {};
        System.out.print("\n空数组长度: " + removeDuplicates(nums2));
        
        // 测试用例3：只有一个元素
        int[] nums3 = {1};
        System.out.print("\n单元素数组: ");
        printArray(nums3, removeDuplicates(nums3));
        
        // 测试用例4：删除重复项（保留两个）
        System.out.println("\n--- 删除重复项（保留两个） ---");
        int[] nums4 = {1, 1, 1, 2, 2, 3};
        System.out.print("原数组: ");
        printArray(nums4, nums4.length);
        
        int length4 = removeDuplicates(nums4, 2);
        System.out.print("保留两个重复项后: ");
        printArray(nums4, length4);
        System.out.println("新长度: " + length4);
        
        // 测试用例5：所有元素都相同
        int[] nums5 = {2, 2, 2, 2, 2};
        System.out.print("\n相同元素数组: ");
        printArray(nums5, nums5.length);
        int length5 = removeDuplicates(nums5);
        System.out.print("去重后: ");
        printArray(nums5, length5);
        System.out.println("新长度: " + length5);
    }
}
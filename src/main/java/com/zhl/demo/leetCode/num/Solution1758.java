package com.zhl.demo.leetCode.num;

/**
 * @author zhl
 * @version 1.0.0
 * @Description TODO
 * @createTime 2026年03月05日 17:00
 */
public class Solution1758 {
    // 1758. 生成交替二进制字符串的最少操作数
    /*给你一个仅由字符 '0' 和 '1' 组成的字符串 s 。一步操作中，你可以将任一 '0' 变成 '1' ，或者将 '1' 变成 '0' 。

    交替字符串 定义为：如果字符串中不存在相邻两个字符相等的情况，那么该字符串就是交替字符串。
    例如，字符串 "010" 是交替字符串，而字符串 "0100" 不是。
    返回使 s 变成 交替字符串 所需的 最少 操作数。*/
    public int minOperations(String s) {
        // 交替字符串只有两种可能：
        // 1. 以'0'开头："010101..." (偶数位为'0',奇数位为'1')
        // 2. 以'1'开头："101010..." (偶数位为'1',奇数位为'0')
            
        // 计算转换为以'0'开头的交替字符串所需的操作数
        int count0 = 0;
        for (int i = 0; i < s.length(); i++) {
            char expected = (i % 2 == 0) ? '0' : '1';
            if (s.charAt(i) != expected) {
                count0++;
            }
        }
            
        // 计算转换为以'1'开头的交替字符串所需的操作数
        int count1 = 0;
        for (int i = 0; i < s.length(); i++) {
            char expected = (i % 2 == 0) ? '1' : '0';
            if (s.charAt(i) != expected) {
                count1++;
            }
        }
            
        // 返回较小的操作数
        return Math.min(count0, count1);
    }
        
    /**
     * 优化方法：一次遍历同时计算两种情况
     * 时间复杂度：O(n)，但只需遍历一次
     * @param s 输入字符串
     * @return 最少操作数
     */
    public int minOperationsOptimized(String s) {
        int count0 = 0;  // 以'0'开头的操作数
        int count1 = 0;  // 以'1'开头的操作数
            
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 以'0'开头：偶数位应为'0'，奇数位应为'1'
            char expected0 = (i % 2 == 0) ? '0' : '1';
            // 以'1'开头：偶数位应为'1'，奇数位应为'0'
            char expected1 = (i % 2 == 0) ? '1' : '0';
                
            if (c != expected0) {
                count0++;
            }
            if (c != expected1) {
                count1++;
            }
        }
            
        return Math.min(count0, count1);
    }

    public static void main(String[] args) {
        Solution1758 solution1758 = new Solution1758();
        System.out.println(solution1758.minOperations("0100"));
        System.out.println(solution1758.minOperations("10"));
        System.out.println(solution1758.minOperations("1111"));
    }

}

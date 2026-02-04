package com.zhl.demo.leetCode.algorithm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestPalindrome {
    
    /**
     * 计算用字符串中字符能构造的最长回文串的长度
     * 
     * 解题思路：
     * 1. 统计每个字符出现的次数
     * 2. 对于每个字符：
     *    - 如果出现次数为偶数，全部加入回文串
     *    - 如果出现次数为奇数，将其减1后加入回文串（即取最大的偶数部分）
     * 3. 如果有任何字符出现奇数次，可以在回文串中心放置一个字符，长度再加1
     * 
     * @param s 输入字符串
     * @return 最长回文串的长度
     */
    public static int longestPalindrome(String s) {
        // 统计每个字符出现的次数
        Map<Character, Integer> charCount = new HashMap<Character, Integer>();
        for (char c : s.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        
        int palindromeLength = 0;
        boolean hasOdd = false;
        
        // 计算回文串长度
        for (int count : charCount.values()) {
            // 每个字符取最大的偶数部分
            palindromeLength += (count / 2) * 2;
            
            // 如果有字符出现奇数次，标记一下
            if (count % 2 == 1) {
                hasOdd = true;
            }
        }
        
        // 如果有字符出现奇数次，可以在回文串中心放置一个字符
        if (hasOdd) {
            palindromeLength += 1;
        }
        
        return palindromeLength;
    }
    
    public static void main(String[] args) {
        // 测试用例
        System.out.println("测试用例:");
        System.out.println("输入: \"abccccdd\" -> 输出: " + longestPalindrome("abccccdd"));
        System.out.println("输入: \"a\" -> 输出: " + longestPalindrome("a"));
        System.out.println("输入: \"Aa\" -> 输出: " + longestPalindrome("Aa"));
        System.out.println("输入: \"aabbcc\" -> 输出: " + longestPalindrome("aabbcc"));
        System.out.println("输入: \"abcdef\" -> 输出: " + longestPalindrome("abcdef"));
    }
}
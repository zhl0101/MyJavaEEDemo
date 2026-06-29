package com.zhl.demo.leetCode.string;

/**
 * @author zhl
 * @version 1.0.0
 * @Description TODO 1967. 作为子字符串出现在单词中的字符串数目
 * @createTime 2026年06月29日 18:07
 */
public class Solution1967 {
    /*给你一个字符串数组 patterns 和一个字符串 word ，统计 patterns 中有多少个字符串是 word 的子字符串。返回字符串数目。

    子字符串 是字符串中的一个连续字符序列。*/
    public int numOfStrings(String[] patterns, String word) {
        int ans = 0;
        for (String pattern : patterns) {
            if (word.contains(pattern)) {
                ans++;
            }
        }
        return ans;
    }

    public int numOfStrings2(String[] patterns, String word) {
        int ans = 0;
        for (String pattern : patterns) {
            if (word.indexOf(pattern) != -1) {
                ans++;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        String[] patterns = {"a","abc","bc","d"};
        String word = "abc";
        System.out.println(new Solution1967().numOfStrings(patterns, word));
        System.out.println(new Solution1967().numOfStrings2(patterns, word));
    }
}

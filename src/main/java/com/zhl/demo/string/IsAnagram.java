package com.zhl.demo.string;

import java.util.Arrays;

//给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的 字母异位词。
public class IsAnagram {
    public boolean isAnagram(String s , String t){
        // TODO 判断长度
        if(s.length() != t.length()){
            return false;
        }
        // 转小写
        s = s.toLowerCase();

        t = t.toLowerCase();
        // 排序
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        Arrays.sort(sArray);
        Arrays.sort(tArray);

        // 比较
        return Arrays.equals(sArray, tArray);
    }

    public static void main(String[] args) {
        System.out.println(new IsAnagram().isAnagram("anagram", "nagaram"));
        System.out.println(new IsAnagram().isAnagram("rat", "car"));
        System.out.println(new IsAnagram().isAnagram("a", "ab"));
        System.out.println(new IsAnagram().isAnagram("ab", "a"));
        System.out.println(new IsAnagram().isAnagram("aacc", "ccaa"));

    }
}

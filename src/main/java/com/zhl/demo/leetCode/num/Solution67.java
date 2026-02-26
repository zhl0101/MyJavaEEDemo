package com.zhl.demo.leetCode.num;

/**
 * @author zhl
 * @version 1.0.0
 * @Description TODO
 * @createTime 2026年02月26日 17:39
 */
public class Solution67 {
    // 给定两个二进制字符串 a 和 b ，以二进制字符串的表示形式返回它们的和。
    public String addBinary(String a, String b) {
        return Integer.toBinaryString(
                Integer.parseInt(a, 2) + Integer.parseInt(b, 2)
        );


    }

    public String addBinary2(String a, String b) {
        int m = a.length() - 1, n = b.length() - 1;
        StringBuilder answer = new StringBuilder();
        int carry = 0;
        while (m >= 0 || n >= 0) {
            int sum = carry;
            sum += m >= 0 ? a.charAt(m) - '0' : 0;
            sum += n >= 0 ? b.charAt(n) - '0' : 0;
            answer.append(sum % 2);
            carry = sum / 2;
            m--;
            n--;
        }
        if (carry > 0) {
            answer.append('1');
        }
        return answer.reverse().toString();
    }

    public static void main(String[] args) {
        Solution67 solution67 = new Solution67();
        String res = solution67.addBinary("11", "1");

        System.out.println(res);
        System.out.println(solution67.addBinary("1111", "10"));

    }
}

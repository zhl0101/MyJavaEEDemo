package com.zhl.demo.leetCode.array;

/**
 * @author zhl
 * @version 1.0.0
 * @Description TODO
 * @createTime 2026年02月26日 16:43
 */
public class Solution1404 {
    /*给你一个以二进制形式表示的数字 s 。请你返回按下述规则将其减少到 1 所需要的步骤数：

    如果当前数字为偶数，则将其除以 2 。

    如果当前数字为奇数，则将其加上 1 。

    题目保证你总是可以按上述规则将测试用例变为 1 。*/

    public int numSteps(String s) {
        int count = 0;
        StringBuilder sb = new StringBuilder(s);
        // 直接操作二进制字符串
        // 判断当前数字是否为1
        while (sb.length()>1 || sb.charAt(0) != '1'){
           // 判断当前数字是否为偶数
            if (sb.charAt(sb.length() - 1) == '0') {
                // 如果当前数字为偶数，则将其除以 2 就是将字符串右移一位
                sb.deleteCharAt(sb.length() - 1);
            } else {
                // 如果当前数字为奇数，则将其加上 1
                int a = sb.length() - 1 ;
                // 从右往左找到第一个0，将其变为1，右边的1都变为0
                while (a >= 0 && sb.charAt(a) == '1') {
                     sb.setCharAt(a, '0');
                     a--;
                }
                if (a>=0){
                    // 将找到的0变为1
                    sb.setCharAt(a, '1');
                }else {
                    // 如果都是1，则需要在最前面添加一个1
                    sb.insert(0, '1');
                }
            }
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        Solution1404 solution1404 = new Solution1404();
        System.out.println(solution1404.numSteps("1101"));
        System.out.println(solution1404.numSteps("10"));
        System.out.println(solution1404.numSteps("1"));
        System.out.println(solution1404.numSteps("1111"));
    }
}
